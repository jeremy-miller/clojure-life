(ns life-clojure.core
  (:require [clojure.core.matrix :refer :all])  ;; TODO only refer to required functions
  (:gen-class))

(def max-rows 5)
(def max-columns 5)
(def board [[0 0 0 0 0]
            [0 0 1 0 0]
            [0 0 1 0 0]
            [0 0 1 0 0]
            [0 0 0 0 0]])

(defn- get-living-neighbors
  [row column cell-value board]
  (let [row-start (max (- row 1) 0)
        row-length (if (or (= row 0) (= row (- max-rows 1))) 2 3)  ;; subtract 1 from max-rows because it's 1-indexed
        column-start (max (- column 1) 0)
        column-length (if (or (= column 0) (= column (- max-columns 1))) 2 3)]  ;; subtract 1 from max-columns because it's 1-indexed
    (- (esum (submatrix board row-start row-length column-start column-length)) cell-value)))

(defn- evolve
  "Return correct value of cell based on neighbors"
  [[row column] cell-value board]
  (let [live-neighbors (get-living-neighbors row column cell-value board)]
    (if (= cell-value 1)  ;; cell is currently living
      (cond
        (< live-neighbors 2) 0  ;; rule 1
        (> live-neighbors 3) 0  ;; rule 3
        (or (= live-neighbors 2) (= live-neighbors 3)) 1)  ;; rule 2
      (if (= live-neighbors 3) 1 0))))  ;; rule 4

(defn -main
  "Run the game."
  []
  (println "Starting Conway's Game of Life...\n")
  (pm board)
  (println "")
  (pm (emap-indexed #(evolve %1 %2 board) board)))
