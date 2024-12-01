(ns aoc-2022-day-02)

(def input (slurp "src/aoc-2022-day-02-input.txt"))
(def raw-rounds (clojure.string/split-lines input))

;; A = Rock
;; B = Paper
;; C = Scissors

;; X = Rock
;; Y = Paper
;; Z = Scissors
(def rules-score
  {"A X" (+ 1 3)
   "A Y" (+ 2 6)
   "A Z" (+ 3 0)

   "B X" (+ 1 0)
   "B Y" (+ 2 3)
   "B Z" (+ 3 6)

   "C X" (+ 1 6)
   "C Y" (+ 2 0)
   "C Z" (+ 3 3)})

;; X = Loose
;; Y = Draw
;; Z = Win
(def winning-strategy
  {"A X" (+ 3 0)
   "A Y" (+ 1 3)
   "A Z" (+ 2 6)

   "B X" (+ 1 0)
   "B Y" (+ 2 3)
   "B Z" (+ 3 6)

   "C X" (+ 2 0)
   "C Y" (+ 3 3)
   "C Z" (+ 1 6)})

(defn round-score-1 [round]
  (rules-score round))

(defn round-score-2 [round]
  (winning-strategy round))

(def answer-1
  (->> raw-rounds
       (map round-score-1)
       (apply +)))

(def answer-2
  (->> raw-rounds
       (map round-score-2)
       (apply +)))

(comment
  (count raw-rounds)
  (->> raw-rounds
       (map round-score)
       (apply +))
  (round-score (second raw-rounds)))


