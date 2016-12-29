(ns programmingpraxis-clj.mindcipher
  (:require [clojure.spec :as s]
            [clojure.spec.gen :as gen]
            [clojure.spec.test :as stest])
  (:import java.util.Random))

;; we consider Head : True and Tail : False

(defn random-boolean [] (.nextBoolean (new Random)))

(defn flip-until
  "Simulates a series of flips"
  [l]
  (count (take-while #(not= % l) (partition 3 (repeatedly random-boolean)))))

(defn day
  [p]
  (let [l (repeatedly 10000 #(flip-until p))]
    (float (/ (reduce + l) (count l)))))

(def modayFlipNumber (day '(true false true)))

(def tuesdayFlipNumber (day '(true false false)))

;; TODO: create a spec that check that p is a list of booleans
(s/def ::cointoss boolean?)

(s/fdef flip-until
        :args (s/coll-of ::cointoss :kind list? :into ())
        :ret nat-int?)

;; (s/exercise-fn  `flip-until)

;; (stest/check `flip-until)

;; (s/exercise-fn `flip-until)
;; Args need to be a collection of 3 boolean


;; ex2 : find the first year for which the sum of both groups of two digits is equal to the middle two digits.

;; what i want is a fn that i pass an infinite list and it process it untill it
;; finds a value that matches the predicate 

;; (take 5 (drop-while #(<= % 1978) (range)))

(+ (/ 1978 100) (mod 1978 100))

(apply #(= (+ (/ % 100) (mod % 100)) (/ (mod % 1000) 10)) `(1978))

(filter #(= (+ (/ % 100) (mod % 100)) (/ (mod % 1000) 10))
        (drop-while #(<= % 1978) (range)))

(filter #(= (+ (/ % 100) (mod % 100)) (/ (mod % 1000) 10))
        (take-last 2 (take-while #(<= % 2307) (drop-while #(<= % 1978) (range)))))

(defn sumDay
  []
  ())
