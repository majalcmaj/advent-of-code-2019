(ns d03.core
  (:gen-class)
  (:require [d03.parsing :as p]
            [d03.segments :as s]
            [d03.intersections :as i]))

(defn manhattan-dist [p]
  (+ (Math/abs (first p)) (Math/abs (second p))))

(defn get-smallest-nonzero-manhattan-dist [points]
  (let [distances (map manhattan-dist points)
        nonzero-distances (filter #(> % 0) distances)]
    (apply min nonzero-distances)))

(defn calculate-smallest-dist [line1 line2]
  (let [input1 (p/parse-path line1)
        input2 (p/parse-path line2)
        segments1 (s/calculate-segments input1)
        segments2 (s/calculate-segments input2)
        intersections1 (i/calculate-intersections (segments1 :horizontal) (segments2 :vertical))
        intersections2 (i/calculate-intersections (segments2 :horizontal) (segments1 :vertical))
        intersections (concat intersections1 intersections2)]
    (get-smallest-nonzero-manhattan-dist intersections)))

(defn -main
  [& args]
  (with-open [rdr (clojure.java.io/reader "resources/input.txt")]
    (let [[line1 line2] (vec (line-seq rdr))]
      (println (calculate-smallest-dist line1 line2)))))

