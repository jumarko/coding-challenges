(ns wc-test
  (:require
   [clojure.test :refer [deftest is testing]]
   [wc :as wc]))

(deftest count-bytes
  (testing "there should be 342190 bytes in the test file: https://codingchallenges.fyi/challenges/challenge-wc/#step-one"
    (is (= 342190
           (wc/main "-c" "resources/test.txt"))))
  )
