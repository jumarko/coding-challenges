(ns wc-test
  (:require
   [clojure.java.io :as io]
   [clojure.test :refer [deftest is testing]]
   [wc :as wc]))

(defn- do-count [options]
  (wc/count-file (io/file "resources/test.txt") options))

;; At least one test for each step - see https://codingchallenges.fyi/challenges/challenge-wc
(deftest count-file
  (testing "Step 1: count bytes"
    (is (= [342190]
           (do-count {:bytes true}))))
  (testing "Step 2: count lines"
    (is (= [7145]
           (do-count {:lines true}))))
  (testing "Step 3: count words"
    (is (= [58164]
           (do-count {:words true}))))
  (testing "Step 4: count characters"
    (is (= [339292]
           (do-count {:chars true}))))
  (testing "Step 5: default counts (no explicit options)"
    (is (= [7145 58164 342190]
           (do-count {}))))
  )

(deftest count-stdin
  (testing "prints expected output"
    ;; This output is maybe not as nice but it makes implementation easier and it's fine for the end user (easy to read)
    (is (= "4 \n" (binding [*in* (java.io.StringReader. "anything \n with \n new \n lines and some words")]
                    (with-out-str (wc/main "-l")))))))


(deftest invoke-main-options
  (testing "prints expected output"
    (is (= "342190 resources/test.txt\n" (with-out-str (wc/main "-c" "resources/test.txt")))))
  (testing "default options"
    (is (= "7145 58164 342190 resources/test.txt\n" (with-out-str (wc/main "resources/test.txt")))))
  (testing "long-form option"
    (is (= "342190 resources/test.txt\n" (with-out-str (wc/main "--bytes" "resources/test.txt"))))))
