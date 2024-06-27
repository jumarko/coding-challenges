(ns wc-test
  (:require
   [clojure.test :refer [deftest is testing]]
   [wc :as wc]))

(deftest count-file
  (testing "count bytes: https://codingchallenges.fyi/challenges/challenge-wc/#step-one"
    (is (= 342190
           (wc/main "-c" "resources/test.txt"))))
  (testing "count lines: https://codingchallenges.fyi/challenges/challenge-wc/#step-two"
    (is (= 7145
           (wc/main "-l" "resources/test.txt"))))
  )

(deftest long-options
  (testing "count bytes"
    (is (= 342190
           (wc/main "--bytes" "resources/test.txt"))))
  (testing "count lines"
    (is (= 7145
           (wc/main "--lines" "resources/test.txt")))))
