(ns main
  "Tiny shim for gen-class. Any real logic is in `wc` namespace."
  (:gen-class)
  (:require [wc :as wc]))

(defn -main [& args]
  (wc/main args))
