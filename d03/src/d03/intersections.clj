(ns d03.intersections)

(defn- are-intersecting? [[horiz vert]]
  (let [[x1h x2h yh] horiz
        [xv y1v y2v] vert]
    (and (<= x1h xv x2h) (<= y1v yh y2v))))

(defn- calc-intersection-point [[horiz vert]]
  [(first vert) (last horiz)])

(defn calculate-intersections [horiz vert]
  (let [pairs (for [h horiz v vert] [h v])
        intersecting-pairs (filter are-intersecting? pairs)]
    (map calc-intersection-point intersecting-pairs)))
