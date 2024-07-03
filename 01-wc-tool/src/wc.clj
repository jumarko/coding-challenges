(ns wc
  "The wc tool: https://codingchallenges.fyi/challenges/challenge-wc/

  Resources
  - Locale: https://learn.microsoft.com/en-us/globalization/locale/locale"
  (:require [clojure.tools.cli :as cli]
            [clojure.java.io :as io]
            [clojure.string :as str]))

(defn count-bytes [f]
  (.length f))

(defn count-chars
  "Counting characters depends on locale: https://learn.microsoft.com/en-us/globalization/locale/locale
  Compare the output with `wc -m` (the real wc)."
  [f]
  ;; NOTE: loads the whole file in memory -> not great for huge files
  (-> f slurp count))

(defn count-lines [f]
  ;; NOTE: using line-seq might not be the most efficient option, especially for large files
  (count (line-seq (io/reader f))))

(defn count-words [f]
  ;; NOTE: we are holding the whole file in memory
  (count (-> f slurp (str/split #"\s+"))))

(defn count-file
  [f options]
  (cond
    (empty? options) [(count-lines f) (count-words f) (count-bytes f)]
    (:bytes options) [(count-bytes f)]
    (:chars options) [(count-chars f)]
    (:lines options) [(count-lines f)]
    (:words options) [(count-words f)]))


(def cli-options
  [["-c" "--bytes" "Count bytes in a file"]
   ["-m" "--chars" "Count characters in a file"]
   ["-l" "--lines" "Count lines in a file"]
   ["-w" "--words" "Count words in a file"]])

;; https://github.com/clojure/tools.cli
(defn parse-args [args]
  ;; TODO: handle errors - e.g required file arg is missing
  (cli/parse-opts args cli-options))

(defn main [& args]
  (let [{:keys [arguments options] :as _parsed-args} (parse-args args)
        filename (first arguments)]
    (when-let [f (or (io/file filename)
                     *in*)]
      (apply println (conj (count-file f options)
                           ;; if reading from stdin, we don't want to print 'nil'
                           (or filename ""))))))

(comment
  (time (main "--lines" "/Users/jumar/workspace/CODESCENE/CODE/codescene/dev/athena/resources/onprem/analysis/durations-with-account-info_2024-06-12.csv"))

  (time (main "--words" "/Users/jumar/workspace/CODESCENE/CODE/codescene/dev/athena/resources/onprem/analysis/durations-with-account-info_2024-06-12.csv"))
  )
