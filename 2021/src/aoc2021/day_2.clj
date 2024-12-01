(ns aoc2021.day-2)

(def input (->>
             (slurp "advent-of-code-2021-day-2-input.txt")
             (clojure.string/split-lines)))

(defn calc-length [input]
  (reduce
   (fn [m v]
     (let [[keyw amount] ((fn [[k a]] [(keyword k) (Integer. a)]) (clojure.string/split v #" "))]
       (condp = keyw
         :forward (update-in m [0] + amount)
         :up (update-in m [1] - amount)
         :down (update-in m [1] + amount))))
   [0 0]
   input))

(defn calc-length-with-aim [input]
  (reduce
    (fn [m v]
      (println "AA" m v)
      (let [[keyw amount] ((fn [[k a]] [(keyword k) (Integer. a)]) (clojure.string/split v #" "))]
        (condp = keyw
          :forward (-> m
                     (update-in [0] + amount)
                     (update-in [1] + (* (last m) amount)))
          :up (-> m
                ;(update-in [1] - amount)
                (update-in [2] - amount))
          :down (-> m
                  ;(update-in [1] + amount)
                  (update-in [2] + amount)))))
    [0 0 0]
    input))

(def answer-1 (apply * (calc-length input)))
(def answer-2 (apply * (take 2 (calc-length-with-aim input))))
(comment
  (calc-length-with-aim input))


