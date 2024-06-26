(ns wc
  "The wc tool: https://codingchallenges.fyi/challenges/challenge-wc/"
  (:require [clojure.tools.cli :as cli]
            [clojure.java.io :as io]))

(defn count-file
  [{:keys [arguments options]}]
  (when-let [f (io/file (first arguments))]
    (cond
      (:bytes options) (.length f))))

(def cli-options
  [["-c" "--bytes" "Count number of bytes in the file"
    :default true]])

;; https://github.com/clojure/tools.cli
(defn parse-args [args]
  ;; TODO: handle errors - e.g required file arg is missing
  (cli/parse-opts args cli-options)
  
  )

(defn main [& args]
  (count-file (parse-args args))
  )
