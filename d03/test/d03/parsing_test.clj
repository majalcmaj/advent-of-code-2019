(ns d03.parsing-test
  (:require [clojure.test :refer :all]
            [d03.parsing :refer :all]))

(deftest test-parse
  (testing "GIVEN empty line WHEN parse THEN return empty vec"
    (is (= (parse-path "") [])))
  (testing "GIVEN real input1 WHEN parse THEN return correct output"
    (is (= (parse-path "R75,D30,R83,U83,L12,D49,R71,U7,L72") 
           [[:r 75] [:d 30] [:r 83] [:u 83] [:l 12] [:d 49] [:r 71] [:u 7] [:l 72]]))))