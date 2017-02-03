(ns life-clojure.core
  (:require [clojure.core.matrix :as matrix]
            [clojure.string :as string])
  (:gen-class))

(def configuration {"blinker" {:max-rows 5 :max-columns 5 :live-cells [[1 2] [2 2] [3 2]]}
                    "glider" {:max-rows 10 :max-columns 10 :live-cells [[1 2] [2 3] [3 1] [3 2] [3 3]]}
                    "toad" {:max-rows 5 :max-columns 5 :live-cells [[1 2] [1 3] [1 4] [2 1] [2 2] [2 3]]}
                    "pulsar" {:max-rows 16 :max-columns 16 :live-cells [[1 3] [1 4] [1 5] [1 9] [1 10] [1 11] [3 1] [3 6] [3 8] [3 13]
                                                                        [4 1] [4 6] [4 8] [4 13] [5 1] [5 6] [5 8] [5 13] [6 3] [6 4]
                                                                        [6 5] [6 9] [6 10] [6 11] [8 3] [8 4] [8 5] [8 9] [8 10] [8 11]
                                                                        [9 1] [9 6] [9 8] [9 13] [10 1] [10 6] [10 8] [10 13] [11 1]
                                                                        [11 6] [11 8] [11 13] [13 3] [13 4] [13 5] [13 9] [13 10] [13 11]]}
                    "pentadecathlon" {:max-rows 18 :max-columns 11 :live-cells [[4 5] [5 5] [6 4] [6 6] [7 5] [8 5] [9 5] [10 5] [11 4] [11 6] [12 5] [13 5]]}
                    "lightweight-spaceship" {:max-rows 7 :max-columns 20 :live-cells [[1 1] [1 4] [2 5] [3 1] [3 5] [4 2] [4 3] [4 4] [4 5]]}
                    "gosper-glider-gun" {:max-rows 15 :max-columns 38 :live-cells [[1 25] [2 23] [2 25] [3 13] [3 14] [3 21] [3 22] [3 35] [3 36] [4 12] [4 16]
                                                                                   [4 21] [4 22] [4 35] [4 36] [5 1] [5 2] [5 11] [5 17] [5 21] [5 22] [6 1]
                                                                                   [6 2] [6 11] [6 15] [6 17] [6 18] [6 23] [6 25] [7 11] [7 17] [7 25] [8 12]
                                                                                   [8 16] [9 13] [9 14]]}})
(def configuration-name "blinker")
(def max-rows (get-in configuration [configuration-name :max-rows]))
(def max-columns (get-in configuration [configuration-name :max-columns]))

(defn- get-living-neighbors
  "Calculate number of living neighbors of cell in current board."
  [row column cell-value board]
  (let [row-start (max (- row 1) 0)
        row-length (if (or (= row 0) (= row (- max-rows 1))) 2 3)  ; subtract 1 from max-rows because it's 1-indexed
        column-start (max (- column 1) 0)
        column-length (if (or (= column 0) (= column (- max-columns 1))) 2 3)]  ; subtract 1 from max-columns because it's 1-indexed
    (- (matrix/esum (matrix/submatrix board row-start row-length column-start column-length)) cell-value)))

(defn- evolve
  "Return correct value of cell based on neighbors"
  [[row column] cell-value board]
  (let [live-neighbors (get-living-neighbors row column cell-value board)]
    (if (= cell-value 1)  ; cell is currently living
      (cond
        (< live-neighbors 2) 0  ; rule 1
        (> live-neighbors 3) 0  ; rule 3
        (or (= live-neighbors 2) (= live-neighbors 3)) 1)  ; rule 2
      (if (= live-neighbors 3) 1 0))))  ; rule 4

(defn- print-board
  "Print board"
  [board]
  (doseq [row board]
    (println (string/join " " (map #(if (= % 0) "." "O") row))))
  (println))  ; add empty line between boards

(defn- get-initial-board
  "Build and return the initial board"
  [live-cells]
  (let [board (matrix/matrix (make-array Integer/TYPE max-rows max-columns))]  ; build board filled with 0s;
    (reduce (fn [new-board [row column]] (assoc-in new-board [row column] 1)) board live-cells)))  ; set 1 at all locations in 'live-cells'

(defn -main
  "Run the game."
  []
  (let [board (get-initial-board (get-in configuration [configuration-name :live-cells]))]
    (println "Starting Conway's Game of Life...\n")
    (print-board board)
    (print-board (matrix/emap-indexed #(evolve %1 %2 board) board))))
