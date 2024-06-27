(ns wc
  "The wc tool: https://codingchallenges.fyi/challenges/challenge-wc/"
  (:require [clojure.tools.cli :as cli]
            [clojure.java.io :as io]
            [clojure.string :as str]))

(defn count-file
  [{:keys [arguments options]}]
  (when-let [f (io/file (first arguments))]
    (cond
      (:bytes options) (.length f)
      ;; NOTE: using line-seq might not be the most efficient option, especially for large files
      (:lines options) (count (line-seq (io/reader f)))
      ;; NOTE: we are holding the whole file in memory
      (:words options) (count (-> f slurp (str/split #"\s+"))))))

(def cli-options
  [["-c" "--bytes" "Count bytes in a file"]
   ["-l" "--lines" "Count lines in a file"]
   ["-w" "--words" "Count words in a file"]])

;; https://github.com/clojure/tools.cli
(defn parse-args [args]
  ;; TODO: handle errors - e.g required file arg is missing
  (cli/parse-opts args cli-options))

(defn main [& args]
  (count-file (parse-args args))
  )
