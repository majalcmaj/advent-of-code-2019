(ns d02.intcode-test
  (:require [clojure.test :refer :all]
            [d02.intcode :refer :all]))

(deftest should-parse-input-correctly
  (testing "Should return empty list on empty input"
    (is (= [] (parse-input ""))))
  (testing "Should return correct list on input"
    (is (= [1 0 1 4 2 2 2 2 99] (parse-input "1,0,1,4,2,2,2,2,99")))))

(deftest test-addition-operator
  (testing "Should modify mem accordingly"
    (is (= (addition [1 0 0 0] 1) [2 0 0 0]))
    (is (= (addition [1 1 2 1] 1) [1 3 2 1]))))

(deftest test-multiplication-operator
  (testing "Should modify mem accordingly"
    (is (= (multiplication [2 0 0 0] 1) [4 0 0 0]))
    (is (= (multiplication [10 0 2 1] 1) [10 20 2 1]))))

(deftest should-work-correctly
  (testing "Case 1"
    (is (= (intcode [1 0 0 0 99])) [2 0 0 0 99])
    (is (= (intcode [2 3 0 3 99]) [2 3 0 6 99]))
    (is (= (intcode [2 4 4 5 99 0]) [2 4 4 5 99 9801]))
    (is (= (intcode [1 1 1 4 99 5 6 0 99]) [30 1 1 4 2 5 6 0 99]))
    (is (= (intcode [1 9 10 3 2 3 11 0 99 30 40 50]) [3500 9 10 70 2 3 11 0 99 30 40 50]))
    ))