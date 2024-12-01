(ns aoc-2022-day-05
     (:require [clojure.string :as str]))

(def input (slurp "src/aoc-2022-day-05-input.txt"))
(def day5-parts (clojure.string/split input #"\r?\n\n"))

(def container-stack
     (->> (first day5-parts)
          (clojure.string/split-lines)
          (drop-last)
          (map #(str/replace % #"] " "]"))
          (map #(str/replace % #"    " "[ ]"))
          (map #(str % (apply str (repeat
                                       (/ (- 27 (count %)) 3)
                                       "[ ]"))))
          (map #(map last
                     (re-seq #"(\[(.)\])" %)))
          (#(reduce
              (fn [r l]
                (map conj r l))

              (concat
                [(take 9 (repeat []))]
                %)))
          (map (fn [v] (remove #(= % " ") v)))
          (#(vec (map vec %)))))

(def container-platform (atom container-stack))

(def container-movements
     (->> (second day5-parts)
          (clojure.string/split-lines)
          (map #(map parse-long
                     (rest (re-matches #"move (\d+) from (\d+) to (\d+)" %))))))

(defn move-containers [containers order-fn num from to]
  ;(println "moving" num "from" from "to" to)
  (let [[mv re] (split-at num (nth containers (dec from)))]
    ;(println "mv re" mv re)
    (-> containers
        (assoc (dec from) (vec re))
        (update (dec to) (fn [v]
                           ;(println "mv v" mv v)
                           (apply conj (vec (order-fn mv)) v))))))



(def answer-1
  (->> container-movements
       (reduce #(apply move-containers %1 reverse %2) @container-platform)
       (map first)
       (apply str)))

(def answer-2
  (->> container-movements
       (reduce #(apply move-containers %1 identity %2) @container-platform)
       (map first)
       (apply str)))

(comment
  (->> (first day5-parts)
       (clojure.string/split-lines)
       (drop-last)
       (map #(str/replace % #"] " "]"))
       (map #(str/replace % #"    " "[ ]"))
       (map #(str % (apply str (repeat
                                 (/ (- 27 (count %)) 3)
                                 "[ ]")))))

  (->> (second day5-parts)
       (clojure.string/split-lines)
       (map #(map parse-long
                  (rest (re-matches #"move (\d+) from (\d+) to (\d+)" %)))))

  (map #(map last
             (re-seq #"(\[(.)\])" %)) container-stack)

  (map #(apply move-containers %) container-movements)

  (map #(mapcat (comp vec last)
             (re-seq #"(\[(.)\])" %)) container-stack)

  (->> (take 1 container-movements)
       (reduce #(apply move-containers %1 %2) @container-platform))
       ;(map first)
       ;(apply str))

  (reduce
    (fn [r l]
      (println r)
      (map conj r l))

    (concat
      [(take 9 (repeat []))]
      container-stack))

  (reduce
    (fn [r l]
      (println r)
      (map conj r l))

   (concat
     [(take 9 (repeat []))]
     a)))





