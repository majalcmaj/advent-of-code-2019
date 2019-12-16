(defn to-digits ([number] (to-digits number []))
  ([number curr]
   (if (> number 0)
     (recur (quot number 10) (conj curr (mod number 10)))
     (into [] (reverse curr)))))

(defn check-if-matches
  ([number] (let [digits (to-digits number)] (check-if-matches digits 0 0 false)))
  ([[first & rest] last-number reps-cnt pair-found]
   (cond
     (= nil first) (or pair-found (= reps-cnt 1))
     (= first last-number) (recur rest first (inc reps-cnt) pair-found)
     (> first last-number) (recur rest first 0 (or (= reps-cnt 1) pair-found))
     :else false)))

(defn find-numbers [current cap curr-count]
  (let [current-matches (check-if-matches current)
        next-count (+ curr-count (if current-matches 1 0))]
    ;; (when current-matches (println current))
    (if (>= cap current)
      (recur (inc current) cap next-count)
      (println "Result " curr-count))))
(find-numbers 108457 562041 0)

(print
 (check-if-matches 112233)
 (check-if-matches 123444)
 (check-if-matches 111122)
 (check-if-matches 111122)
 (check-if-matches 112223)
 (check-if-matches 111113)
 (check-if-matches 221133)
 (check-if-matches 111222)
 (check-if-matches 123444)
 (check-if-matches 223456)
 (check-if-matches 223456)
 (check-if-matches 111123)
 )
