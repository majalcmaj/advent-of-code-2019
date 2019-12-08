(ns d03.segments)

(defn- calc-dir-res-final-point [point vector]
  (let [dir (first vector)
        value (second vector)
        x (first point)
        y (second point)]
    (case dir
      :u [:v [x y (+ y value)] [x (+ y value)]]
      :d [:v [x (- y value) y] [x (- y value)]]
      :l [:h [(- x value) x y] [(- x value) y]]
      :r [:h [x (+ x value) y] [(+ x value) y]])))

(defn calculate-segments
  ([path] (calculate-segments path [0 0] [] []))
  ([[vector & rest] cur-xy horiz vert]
   (if vector
     (let [[dir res final-point] (calc-dir-res-final-point cur-xy vector)
           [new-vert new-horiz] (if (= dir :v)
                                  [(conj vert res) horiz] [vert (conj horiz res)])]
       (recur rest final-point new-horiz new-vert))
     {:horizontal horiz :vertical vert})))
