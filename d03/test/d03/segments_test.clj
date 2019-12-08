(ns d03.segments-test
  (:require [clojure.test :refer :all]
            [d03.segments :refer :all]))

(deftest test-segment-calculation
  (testing "GIVEN one vertical segment WHEN calculate segments THEN return correct segment"
    (is (= {:horizontal [] :vertical [[0 0 10]]} (calculate-segments [[:u 10]])))
    (is (= {:horizontal [] :vertical [[0 -10 0]]} (calculate-segments [[:d 10]]))))

  (testing "GIVEN one horiztonal segment WHEN calculate segments THEN return correct segment"
    (is (= {:horizontal [[-8 0 0]] :vertical []} (calculate-segments [[:l 8]])))
    (is (= {:horizontal [[0 12 0]] :vertical []} (calculate-segments [[:r 12]]))))

  (testing "GIVEN real path WHEN calculate segments THEN return correct segments"
    (is (= {:horizontal [[0 75 0] [75 158 -30] [146 158 53] [146 217 4] [145 217 11]]
            :vertical [[75 -30 0] [158 -30 53] [146 4 53] [217 4 11]]}
           (calculate-segments [[:r 75] [:d 30] [:r 83] [:u 83] [:l 12] [:d 49] [:r 71] [:u 7] [:l 72]])))))

