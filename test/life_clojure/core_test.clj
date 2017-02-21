(ns life-clojure.core-test
  (:require [clojure.test :refer :all]
            [life-clojure.core :as core :refer :all]))

(deftest test-get-living-neighbors
  (let [board [[0 0 0 0 0] [0 0 1 0 0] [0 0 1 0 0] [0 0 1 0 0] [0 0 0 0 0]]]
    (is (zero? (#'core/get-living-neighbors 0 0 0 board)) "[0 0] failed")
    (is (= 1 (#'core/get-living-neighbors 0 1 0 board)) "[0 1] failed")
    (is (= 1 (#'core/get-living-neighbors 0 2 0 board)) "[0 2] failed")
    (is (= 1 (#'core/get-living-neighbors 0 3 0 board)) "[0 3] failed")
    (is (zero? (#'core/get-living-neighbors 0 4 0 board)) "[0 4] failed")
    (is (zero? (#'core/get-living-neighbors 1 0 0 board)) "[1 0] failed")
    (is (= 2 (#'core/get-living-neighbors 1 1 0 board)) "[1 1] failed")
    (is (= 2 (#'core/get-living-neighbors 1 2 0 board)) "[1 2] failed")
    (is (= 2 (#'core/get-living-neighbors 1 3 0 board)) "[1 3] failed")
    (is (zero? (#'core/get-living-neighbors 1 4 0 board)) "[1 4] failed")
    (is (zero? (#'core/get-living-neighbors 2 0 0 board)) "[2 0] failed")
    (is (= 3 (#'core/get-living-neighbors 2 1 0 board)) "[2 1] failed")
    (is (= 3 (#'core/get-living-neighbors 2 2 0 board)) "[2 2] failed")
    (is (= 3 (#'core/get-living-neighbors 2 3 0 board)) "[2 3] failed")
    (is (zero? (#'core/get-living-neighbors 2 4 0 board)) "[2 4] failed")
    (is (zero? (#'core/get-living-neighbors 3 0 0 board)) "[3 0] failed")
    (is (= 2 (#'core/get-living-neighbors 3 1 0 board)) "[3 1] failed")
    (is (= 2 (#'core/get-living-neighbors 3 2 0 board)) "[3 2] failed")
    (is (= 2 (#'core/get-living-neighbors 3 3 0 board)) "[3 3] failed")
    (is (zero? (#'core/get-living-neighbors 3 4 0 board)) "[3 4] failed")
    (is (zero? (#'core/get-living-neighbors 4 0 0 board)) "[4 0] failed")
    (is (= 1 (#'core/get-living-neighbors 4 1 0 board)) "[4 1] failed")
    (is (= 1 (#'core/get-living-neighbors 4 2 0 board)) "[4 2] failed")
    (is (= 1 (#'core/get-living-neighbors 4 3 0 board)) "[4 3] failed")
    (is (zero? (#'core/get-living-neighbors 4 4 0 board)) "[4 4] failed")))

(deftest test-evolve
  (testing "Rule 1"
    (let [row 0
          column 0
          cell-value 1
          board [[1 0 0 0 0] [0 0 0 0 0] [0 0 0 0 0] [0 0 0 0 0] [0 0 0 0 0]]]
      (is (zero? (#'core/evolve [row column] cell-value board)))))
  (testing "Rule 2"
    (let [row-2 0
          column-2 0
          row-3 4
          column-3 4
          cell-value 1
          board [[1 1 0 0 0] [1 0 0 0 0] [0 0 0 0 0] [0 0 0 1 1] [0 0 0 1 1]]]
      (testing " - two living neighbors"
        (is (= 1 (#'core/evolve [row-2 column-2] cell-value board))))
      (testing " - three living neighbors"
        (is (= 1 (#'core/evolve [row-3 column-3] cell-value board))))))
  (testing "Rule 3"
    (let [row 1
          column 1
          cell-value 0
          overpopulated-board [[1 1 1 0 0] [1 1 1 0 0] [1 1 1 0 0] [0 0 0 0 0] [0 0 0 0 0]]]
      (is (zero? (#'core/evolve [row column] cell-value overpopulated-board)))))
  (testing "Rule 4"
    (let [row-1 0
          column-1 4
          row-3 0
          column-3 0
          row-5 4
          column-5 3
          cell-value 0
          board [[0 1 0 1 0] [1 1 0 0 0] [0 0 0 0 0] [0 0 1 1 1] [0 0 1 0 1]]]
      (testing " - three living neighbors"
        (is (= 1 (#'core/evolve [row-3 column-3] cell-value board))))
      (testing " - less than three living neighbors"
        (is (zero? (#'core/evolve [row-1 column-1] cell-value board))))
      (testing " - more than three living neighbors"
        (is (zero? (#'core/evolve [row-5 column-5] cell-value board)))))))

(deftest test-print-board
  (let [board [[0 0 0 0 0] [0 0 1 0 0] [0 0 1 0 0] [0 0 1 0 0] [0 0 0 0 0]]
        output ". . . . .\n. . O . .\n. . O . .\n. . O . .\n. . . . .\n\n"]
    (is (= (with-out-str (#'core/print-board board)) output))))

(deftest test-get-initial-board
  (let [live-cells [[1 2] [2 2] [3 2]]
        board [[0 0 0 0 0] [0 0 1 0 0] [0 0 1 0 0] [0 0 1 0 0] [0 0 0 0 0]]]
    (is (= (#'core/get-initial-board live-cells) board))))

(deftest test-main
  (let [output (str "Starting Conway's Game of Life...\n"
                    "\n. . . . .\n. . O . .\n. . O . .\n. . O . .\n. . . . .\n"
                    "\n. . . . .\n. . . . .\n. O O O .\n. . . . .\n. . . . .\n"
                    "\n. . . . .\n. . O . .\n. . O . .\n. . O . .\n. . . . .\n"
                    "\n. . . . .\n. . . . .\n. O O O .\n. . . . .\n. . . . .\n"
                    "\n. . . . .\n. . O . .\n. . O . .\n. . O . .\n. . . . .\n"
                    "\n. . . . .\n. . . . .\n. O O O .\n. . . . .\n. . . . .\n"
                    "\n. . . . .\n. . O . .\n. . O . .\n. . O . .\n. . . . .\n"
                    "\nThanks for playing!\n")]
    (is (= (with-out-str (-main)) output))))
