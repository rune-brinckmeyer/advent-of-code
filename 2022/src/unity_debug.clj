(ns unity-debug
  (:require [jsonista.core :as jsin]))

(def logfile-path "/Users/runeb/Library/Application Support/UnityHub/logs/info-log.json")

(def unity-log
  (with-open [logfile (clojure.java.io/reader logfile-path)]
    (doall (map #(jsin/read-value % jsin/keyword-keys-object-mapper)
                (line-seq logfile)))))

(comment
  (distinct (map
              :level
              unity-log))
  (filter #(= (:level %) "error")
          unity-log)
  (map :message unity-log))