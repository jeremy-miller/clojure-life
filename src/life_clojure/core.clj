(ns life-clojure.core
  (:require [clojure.core.matrix :refer :all])  ;; TODO only refer to required functions
  (:gen-class))

;; The rules of Conway's Game of Life are as follows:
;; 1. Any live cell with fewer than two live neighbours dies, as if caused by under-population.
;; 2. Any live cell with two or three live neighbours lives on to the next generation.
;; 3. Any live cell with more than three live neighbours dies, as if by over-population.
;; 4. Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.

(def max-rows 4)  ;; 0-indexed
(def max-columns 4)  ;; 0-indexed
(def board [[0 0 0 0 0]
            [0 0 1 0 0]
            [0 0 1 0 0]
            [0 0 1 0 0]
            [0 0 0 0 0]])

(defn- get-living-neighbors
  [row column cell-value board]
  (let [row-start (max (- row 1) 0)
        row-length (min (+ row 1) max-rows)
        column-start (max (- column 1) 0)
        column-length (min (+ column 1) max-columns)]
    (- (esum (submatrix board row-start row-length column-start column-length)) cell-value)))

(defn- evolve
  "Return correct value of cell based on neighbors"
  [[row column] cell-value board]
  (let [live-neighbors (get-living-neighbors row column cell-value board)]
    (if (= cell-value 1)  ;; cell is currently alive
      (cond
        (< live-neighbors 2) 0  ;; rule 1
        (> live-neighbors 3) 0  ;; rule 3
        (or (= live-neighbors 2) (= live-neighbors 3)) 1)  ;; rule 2
      (if (= live-neighbors 3)  ;; rule 4
        1))))

(defn -main
  "Run the game."
  []
  (println "Starting Conway's Game of Life...")
  (pm (emap-indexed #(evolve %1 %2 board) board)))
