(ns d02.core
  (:gen-class)
  (:require [d02.intcode :as ic]))

(defn -main
  "Calculating..."
  [& args]
  (let [content (slurp "resources/input.txt")]
    (print (ic/intcode (ic/parse-input content)))))
