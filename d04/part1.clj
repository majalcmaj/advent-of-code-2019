(defn to-digits ([number] (to-digits number []))
  ([number curr] 
    (if (> number 0) 
      (recur (quot number 10) (conj curr (mod number 10)))
      (into [] (reverse curr)))))
(defn check-if-matches [number]
  (let [digits (to-digits number)
        prep-0-digits (into [0] digits)
        app-0-digits (conj digits 10)
        diff (map - app-0-digits prep-0-digits)]
        (and (every? #(>= % 0) diff) (some #(= % 0) diff))))
(defn find-numbers [current cap curr-count]
  (let [current-matches (check-if-matches current)
        next-count (+ curr-count (if current-matches 1 0))]
    (if (>= cap current) 
    (recur (inc current) cap next-count)
    (print "Result " curr-count))))
(find-numbers 108457 562041 0)
