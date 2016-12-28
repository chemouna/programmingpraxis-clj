(ns programmingpraxis-clj.mindcipher
  (:require [uncomplicate.fluokitten.core :as flc :refer :all]
            [clojure.string :as s])
  (:import java.util.Random))

(defn random-boolean [] (.nextBoolean (new Random)))

(defn flipUntil
  "Simulates a series of flips"
  [l]
  (count (take-while #(not (= % l)) (partition 3 (repeatedly random-boolean)))))

(def l '(true, false, true))
(count (take-while #(not (= % l)) (partition 3 (repeatedly random-boolean))))
(flipUntil l)

(reduce + (repeat 10 (flipUntil '(true true false))))

(count (repeat 10 (flipUntil '(true true false))))

(def l2 (repeat 10 (flipUntil '(true true false))))
(/ (reduce + l2) (count l2))


(defn day
  [p]
  )

;; TODO: create a spec that check that p is a list of booleans



