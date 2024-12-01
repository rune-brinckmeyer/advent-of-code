(ns aoc-2022-day-07
  (:require [clojure.string :as str]
            [com.rpl.specter :as spc]))

(def input (slurp "src/aoc-2022-day-07-input.txt"))
(def commands (str/split-lines input))
(def q1-limit 100000)

(defn parse-input [cmd-list]
  (reduce (fn [mm line]
            (let [cd        (second (re-find #"\$ cd (.+)" line))
                  [_ dir] (re-find #"dir (.+)" line)
                  [_ filesize filename] (re-find #"(\d+) (.+)" line)
                  file-path (apply conj [:mount] (:current-path mm))]
              ;(println "dir" dir)
              ;(println "mm" mm)
              (cond
                (= line "$ cd /") (assoc mm :current-path [])
                (= line "$ cd ..") (update mm :current-path pop)
                (some? cd) (update mm :current-path conj cd)
                (some? dir) (assoc-in mm (conj file-path dir) {:size 0 :files []})
                (some? filesize) (do
                                    ;(println "file" filename filesize)
                                   ;(println "dd" file-path)
                                   (-> mm
                                       (update-in (conj file-path :files) conj [(parse-long filesize) filename])
                                       (
                                        (fn [mmm]
                                          (let [fsize (parse-long filesize)]
                                            (loop [mmmm mmm
                                                   path file-path]
                                              (if (not-empty path)
                                                (recur (update-in mmmm (conj path :size) + fsize)
                                                       (pop path))
                                                mmmm)))))))
                                       ;(update-in (conj file-path :size) + (parse-long filesize))))

                :else mm)))
          {:current-path []
           :mount        {:size 0}}
          cmd-list))

(def disk1 (parse-input commands))

(defn size-and-subdirs [dir]
  [(:size dir) (map #(get dir %) (filter string? (keys dir)))])

(defn size-of-dirs[dir size-limit]
  (loop [total-size 0
         subdirs [dir]]
    ;(println "dir" subdirs)
    (if (not-empty subdirs)
      (let [[size new-dirs] (size-and-subdirs (first subdirs))]
        (if (< size size-limit)
          (recur (+ total-size size) (apply conj (rest subdirs) new-dirs))
          (recur total-size (apply conj (rest subdirs) new-dirs))))
      total-size)))

(def answer-1
  (size-of-dirs (:mount disk1) 100000))

;(def answer-2
;  (let [[marker offset] (search-start-marker input 14)]
;    (+ 14 offset)))


(comment
  (parse-input (take 16 commands))
  (reduce (fn [mm line]
            (let [cd        (second (re-find #"\$ cd (.+)" line))
                  [_ dir] (re-find #"dir (.+)" line)
                  [_ filesize filename] (re-find #"(\d+) (.+)" line)
                  file-path (apply conj [:mount] (:current-path mm))]
              ;(println "dir" dir)
              ;(println "mm" mm)
              (cond
                (= line "$ cd /") (assoc mm :current-path [])
                (= line "$ cd ..") (update mm :current-path pop)
                (some? cd) (update mm :current-path conj cd)
                (some? dir) (assoc-in mm (conj file-path dir) {:size 0 :files []})
                (some? filesize) (do
                                   ;(println "file" filename filesize)
                                   ;(println "dd" file-path)
                                   (-> mm
                                       (update-in (conj file-path :files) conj [(parse-long filesize) filename])
                                       (
                                        (fn [mmm]
                                          (let [fsize (parse-long filesize)]
                                            (loop [mmmm mmm
                                                   path file-path]
                                              (if (not-empty path)
                                                (recur (update-in mmmm (conj path :size) + fsize)
                                                       (pop path))
                                                mmmm)))))))
                                       ;(update-in (conj file-path :size) + (parse-long filesize))))

                :else mm)))
          {:current-path []
           :mount        {:size 0}}
          ;commands)
          (take 16 commands))

  (subs input 0 4)
  (distinct (subs input 0 4)))










