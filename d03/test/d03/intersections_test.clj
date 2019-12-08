(ns d03.intersections-test
  (:require [d03.intersections :refer [calculate-intersections]]
            [clojure.test :refer :all]))

(deftest test-calculates-intersections
  (testing "GIVEN no intersections WHEN calc THEN return empty vec"
    (is (= [] (calculate-intersections [[0 10 0] [2 4 10]] [[1 1 3] [-2 0 10]]))))
  (testing "GIVEN intersection WHEN calc THEN return correct intersection point"
    (is (= [[7 2]] (calculate-intersections [[5 10 2]] [[7 1 3]]))))
  (testing "GIVEN intersections WHEN calc THEN return correct intersection points"
    (is (= (set [[0 0] [6 5] [8 0]])
           (set (calculate-intersections [[0 8 0] [3 8 5]]
                                         [[0 0 7] [6 3 7] [8 0 1]]))))))
