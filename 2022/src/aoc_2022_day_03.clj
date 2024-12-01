(ns aoc-2022-day-03)

(def input (slurp "src/aoc-2022-day-03-input.txt"))
(def rucksacks (clojure.string/split-lines input))

(def item-priority
  (into (sorted-map)
        (zipmap (apply conj
                       (map char (range (int \A) (+ 1 (int \Z))))
                       (map char (range (int \a) (+ 1 (int \z)))))
                (apply conj (range 27 54) (range 1 27)))))

(defn contents [rucksack]
  (let [midpoint (/ (count rucksack) 2)]
    (map #(apply str %) (partition midpoint rucksack))))

(defn compare-compartments [c1 c2]
  (let [v1 (frequencies c1)
        v2 (frequencies c2)]
    (clojure.set/intersection (set (keys v1)) (set (keys v2)))))


(def answer-1
  (->> rucksacks
       (map #(apply compare-compartments (contents %)))
       (map first)
       (map item-priority)
       (apply +)))

(def answer-2
  (->> rucksacks
    (map #(set %))
    (partition 3)
    (map #(apply clojure.set/intersection %))
    (map first)
    (map item-priority)
    (apply +)))





(comment
   item-priority
   (contents "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn")
   (apply compare-compartments (contents "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn"))
   (apply compare-compartments (contents "ttgJtRGJQctTZtZT"))
   (def sample "vJrwpWtwJgWrhcsFMMfFFhFp\njqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL\nPmmdzqPrVvPwwTWBwg\nwMqvLMZHhHMvwLHjbvcjnnSBnvTQFn\nttgJtRGJQctTZtZT\nCrZsJsPPZsGzwwsLwLmpwMDw")
   (->> sample
        (clojure.string/split-lines)
        (map #(apply compare-compartments (contents %)))
        (map first)
        (map item-priority)
        (apply +))

   (->> sample
        (clojure.string/split-lines)
        (map #(apply compare-compartments (contents %)))
        (map first))
        ;(map item-priority)
        ;(apply +))

   (->> sample
        (clojure.string/split-lines)
        (map #(set %))
        (partition 3)
        (map #(apply clojure.set/intersection %))
        (map first)
        (map item-priority)
        (apply +))


   (contents (first rucksacs)))



