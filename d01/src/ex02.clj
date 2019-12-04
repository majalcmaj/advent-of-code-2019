(ns ex02
  (:require [clojure.edn :as edn])
  (:gen-class))

(defn rectify [number] 
  (if (> number 0)
    number
    0))

(defn calculate [weight]
  (rectify (- (quot weight 3) 2)))

(defn calculate-fuel [weight]
  (if (> weight 0)
    (let [fuel-weight (calculate weight)]
      (+ (calculate-fuel fuel-weight) fuel-weight))
    0
    )
  )

(defn sum-fuel [list sum]
  (if (> (count list) 0) 
    (let [[current & rest] list]
      (recur rest (+ (calculate-fuel (edn/read-string current)) sum)))
    sum))
  

(defn -main []
  (with-open [rdr (clojure.java.io/reader "input.txt")]
    (println (sum-fuel (line-seq rdr) 0))
    ))
   
    
