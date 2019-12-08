(ns d03.core-test
  (:require [clojure.test :refer :all]
            [d03.core :refer :all]))

(deftest test-manhattan-dist
  (testing "when manhattan-dist then return correct value"
    (is (= (manhattan-dist [0 0]) 0))
    (is (= (manhattan-dist [5 19]) 24))
    (is (= (manhattan-dist [-5 19]) 24))
    (is (= (manhattan-dist [-5 -96]) 101)))
  (testing "when smallest dist then return correct value"
    (is (=
         (get-smallest-nonzero-manhattan-dist [[0 0] [-2 4] [10 0] [-6 -5] [6 5]])
         6))))

(deftest test-calculate-smallest-dist
  (testing "given input when calculate then result correct"
    (is (= 6 (calculate-smallest-dist "R8,U5,L5,D3" "U7,R6,D4,L4")))))
