(ns d05.intcode.operations)

(defrecord Operation [opcode nargs function])
(defrecord Write [address value])
(defrecord Halt [])
(defrecord WriteStdout [value])

(defn- binary-operation [op]
  (fn [arg1 arg2 write-address]
    (let [result (op arg1 arg2)]
      (Write. write-address result)))) 

(def addition (Operation. 1 3 (binary-operation +)))
(def multiplication (Operation. 2 3 (binary-operation *)))
(def halt (Operation. 99 0 (Halt.)))
(def reading (Operation. 3 1 (fn [write-addr]
                              (let [input (Integer/parseInt (read-line))]
                              (Write. write-addr input)))))
(def writing (Operation. 4 1 (fn [arg] (WriteStdout. arg))))
