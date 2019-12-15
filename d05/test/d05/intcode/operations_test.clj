(ns d05.intcode.operations-test
  (:require [d05.intcode.operations :refer :all]
            [clojure.test :refer :all])
  (:import [d05.intcode.operations Write Halt WriteStdout]))

(deftest test-operations
  (testing "WHEN addition THEN result correct"
    (is (= 1 (:opcode addition)))
    (is (= 3 (:nargs addition)))
    (is (= (Write. 20 10) ((:function addition) 4 6 20))))
  (testing "WHEN multiplication THEN result correct"
    (is (= 2 (:opcode multiplication)))
    (is (= 3 (:nargs multiplication)))
    (is (= (Write. 4 30) ((:function multiplication) 3 10 4))))
  (testing "WHEN halt THEN result correct"
    (is (= 99 (:opcode halt)))
    (is (= 0 (:nargs halt)))
    (is (= (Halt.) (:function halt))))
  (testing "WHEN reading THEN result correct"
    (is (= 3 (:opcode reading)))
    (is (= 1 (:nargs reading)))
    (is (= (Write. 31 23) (with-in-str "23" ((:function reading) 31)))))
  (testing "WHEN writing THEN result correct"
    (is (= 4 (:opcode writing)))
    (is (= 1 (:nargs writing)))
    (is (= (WriteStdout. 123) ((:function writing) 123)))))

