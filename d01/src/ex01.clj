(ns ex01
  (:require [clojure.edn :as edn])
  (:gen-class))

(defn calculate [weight]
  (- (quot weight 3) 2))
  

(defn sum-fuel [list sum]
  (if (> (count list) 0) 
    (let [[current & rest] list]
      (recur rest (+ (calculate (edn/read-string current)) sum)))
    sum))
  

(defn -main []
  (with-open [rdr (clojure.java.io/reader "input.txt")]
    (println (sum-fuel (line-seq rdr) 0))
    ))
   
    
