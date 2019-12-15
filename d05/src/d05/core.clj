(ns d05.core
  (:gen-class)
  (:require [d05.intcode.core :as ic]))

(defn run-intcode [input]
  (first (ic/intcode input)))

(defn find-code-for-number
  ([input number] (find-code-for-number 0 0 input number))
  ([noun verb input number]
   (if (< noun 100)
     (let [input-with-code (assoc input 1 noun 2 verb)
           result (run-intcode input-with-code)]
       (if (= result number)
         (println "The result is " (+ (* 100 noun) verb))
         (if (< verb 100)
           (recur noun (inc verb) input number)
           (recur (inc noun) 0 input number))))
     (println "Failed to find the solution"))))

(defn -main
  [& args]
  (let [input (ic/parse-input (slurp "resources/input.txt"))]
    (if (= (first args) "d01")
      (print (run-intcode input)))
    (print (find-code-for-number input 19690720))))
