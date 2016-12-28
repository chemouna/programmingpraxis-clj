(ns programmingpraxis-clj.mindcipher
  (:require [uncomplicate.fluokitten.core :as flc :refer :all]
            [clojure.string :as s])
  (:import java.util.Random))

;; we consider Head : True and Tail : False

(defn random-boolean [] (.nextBoolean (new Random)))

(defn flipUntil
  "Simulates a series of flips"
  [l]
  (count (take-while #(not (= % l)) (partition 3 (repeatedly random-boolean)))))

(defn day
  [p]
  (let [l (repeatedly 10000 #(flipUntil p))]
    (float (/ (reduce + l) (count l)))))

(def modayFlipNumber (day '(true false true)))

(def tuesdayFlipNumber (day '(true false false)))

;; TODO: create a spec that check that p is a list of booleans



