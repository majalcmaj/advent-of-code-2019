(ns d06.core-test
  (:require [clojure.test :refer :all]
            [d06.core :refer :all])
  (:import [d06.core Node]))
(def example-tree {"COM" (Node. ["B"] nil)
                   "B" (Node. ["C" "G"] "COM")
                   "G" (Node. ["H"] "B")
                   "H" (Node. [] "G")
                   "C" (Node. ["D"] "B")
                   "D" (Node. ["E" "I"] "C")
                   "E" (Node. ["F" "J"] "D")
                   "I" (Node. [] "D")
                   "J" (Node. ["K"] "E")
                   "F" (Node. [] "E")
                   "K" (Node. ["L"] "J")
                   "L" (Node. [] "K")})

(deftest test-create-orbit-map
  (testing "GIVEN empty input WHEN called THEN returns empty map"
    (is (= {} (create-orbit-tree []))))
  (testing "GIVEN input WHEN called THEN returns correct map"
    (is (= example-tree
           (create-orbit-tree
            [["COM" "B"] ["B" "C"] ["C" "D"] ["D" "E"] ["E" "F"] ["B" "G"] ["G" "H"] ["D" "I"] ["E" "J"] ["J" "K"] ["K" "L"]])))))

(deftest test-calculate-orbit-count
  (testing "given tree when calculate then correct answer"
    (is (= 42 (calculate-orbits-count example-tree)))))

(deftest test-calculate-path-to-root
  (testing "when called then result correct"
    (is (= ["COM" "B" "C" "D" "E" "J"] (calc-path-from-root example-tree "J")))))

(deftest test-calculate-paths-diff
  (testing "when called then result correct"
    (is (= 5
           (calc-paths-diff ["COM" "B" "C" "D" "E" "J" "K"]
                            ["COM" "B" "G" "H"])))))
