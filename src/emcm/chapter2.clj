;; gorilla-repl.fileformat = 1

;; @@
(ns emcm.chapter1
  (:require [clojure.core.matrix.random :refer [sample-uniform]]
            [clojure.core.matrix :as m]
            [clojure.core.matrix.stats :refer [sum mean variance]]
            [gorilla-plot.core :refer [histogram list-plot bar-chart]]))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
;; P2.1
(defn petersburg
  []
  (let [init -5]
    (+ (Math/pow 2 (count (take-while pos? (repeatedly #(rand-int 2)))))
       -1
       init)))
(for [n [100 1000 10000]] (mean (repeatedly n petersburg)))
(histogram (repeatedly 1000 petersburg))
;; @@
;; =>
;;; {"type":"vega","content":{"width":400,"height":247.2187957763672,"padding":{"top":10,"left":55,"bottom":40,"right":10},"data":[{"name":"652eb7b3-a44e-498a-95a5-4daaf95c30f0","values":[{"x":-5.0,"y":0},{"x":88.00000000000001,"y":986.0},{"x":181.00000000000003,"y":6.0},{"x":274.00000000000006,"y":2.0},{"x":367.00000000000006,"y":0.0},{"x":460.00000000000006,"y":0.0},{"x":553.0000000000001,"y":4.0},{"x":646.0000000000001,"y":0.0},{"x":739.0000000000001,"y":0.0},{"x":832.0000000000001,"y":0.0},{"x":925.0000000000001,"y":0.0},{"x":1018.0000000000001,"y":2.0},{"x":1111.0000000000002,"y":0}]}],"marks":[{"type":"line","from":{"data":"652eb7b3-a44e-498a-95a5-4daaf95c30f0"},"properties":{"enter":{"x":{"scale":"x","field":"data.x"},"y":{"scale":"y","field":"data.y"},"interpolate":{"value":"step-before"},"fill":{"value":"steelblue"},"fillOpacity":{"value":0.4},"stroke":{"value":"steelblue"},"strokeWidth":{"value":2},"strokeOpacity":{"value":1}}}}],"scales":[{"name":"x","type":"linear","range":"width","zero":false,"domain":{"data":"652eb7b3-a44e-498a-95a5-4daaf95c30f0","field":"data.x"}},{"name":"y","type":"linear","range":"height","nice":true,"zero":false,"domain":{"data":"652eb7b3-a44e-498a-95a5-4daaf95c30f0","field":"data.y"}}],"axes":[{"type":"x","scale":"x"},{"type":"y","scale":"y"}]},"value":"#gorilla_repl.vega.VegaView{:content {:width 400, :height 247.2188, :padding {:top 10, :left 55, :bottom 40, :right 10}, :data [{:name \"652eb7b3-a44e-498a-95a5-4daaf95c30f0\", :values ({:x -5.0, :y 0} {:x 88.00000000000001, :y 986.0} {:x 181.00000000000003, :y 6.0} {:x 274.00000000000006, :y 2.0} {:x 367.00000000000006, :y 0.0} {:x 460.00000000000006, :y 0.0} {:x 553.0000000000001, :y 4.0} {:x 646.0000000000001, :y 0.0} {:x 739.0000000000001, :y 0.0} {:x 832.0000000000001, :y 0.0} {:x 925.0000000000001, :y 0.0} {:x 1018.0000000000001, :y 2.0} {:x 1111.0000000000002, :y 0})}], :marks [{:type \"line\", :from {:data \"652eb7b3-a44e-498a-95a5-4daaf95c30f0\"}, :properties {:enter {:x {:scale \"x\", :field \"data.x\"}, :y {:scale \"y\", :field \"data.y\"}, :interpolate {:value \"step-before\"}, :fill {:value \"steelblue\"}, :fillOpacity {:value 0.4}, :stroke {:value \"steelblue\"}, :strokeWidth {:value 2}, :strokeOpacity {:value 1}}}}], :scales [{:name \"x\", :type \"linear\", :range \"width\", :zero false, :domain {:data \"652eb7b3-a44e-498a-95a5-4daaf95c30f0\", :field \"data.x\"}} {:name \"y\", :type \"linear\", :range \"height\", :nice true, :zero false, :domain {:data \"652eb7b3-a44e-498a-95a5-4daaf95c30f0\", :field \"data.y\"}}], :axes [{:type \"x\", :scale \"x\"} {:type \"y\", :scale \"y\"}]}}"}
;; <=

;; @@
;; P2.2
(defn paris-salon
  []
  (let [roll #(rand-nth (range 1 7))]
    (count (take-while #(not= % 6) (repeatedly roll)))))

(double (mean (repeatedly 10000 paris-salon)))
(histogram (repeatedly 1000 paris-salon))
;; @@
;; =>
;;; {"type":"vega","content":{"width":400,"height":247.2187957763672,"padding":{"top":10,"left":55,"bottom":40,"right":10},"data":[{"name":"140aa95d-e52b-463d-9497-49ae8d41a7d0","values":[{"x":0.0,"y":0},{"x":3.2727272727272734,"y":520.0},{"x":6.545454545454547,"y":204.0},{"x":9.81818181818182,"y":117.0},{"x":13.090909090909093,"y":73.0},{"x":16.363636363636367,"y":28.0},{"x":19.63636363636364,"y":28.0},{"x":22.909090909090914,"y":14.0},{"x":26.181818181818187,"y":9.0},{"x":29.45454545454546,"y":2.0},{"x":32.727272727272734,"y":2.0},{"x":36.00000000000001,"y":3.0},{"x":39.27272727272728,"y":0}]}],"marks":[{"type":"line","from":{"data":"140aa95d-e52b-463d-9497-49ae8d41a7d0"},"properties":{"enter":{"x":{"scale":"x","field":"data.x"},"y":{"scale":"y","field":"data.y"},"interpolate":{"value":"step-before"},"fill":{"value":"steelblue"},"fillOpacity":{"value":0.4},"stroke":{"value":"steelblue"},"strokeWidth":{"value":2},"strokeOpacity":{"value":1}}}}],"scales":[{"name":"x","type":"linear","range":"width","zero":false,"domain":{"data":"140aa95d-e52b-463d-9497-49ae8d41a7d0","field":"data.x"}},{"name":"y","type":"linear","range":"height","nice":true,"zero":false,"domain":{"data":"140aa95d-e52b-463d-9497-49ae8d41a7d0","field":"data.y"}}],"axes":[{"type":"x","scale":"x"},{"type":"y","scale":"y"}]},"value":"#gorilla_repl.vega.VegaView{:content {:width 400, :height 247.2188, :padding {:top 10, :left 55, :bottom 40, :right 10}, :data [{:name \"140aa95d-e52b-463d-9497-49ae8d41a7d0\", :values ({:x 0.0, :y 0} {:x 3.2727272727272734, :y 520.0} {:x 6.545454545454547, :y 204.0} {:x 9.81818181818182, :y 117.0} {:x 13.090909090909093, :y 73.0} {:x 16.363636363636367, :y 28.0} {:x 19.63636363636364, :y 28.0} {:x 22.909090909090914, :y 14.0} {:x 26.181818181818187, :y 9.0} {:x 29.45454545454546, :y 2.0} {:x 32.727272727272734, :y 2.0} {:x 36.00000000000001, :y 3.0} {:x 39.27272727272728, :y 0})}], :marks [{:type \"line\", :from {:data \"140aa95d-e52b-463d-9497-49ae8d41a7d0\"}, :properties {:enter {:x {:scale \"x\", :field \"data.x\"}, :y {:scale \"y\", :field \"data.y\"}, :interpolate {:value \"step-before\"}, :fill {:value \"steelblue\"}, :fillOpacity {:value 0.4}, :stroke {:value \"steelblue\"}, :strokeWidth {:value 2}, :strokeOpacity {:value 1}}}}], :scales [{:name \"x\", :type \"linear\", :range \"width\", :zero false, :domain {:data \"140aa95d-e52b-463d-9497-49ae8d41a7d0\", :field \"data.x\"}} {:name \"y\", :type \"linear\", :range \"height\", :nice true, :zero false, :domain {:data \"140aa95d-e52b-463d-9497-49ae8d41a7d0\", :field \"data.y\"}}], :axes [{:type \"x\", :scale \"x\"} {:type \"y\", :scale \"y\"}]}}"}
;; <=

;; @@
(double (mean (map #(if (< % 4) 0 1) (repeatedly 10000 paris-salon))))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-double'>0.4829</span>","value":"0.4829"}
;; <=

;; @@
;; P2.3
;; c = 5/7
;; @@

;; @@
;; P2.6
(defn min-distance
  [points]
  (let [get-dist (fn [[x1 y1] [x2 y2]]
                   (Math/sqrt (+ (Math/pow (- x1 x2) 2)
                                 (Math/pow (- y1 y2) 2))))]
    (loop [init-point (first points)
           rest-points (rest points)
           min-dist (Math/sqrt 2)]
      (if (seq rest-points)
        (let [min-dist-new (apply min (map (partial get-dist init-point) rest-points))]
          (recur (first rest-points)
                 (rest rest-points)
                 (min min-dist min-dist-new)))
        min-dist))))

(defn min-dist-expectation
  [N]
  (let [n 1000
        gen-points #(for [_ (range N)] [(rand) (rand)])]
    (double (mean (repeatedly n #(min-distance (gen-points)))))))

(map min-dist-expectation [2 4 8 16 32 64 128])
;; (0.5009540702075905
;;  0.20776249377493647
;;  0.09614956315172914
;;  0.04537395095149332
;;  0.022474945562603807
;;  0.01094621666895177
;;  0.005497748727015809)
;; @@

;; @@
;; P2.9
(defn dist-of-min
  [n]
  (let [gen-min #(apply min (repeatedly n rand))]
    (double (mean (repeatedly 1000 gen-min)))))

(map dist-of-min [10 100 1000])
;; (0.09114434891257989
;;  0.01035934813334652
;;  0.001018004170508814)
;; @@
