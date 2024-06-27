(ns wc-test
  (:require
   [clojure.test :refer [deftest is testing]]
   [wc :as wc]))

;; At least one test for each step - see https://codingchallenges.fyi/challenges/challenge-wc
(deftest count-file
  (testing "Step 1: count bytes"
    (is (= 342190
           (wc/main "-c" "resources/test.txt"))))
  (testing "Step 2: count lines"
    (is (= 7145
           (wc/main "-l" "resources/test.txt"))))
  (testing "Step 3: count lines"
    (is (= 58164
           (wc/main "-w" "resources/test.txt"))))
  )

(deftest long-options
  (testing "count bytes"
    (is (= 342190
           (wc/main "--bytes" "resources/test.txt"))))
  (testing "count lines"
    (is (= 7145
            (wc/main "--lines" "resources/test.txt")))))
