(ns aoc)

(def day-1-q-1-example "resources/aoc-day-1-test-1.txt")
(def day-1-q-1-input "resources/aoc-day-1-q-1.txt")

(defn read-and-split-numbers [input-file]
  (with-open [rdr (clojure.java.io/reader input-file)]
    (let [two-number-lines (map
                             (fn [line]
                               (->> line
                                    (re-seq #"(\d+)\s+(\d+)")
                                    (first)
                                    (rest)
                                    (map parse-long)))

                             (doall (line-seq rdr)))
          split-numbers    (juxt #(take-nth 2 %) #(take-nth 2 (rest %)))
          two-lists        (->> (flatten two-number-lines)
                                (split-numbers))]           ;; split lists
      two-lists)))

(defn question-1 [input-file]
  (let [two-lists      (read-and-split-numbers input-file)
        total-distance (->> two-lists
                            (map sort)                        ;; sorted splitted lists
                            (apply map -)                     ;; find difference
                            (map abs)                         ;; abs to find distance
                            (apply +))]                       ;; add up distances])
    total-distance))

(defn question-2 [input-file]
  (let [two-lists     (read-and-split-numbers input-file)
        freq          (frequencies (second two-lists))
        freq-adjusted (->> (map
                             #(* % (get freq % 0))
                             (first two-lists))
                          (apply +))]
    freq-adjusted))

(comment
  (question-1 day-1-q-1-input)
  (question-1 day-1-q-1-example)

  (question-2 day-1-q-1-input)
  (question-2 day-1-q-1-example)

  (read-and-split-numbers day-1-q-1-example)

  (line-seq example)
  (+ 1 2))