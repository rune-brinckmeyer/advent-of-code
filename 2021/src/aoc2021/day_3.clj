(ns aoc2021.day-3)

(def input (->>
             (slurp "advent-of-code-2021-day-3-input.txt")
             (clojure.string/split-lines)))

(defn decompose-line [line]
  (vec (map
         #(condp = %
            "0" -1
            "1" 1)
         (clojure.string/split line #""))))

(defn transform-to-gamma [linesum]
  (let [dims (clojure.string/join
               (map
                 #(cond
                    (< % 0) "0"
                    :else "1")
                 linesum))]
    (BigInteger. dims 2)))

(defn transform-to-epsilon [linesum]
  (let [dims (clojure.string/join
               (map
                 #(cond
                    (< % 0) "1"
                    :else "0")
                 linesum))]
    (BigInteger. dims 2)))


(def answer-1
  (let [linesum (->> input
                     (map decompose-line)
                     (apply map +))
        gamma   (transform-to-gamma linesum)
        epsilon (transform-to-epsilon linesum)
        ge      (* gamma epsilon)]
    (println linesum)
    (println "gamma" gamma "epsilon" epsilon)
    (println "g*e" ge)
    ge))

(def answer-2
  (let [linesum (->> input
                     (map decompose-line)
                     (apply map +))
        kk      (filter (fn [i]
                          (let [di (decompose-line i)
                                m (map-indexed (fn [idx v]
                                                 (cond
                                                   (< v 0) "1"
                                                   :else "0")) di)]
                            ())) input)]))


(comment
  (map-indexed
    (fn [idx v]
      (println "--" v)
      (cond
        (< v 0) "1"
        :else "0"))
    (map decompose-line (take 5 input)))

  (map
    #(if (< % 0) "0" "1")
    linesum)

  (count input)
  (first input)

  (def ti (map decompose-line input))
  (def ans-1 (apply map + ti))
  (transform-to-gamma ans-1)
  (reduce
    (fn [a v]
      (map + a (decompose-line v)))
    input))