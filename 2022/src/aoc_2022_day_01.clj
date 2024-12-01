(ns aoc-2022-day-01)

(def input (slurp "src/aoc-2022-day-01-input.txt"))
(def ii (clojure.string/split-lines input))

(defn list-of-eleves-by-total [ii]
  (->> ii
       (partition-by #(= % ""))
       ;(take 3)
       (map #(map parse-long %))
       (map #(apply + %))
       (remove nil?)))

(def answer-1
  (->>
    (list-of-eleves-by-total ii)
    (apply max)))

(def answer-2
  (->>
    (sort (list-of-eleves-by-total ii))
    (take-last 3)
    (apply +)))


(comment
  (count ii)
  (->> ii
       (partition-by #(= % ""))
       ;(take 3)
       (map #(map parse-long %))
       (map #(apply + %))
       (remove nil?)
       (apply max)))


