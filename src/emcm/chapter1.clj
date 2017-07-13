(ns emcm.chapter1
  (:require [clojure.core.matrix.random :refer [sample-uniform]]
            [clojure.core.matrix :as m]))

(defn buffon-needle-problem
  []
  (let [N 100000
        hit? (fn []
               (let [x (rand)
                     theta (rand (* Math/PI 0.5))]
                 (if (<= x (* 0.5 (Math/sin theta)))
                   1
                   0)))
        n-hits (apply + (repeatedly N hit?))]
    (double (/ N n-hits))))
