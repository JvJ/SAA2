(ns misc.testapp
  (:use [clojure.set]))

(def board
  "The minesweeper board.
:m -> mine
0..8 -> appropriate integers"
  (atom []))

(def filter-grid
  "The covered/uncovered squares on the board.
:u -> uncovered
:c -> covered
:f -> flagged"
  (atom []))

(def grid-size
  (atom 0))

(def num-mines
  (atom 0))

(def game-status
  "Game status!
:ok -> good to keep playing
:win -> all mines are covered
:lose -> a mine has been uncovered!"
  (atom :ok))


;;;; Util Functions
;;;; ===========================================

(defn assoc-into
  "Given sequences of keys and values, set the values associated with each nested key
to the thingie!"
  ([m kvmap]
    (reduce
      (fn [acc [k v]] (assoc-in acc k v))
      m
      kvmap))
  ([m kcol vcol] (assoc-into m (map vector kcol vcol))))
      
(defn neighbours
  "Gets all eight neighbours of a cell."
  ([[x y] diag?] (neighbours [x y]
                       [Long/MIN_VALUE Long/MIN_VALUE]
                       [Long/MAX_VALUE Long/MAX_VALUE]
                       diag?))
  ([[x y] [max-x max-y] diag?] (neighbours [x y] [0 0] [max-x max-y] diag?))
  ([[x y] [min-x min-y] [max-x max-y] diag?]
    (for [xx [-1 0 1]
          yy [-1 0 1]
          :let [-x (+ x xx)
                -y (+ y yy)]
          :when (and
                  (not (= 0 xx yy))
                  (< -x max-x)
                  (< -y max-y)
                  (>= -x min-x)
                  (>= -y min-y)
                  (or diag? (= xx 0) (= yy 0)))]
      [(+ x xx) (+ y yy)])))


(defn initialize
  [g-size n-mines]
  (let [_ (reset! game-status :ok)
        _ (reset! grid-size g-size)
        _ (reset! num-mines n-mines)
        coords (->
                 (for [x (range g-size)
                     y (range g-size)]
                   [x y])
                 (shuffle))
        mines (set (take n-mines coords))
        _ (reset! board
                  (vec
                    (for [x (range g-size)]
                      (vec
                        (for [y (range g-size)]
                          (if (mines [x y])
                            :m
                            (get
                              (frequencies
                              (map #(contains? mines %)
                                   (neighbours [x y] true)))
                              true
                              0)))))))
        _ (reset! filter-grid
                  (vec (repeat g-size
                               (vec (repeat g-size :c)))))]
    nil))

(defn clear-filter-grid
  []
  (reset! filter-grid
          (vec (repeat @grid-size  (vec (repeat @grid-size :u))))))

(defn board-seen
  []
  (map
    (partial map
             (fn [x y] (cond
                       (= x :u) y
                       :else x)))
    @filter-grid
    @board))
  
(defn flood-fill
  "Give us a grid!  Give us coords!
Give us a predicate that runs on element
[x y] of the grid!
It always goes exactly ONE cell beyond the bounds!
The predicate returns true if if should continue.
Returns a set of coordinates that should be filled!"
  ([grid predk predc [x y]]
    (flood-fill grid predk predc [x y] #{} [(count grid) (count (first grid))]))
  ([grid predk predc [x y] acc [w h]]
    ;;(println "acc: " acc)
    (let [cur (get-in grid [x y])
          default-ret (if (predk cur)
                        (conj acc [x y])
                        acc)]
      (if (predc cur)
        (reduce
          (fn [a el]
            (if (contains? a el)
              a
              (flood-fill grid predc predk el a [w h])))
          (conj acc [x y])
          (difference
            (set (neighbours [x y] [w h] false))
            acc))
        default-ret))))
      

;;;; UI functions
;;;; ===========

(defn uncover
  [[x y]]
  ;;; Swap the game state!
  (let [ff (flood-fill
             @board number?
             #(and (number? %) (zero? %))
             [x y])
        ff (filter
             #(not (= :m (get-in @board %)))
             ff)]
    (swap! filter-grid assoc-into ff (repeat :u)))
  (cond
    (= (get-in @board [x y]) :m) (reset! game-status :lose)
    :else @game-status))
  
(defn flag
  [[x y]]
  (swap! filter-grid
         update-in
         [x y]
         #(cond
            (= % :c) :f
            (= % :f) :c
            :else %))
  (cond
    (every?
      #(and
         (= (get-in @board %) :m)
         (= (get-in @filter-grid %) :f)))
    (reset! game-status :win))
  )
            
(defn display
  []
  (cond
    (= @game-status :ok) (println "OK to go")
    (= @game-status :win) (println "You win!")
    (= @game-status :lose) (println "You lose!"))
  (doseq [row (board-seen)]
    (->>
      (for [el row]
        (cond (= el :c) "# "
              (= el :f) "X "
              (= el :m) "@ "
              (= el 0) ". "
              (integer? el) (str el " ")))
      (apply str)
      (println))))

(defn turn
  [[x y]]
  (uncover [x y])
  (display))
            
            