(ns aoc-2022-day-06
  (:require [clojure.string :as str]))

(def input (slurp "src/aoc-2022-day-06-input.txt"))

(defn search-start-marker [s window-size]
  (loop [offset 0]
    (let [marker-window (subs s offset (+ offset window-size))
          distinct-set  (distinct marker-window)
          distinct-len  (count distinct-set)]
      ;(println "john" distinct-len)
      (cond
        (= distinct-len window-size) [marker-window offset]
        :else (recur (inc offset))))))


(def answer-1
  (let [[marker offset] (search-start-marker input 4)]
    (+ 4 offset)))

(def answer-2
  (let [[marker offset] (search-start-marker input 14)]
    (+ 14 offset)))

(comment
  (subs input 0 4)
  (distinct (subs input 0 4)))











