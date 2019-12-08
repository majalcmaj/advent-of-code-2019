(ns d06.core
  (:gen-class)
  (:require [clojure.string :as str]))

(defrecord Node [children parent])

(def empty-node (Node. [] nil))

(defn- add-element-to-map [curr-map [el1 el2]]
  (let [cur-el1-val (get curr-map el1 empty-node)
        new-el1-val (Node. (conj (:children cur-el1-val) el2) (:parent cur-el1-val))
        el2-val (get curr-map el2 empty-node)
        new-el2-val (Node. (:children el2-val) el1)]
    (assoc curr-map el1 new-el1-val el2 new-el2-val)))

(defn create-orbit-tree
  ([input] (create-orbit-tree {} input))
  ([curr-map [pair & other-pairs]]
   (if pair
     (let [new-map (add-element-to-map curr-map pair)]
       (recur new-map other-pairs))
     curr-map)))

(defn calculate-orbits-count ([tree] (calculate-orbits-count tree "COM" 0))
  ([tree object-name orbit]
   (let [current-children (:children (tree object-name))
         children-orbits-count (map #(calculate-orbits-count tree % (inc orbit)) current-children)]
     (+ orbit (reduce + children-orbits-count)))))

(defn calc-path-from-root
  ([tree node] (calc-path-from-root tree node []))
  ([tree node path]
   (if node
     (recur tree (:parent (tree node)) (conj path node))
     (into [] (reverse path)))))

(defn calc-paths-diff [[h1 & r1] [h2 & r2]]
  (if (= h1 h2)
    (recur r1 r2)
    (+ (count r1) (count r2))))

(defn calculate-paths-diff-you-santa [tree]
  (let [you-path (calc-path-from-root tree "YOU")
        santa-path (calc-path-from-root tree "SAN")
        paths-diff (calc-paths-diff you-path santa-path)]
    (println "You santa paths diff" paths-diff)))

(defn parse-input [input]
  (mapv #(vec (str/split % #"\)")) input))

(defn -main
  [& args]
  (with-open [rdr (clojure.java.io/reader "resources/input.txt")]
    (let [arg (first args)
          raw-input (vec (line-seq rdr))
          input (parse-input raw-input)
          tree (create-orbit-tree input)]
      (if (= arg "1")
        (println (calculate-orbits-count tree))
        (println "A" (calculate-paths-diff-you-santa tree))))))

