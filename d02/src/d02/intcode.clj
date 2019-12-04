(ns d02.intcode
  (:gen-class)
  (:require [clojure.string :as str]
            [clojure.edn :as edn]))

(defn parse-input [input]
  (if (> (count input) 0)
    (vec (map edn/read-string (str/split input #",")))
    []))

(defn run-operation [operation program ip]
  (let [res (operation (nth program (nth program ip)) (nth program (nth program (inc ip))))]
    (assoc program (nth program (+ ip 2)) res)))

(defn addition [program ip]
  (run-operation + program ip))

(defn multiplication [program ip]
  (run-operation * program ip))

(declare next-instruction)

(defn exec-instruction [instruction program ip]
  (let [new-prog (instruction program (inc ip))]
    (next-instruction new-prog (+ ip 4))))

(defn next-instruction [program ip]
  (let [inst (nth program ip)]
    (cond
      (= inst 1)
      (exec-instruction addition program ip)
      (= inst 2)
      (exec-instruction multiplication program ip)
      (= inst 99) program)))

(defn intcode [program]
  (next-instruction program 0))