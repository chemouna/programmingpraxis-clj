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
(s/def ::cointoss (s/or :t true :f false))

(s/fdef flip-until
        :args (s/coll-of ::cointoss :count 3 :kind boolean?)
        :ret nat-int?)

;; (s/exercise-fn  `flip-until)

(stest/check `flip-until)

;; (s/exercise-fn `flip-until)

 ;; Args need to be a collection of 3 boolean
