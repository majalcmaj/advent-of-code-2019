(ns d03.parsing
  (:require [clojure.string :refer [split lower-case]]))

(defn- split-input [input]
  (if (> (count input) 0)
    (vec (split input #","))
    []))

(defn- parse-single-segment [segment]
  [(keyword (lower-case (first segment)))
   (read-string (subs segment 1))])

(defn parse-path [input]
  (let [split (split-input input)]
    (vec (map parse-single-segment split))))
