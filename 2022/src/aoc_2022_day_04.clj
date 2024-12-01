(ns aoc-2022-day-04)

(def input (slurp "src/aoc-2022-day-04-input.txt"))
(def assignments (clojure.string/split-lines input))

(defn assignment-contained? [a b]
  (or (clojure.set/subset? a b)
      (clojure.set/subset? b a)))

(def answer-1
  (->> assignments
       (map #(clojure.string/split % #","))
       (map #(map (fn [v] (set (apply range (map + [0 1] (map parse-long (clojure.string/split v #"-")))))) %))
       (map #(apply assignment-contained? %))
       (filter true?)
       (count)))


(def answer-2
  (->> assignments
       (map #(clojure.string/split % #","))
       (map #(map (fn [v] (set (apply range (map + [0 1] (map parse-long (clojure.string/split v #"-")))))) %))
       (map #(apply (comp empty? clojure.set/intersection) %))
       (filter false?)
       (count)))



(comment
  (->> assignments
       (map #(clojure.string/split % #","))
       (map #(map (fn [v] (set (apply range (map + [0 1] (map parse-long (clojure.string/split v #"-")))))) %))
       ;(map first))
       ;(take 4)
       (map #(apply (comp empty? clojure.set/intersection) %)))
       ;(filter true?)
       ;(count))

       ;(apply #(map (fn [v] (set (apply range (map parse-long (clojure.string/split v #"-"))))) %)))
       ;(apply #(map assignment-contained?)))


  (->> assignments
       (take 1)
       (map #(clojure.string/split % #","))
       (map #(map (fn [v] (set (apply range (map + [0 1] (map parse-long (clojure.string/split v #"-")))))) %))
       (map #(apply (comp empty? clojure.set/intersection) %))
       (filter true?)
       (count))

  (assignment-contained?
    (set [6]) (set [4 5 6]))
  (assignment-contained?
    (set [4 5 6]) (set [6]))
  (clojure.set/union (set [1 2 3]) (set [5 6 7])))





