(ns d06.core
  (:gen-class)
  (:require [clojure.string :as str]))

(defn- add-element-to-map [curr-map [el1 el2]]
  (let [cur-el1-val (get curr-map el1 [])
        new-el1-val (conj cur-el1-val el2)
        el2-val (get curr-map el2 [])]
    (assoc curr-map el1 new-el1-val el2 el2-val)))

(defn create-orbit-tree
  ([input] (create-orbit-tree {} input))
  ([curr-map [pair & other-pairs]]
   (if pair
     (let [new-map (add-element-to-map curr-map pair)]
       (recur new-map other-pairs))
     curr-map)))

(defn calculate-orbits-count ([tree] (calculate-orbits-count tree "COM" 0))
  ([tree object-name orbit]
   (let [current-children (tree object-name)
         children-orbits-count (map #(calculate-orbits-count tree % (inc orbit)) current-children)]
     (+ orbit (reduce + children-orbits-count)))))

(defn parse-input [input]
  (mapv #(vec (str/split % #"\)")) input))

(defn -main
  [& args]
  (with-open [rdr (clojure.java.io/reader "resources/input.txt")]
    (let [raw-input (vec (line-seq rdr))
          input (parse-input raw-input)
          tree (create-orbit-tree input)
          orbits-count (calculate-orbits-count tree)]
      (println orbits-count))))
