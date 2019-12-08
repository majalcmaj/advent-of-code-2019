(ns d06.core-test
  (:require [clojure.test :refer :all]
            [d06.core :refer :all]))

(def example-tree {"COM" ["B"]
                   "B" ["C" "G"]
                   "G" ["H"]
                   "H" []
                   "C" ["D"]
                   "D" ["E" "I"]
                   "E" ["F" "J"]
                   "I" []
                   "J" ["K"]
                   "F" []
                   "K" ["L"]
                   "L" []})

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
