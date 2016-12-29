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

;; My steps to finding the solution
(int (+ (/ 1978 100) (mod 1978 100)))

(int (/ (mod 1978 1000) 10))

(apply #(= (int (+ (/ % 100) (mod % 100))) (int (/ (mod % 1000) 10))) `(1978))

(filter #(= (int (+ (/ % 100) (mod % 100))) (int (/ (mod % 1000) 10)))
        (drop-while #(<= % 1978) (range)))

(filter #(= (int (+ (/ % 100) (mod % 100))) (int (/ (mod % 1000) 10)))
        (take-last 2 (take-while #(<= % 2307) (drop-while #(<= % 1978) (range)))))


(+ (last
  (take-while #(not= (int (+ (/ % 100) (mod % 100))) (int (/ (mod % 1000) 10)))
              (drop-while #(<= % 1978) (range)))) 1)


;; first attempt
(defn sum-day
  []
  (+ (last
      (take-while #(not= (int (+ (/ % 100) (mod % 100))) (int (/ (mod % 1000) 10)))
                  (drop-while #(<= % 1978) (range)))) 1))

(sum-day)

;; make it better #2
(defn sum-day-2
  []
  (inc (last
        (take-while #(not= (int (+ (/ % 100) (mod % 100))) (int (/ (mod % 1000) 10)))
                    (drop-while #(<= % 1978) (range))))))

