(ns aoc2021.day-1
 (:require [clojure.string]))

;; advent-of-code-day-1
(def input (->>
             ;(slurp "/Users/runebrinckmeyer/dev/issuu-garage/bodacious-issuu-editor/frontend/src/font_builder/bie/font_builder/advent-of-code-2021-day-1-input.txt")
             (slurp "advent-of-code-2021-day-1-input.txt")
             (clojure.string/split-lines)
             (map #(Integer. %))))

(def answer-1 (->> input
                (partition 2 1)
                (map (fn [[a b]] (- b a)))
                (filter pos-int?)
                (count)))

(def answer-2 (->> input
                (partition 3 1)
                (map (fn [p3] (apply + p3)))
                (partition 2 1)
                (map (fn [[a b]] (- b a)))
                (filter pos-int?)
                (count)))

(println "Answer-1" answer-1)
(println "Answer-2" answer-2)