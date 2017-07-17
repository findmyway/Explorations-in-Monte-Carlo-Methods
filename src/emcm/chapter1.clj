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
(defn buffon-needle-problem
  [N]
  (let [hit? (fn []
               (let [x (rand)
                     theta (rand (* Math/PI 0.5))]
                 (if (<= x (* 0.5 (Math/sin theta)))
                   1
                   0)))
        n-hits (sum (repeatedly N hit?))]
    (double (/ N n-hits))))

(let [r 2000
      N 100
      samples (take r (repeatedly #(buffon-needle-problem N)))]
  (histogram samples :plot-range [[1.0 5.0] :all]))
;; @@
;; =>
;;; {"type":"vega","content":{"width":400,"height":247.2187957763672,"padding":{"top":10,"left":55,"bottom":40,"right":10},"data":[{"name":"4144621b-b439-4e5b-b3c5-f4fc483df43d","values":[{"x":1.0,"y":0},{"x":1.3333333333333335,"y":0.0},{"x":1.666666666666667,"y":0.0},{"x":2.0000000000000004,"y":1.0},{"x":2.333333333333334,"y":20.0},{"x":2.6666666666666674,"y":211.0},{"x":3.000000000000001,"y":516.0},{"x":3.3333333333333344,"y":671.0},{"x":3.666666666666668,"y":250.0},{"x":4.000000000000001,"y":229.0},{"x":4.333333333333334,"y":40.0},{"x":4.666666666666667,"y":45.0},{"x":5.0,"y":10.0},{"x":5.333333333333333,"y":6.0},{"x":5.666666666666666,"y":0}]}],"marks":[{"type":"line","from":{"data":"4144621b-b439-4e5b-b3c5-f4fc483df43d"},"properties":{"enter":{"x":{"scale":"x","field":"data.x"},"y":{"scale":"y","field":"data.y"},"interpolate":{"value":"step-before"},"fill":{"value":"steelblue"},"fillOpacity":{"value":0.4},"stroke":{"value":"steelblue"},"strokeWidth":{"value":2},"strokeOpacity":{"value":1}}}}],"scales":[{"name":"x","type":"linear","range":"width","zero":false,"domain":[1.0,5.0]},{"name":"y","type":"linear","range":"height","nice":true,"zero":false,"domain":{"data":"4144621b-b439-4e5b-b3c5-f4fc483df43d","field":"data.y"}}],"axes":[{"type":"x","scale":"x"},{"type":"y","scale":"y"}]},"value":"#gorilla_repl.vega.VegaView{:content {:width 400, :height 247.2188, :padding {:top 10, :left 55, :bottom 40, :right 10}, :data [{:name \"4144621b-b439-4e5b-b3c5-f4fc483df43d\", :values ({:x 1.0, :y 0} {:x 1.3333333333333335, :y 0.0} {:x 1.666666666666667, :y 0.0} {:x 2.0000000000000004, :y 1.0} {:x 2.333333333333334, :y 20.0} {:x 2.6666666666666674, :y 211.0} {:x 3.000000000000001, :y 516.0} {:x 3.3333333333333344, :y 671.0} {:x 3.666666666666668, :y 250.0} {:x 4.000000000000001, :y 229.0} {:x 4.333333333333334, :y 40.0} {:x 4.666666666666667, :y 45.0} {:x 5.0, :y 10.0} {:x 5.333333333333333, :y 6.0} {:x 5.666666666666666, :y 0})}], :marks [{:type \"line\", :from {:data \"4144621b-b439-4e5b-b3c5-f4fc483df43d\"}, :properties {:enter {:x {:scale \"x\", :field \"data.x\"}, :y {:scale \"y\", :field \"data.y\"}, :interpolate {:value \"step-before\"}, :fill {:value \"steelblue\"}, :fillOpacity {:value 0.4}, :stroke {:value \"steelblue\"}, :strokeWidth {:value 2}, :strokeOpacity {:value 1}}}}], :scales [{:name \"x\", :type \"linear\", :range \"width\", :zero false, :domain [1.0 5.0]} {:name \"y\", :type \"linear\", :range \"height\", :nice true, :zero false, :domain {:data \"4144621b-b439-4e5b-b3c5-f4fc483df43d\", :field \"data.y\"}}], :axes [{:type \"x\", :scale \"x\"} {:type \"y\", :scale \"y\"}]}}"}
;; <=

;; @@
(defn gamble
  []
  (let [win? #(if (< (rand) 0.5) -1 1)
        start 100]
    (take-while #(> % 0) (reductions + start (repeatedly win?)))))

;; only take every 50th point here to reduce the size of fig
(list-plot (take-nth 50 (gamble)) :joined true)
;; @@
;; =>
;;; {"type":"vega","content":{"width":400,"height":247.2187957763672,"padding":{"top":10,"left":55,"bottom":40,"right":10},"data":[{"name":"c7db8098-c687-47a5-8d58-ad7bf373d0cc","values":[{"x":0,"y":100},{"x":1,"y":106},{"x":2,"y":110},{"x":3,"y":118},{"x":4,"y":112},{"x":5,"y":104},{"x":6,"y":96},{"x":7,"y":98},{"x":8,"y":94},{"x":9,"y":100},{"x":10,"y":100},{"x":11,"y":100},{"x":12,"y":106},{"x":13,"y":112},{"x":14,"y":106},{"x":15,"y":104},{"x":16,"y":106},{"x":17,"y":104},{"x":18,"y":100},{"x":19,"y":98},{"x":20,"y":98},{"x":21,"y":100},{"x":22,"y":100},{"x":23,"y":90},{"x":24,"y":84},{"x":25,"y":82},{"x":26,"y":90},{"x":27,"y":94},{"x":28,"y":86},{"x":29,"y":94},{"x":30,"y":88},{"x":31,"y":88},{"x":32,"y":96},{"x":33,"y":86},{"x":34,"y":92},{"x":35,"y":88},{"x":36,"y":88},{"x":37,"y":88},{"x":38,"y":70},{"x":39,"y":74},{"x":40,"y":68},{"x":41,"y":62},{"x":42,"y":66},{"x":43,"y":76},{"x":44,"y":72},{"x":45,"y":68},{"x":46,"y":60},{"x":47,"y":64},{"x":48,"y":64},{"x":49,"y":66},{"x":50,"y":56},{"x":51,"y":58},{"x":52,"y":62},{"x":53,"y":64},{"x":54,"y":72},{"x":55,"y":72},{"x":56,"y":80},{"x":57,"y":76},{"x":58,"y":80},{"x":59,"y":94},{"x":60,"y":76},{"x":61,"y":68},{"x":62,"y":66},{"x":63,"y":74},{"x":64,"y":72},{"x":65,"y":70},{"x":66,"y":62},{"x":67,"y":70},{"x":68,"y":78},{"x":69,"y":76},{"x":70,"y":78},{"x":71,"y":82},{"x":72,"y":78},{"x":73,"y":78},{"x":74,"y":76},{"x":75,"y":76},{"x":76,"y":66},{"x":77,"y":70},{"x":78,"y":82},{"x":79,"y":78},{"x":80,"y":78},{"x":81,"y":82},{"x":82,"y":80},{"x":83,"y":82},{"x":84,"y":82},{"x":85,"y":90},{"x":86,"y":98},{"x":87,"y":106},{"x":88,"y":90},{"x":89,"y":86},{"x":90,"y":100},{"x":91,"y":88},{"x":92,"y":86},{"x":93,"y":76},{"x":94,"y":74},{"x":95,"y":64},{"x":96,"y":54},{"x":97,"y":56},{"x":98,"y":44},{"x":99,"y":50},{"x":100,"y":56},{"x":101,"y":54},{"x":102,"y":48},{"x":103,"y":42},{"x":104,"y":40},{"x":105,"y":42},{"x":106,"y":54},{"x":107,"y":58},{"x":108,"y":52},{"x":109,"y":46},{"x":110,"y":38},{"x":111,"y":60},{"x":112,"y":68},{"x":113,"y":68},{"x":114,"y":68},{"x":115,"y":68},{"x":116,"y":64},{"x":117,"y":64},{"x":118,"y":60},{"x":119,"y":66},{"x":120,"y":56},{"x":121,"y":64},{"x":122,"y":60},{"x":123,"y":56},{"x":124,"y":54},{"x":125,"y":60},{"x":126,"y":52},{"x":127,"y":48},{"x":128,"y":38},{"x":129,"y":44},{"x":130,"y":44},{"x":131,"y":40},{"x":132,"y":48},{"x":133,"y":62},{"x":134,"y":60},{"x":135,"y":62},{"x":136,"y":56},{"x":137,"y":48},{"x":138,"y":40},{"x":139,"y":34},{"x":140,"y":28},{"x":141,"y":12}]}],"marks":[{"type":"line","from":{"data":"c7db8098-c687-47a5-8d58-ad7bf373d0cc"},"properties":{"enter":{"x":{"scale":"x","field":"data.x"},"y":{"scale":"y","field":"data.y"},"stroke":{"value":"#FF29D2"},"strokeWidth":{"value":2},"strokeOpacity":{"value":1}}}}],"scales":[{"name":"x","type":"linear","range":"width","zero":false,"domain":{"data":"c7db8098-c687-47a5-8d58-ad7bf373d0cc","field":"data.x"}},{"name":"y","type":"linear","range":"height","nice":true,"zero":false,"domain":{"data":"c7db8098-c687-47a5-8d58-ad7bf373d0cc","field":"data.y"}}],"axes":[{"type":"x","scale":"x"},{"type":"y","scale":"y"}]},"value":"#gorilla_repl.vega.VegaView{:content {:width 400, :height 247.2188, :padding {:top 10, :left 55, :bottom 40, :right 10}, :data [{:name \"c7db8098-c687-47a5-8d58-ad7bf373d0cc\", :values ({:x 0, :y 100} {:x 1, :y 106} {:x 2, :y 110} {:x 3, :y 118} {:x 4, :y 112} {:x 5, :y 104} {:x 6, :y 96} {:x 7, :y 98} {:x 8, :y 94} {:x 9, :y 100} {:x 10, :y 100} {:x 11, :y 100} {:x 12, :y 106} {:x 13, :y 112} {:x 14, :y 106} {:x 15, :y 104} {:x 16, :y 106} {:x 17, :y 104} {:x 18, :y 100} {:x 19, :y 98} {:x 20, :y 98} {:x 21, :y 100} {:x 22, :y 100} {:x 23, :y 90} {:x 24, :y 84} {:x 25, :y 82} {:x 26, :y 90} {:x 27, :y 94} {:x 28, :y 86} {:x 29, :y 94} {:x 30, :y 88} {:x 31, :y 88} {:x 32, :y 96} {:x 33, :y 86} {:x 34, :y 92} {:x 35, :y 88} {:x 36, :y 88} {:x 37, :y 88} {:x 38, :y 70} {:x 39, :y 74} {:x 40, :y 68} {:x 41, :y 62} {:x 42, :y 66} {:x 43, :y 76} {:x 44, :y 72} {:x 45, :y 68} {:x 46, :y 60} {:x 47, :y 64} {:x 48, :y 64} {:x 49, :y 66} {:x 50, :y 56} {:x 51, :y 58} {:x 52, :y 62} {:x 53, :y 64} {:x 54, :y 72} {:x 55, :y 72} {:x 56, :y 80} {:x 57, :y 76} {:x 58, :y 80} {:x 59, :y 94} {:x 60, :y 76} {:x 61, :y 68} {:x 62, :y 66} {:x 63, :y 74} {:x 64, :y 72} {:x 65, :y 70} {:x 66, :y 62} {:x 67, :y 70} {:x 68, :y 78} {:x 69, :y 76} {:x 70, :y 78} {:x 71, :y 82} {:x 72, :y 78} {:x 73, :y 78} {:x 74, :y 76} {:x 75, :y 76} {:x 76, :y 66} {:x 77, :y 70} {:x 78, :y 82} {:x 79, :y 78} {:x 80, :y 78} {:x 81, :y 82} {:x 82, :y 80} {:x 83, :y 82} {:x 84, :y 82} {:x 85, :y 90} {:x 86, :y 98} {:x 87, :y 106} {:x 88, :y 90} {:x 89, :y 86} {:x 90, :y 100} {:x 91, :y 88} {:x 92, :y 86} {:x 93, :y 76} {:x 94, :y 74} {:x 95, :y 64} {:x 96, :y 54} {:x 97, :y 56} {:x 98, :y 44} {:x 99, :y 50} {:x 100, :y 56} {:x 101, :y 54} {:x 102, :y 48} {:x 103, :y 42} {:x 104, :y 40} {:x 105, :y 42} {:x 106, :y 54} {:x 107, :y 58} {:x 108, :y 52} {:x 109, :y 46} {:x 110, :y 38} {:x 111, :y 60} {:x 112, :y 68} {:x 113, :y 68} {:x 114, :y 68} {:x 115, :y 68} {:x 116, :y 64} {:x 117, :y 64} {:x 118, :y 60} {:x 119, :y 66} {:x 120, :y 56} {:x 121, :y 64} {:x 122, :y 60} {:x 123, :y 56} {:x 124, :y 54} {:x 125, :y 60} {:x 126, :y 52} {:x 127, :y 48} {:x 128, :y 38} {:x 129, :y 44} {:x 130, :y 44} {:x 131, :y 40} {:x 132, :y 48} {:x 133, :y 62} {:x 134, :y 60} {:x 135, :y 62} {:x 136, :y 56} {:x 137, :y 48} {:x 138, :y 40} {:x 139, :y 34} {:x 140, :y 28} {:x 141, :y 12})}], :marks [{:type \"line\", :from {:data \"c7db8098-c687-47a5-8d58-ad7bf373d0cc\"}, :properties {:enter {:x {:scale \"x\", :field \"data.x\"}, :y {:scale \"y\", :field \"data.y\"}, :stroke {:value \"#FF29D2\"}, :strokeWidth {:value 2}, :strokeOpacity {:value 1}}}}], :scales [{:name \"x\", :type \"linear\", :range \"width\", :zero false, :domain {:data \"c7db8098-c687-47a5-8d58-ad7bf373d0cc\", :field \"data.x\"}} {:name \"y\", :type \"linear\", :range \"height\", :nice true, :zero false, :domain {:data \"c7db8098-c687-47a5-8d58-ad7bf373d0cc\", :field \"data.y\"}}], :axes [{:type \"x\", :scale \"x\"} {:type \"y\", :scale \"y\"}]}}"}
;; <=

;; @@
(histogram (map count (repeatedly 100 gamble)))
;; @@
;; =>
;;; {"type":"vega","content":{"width":400,"height":247.2187957763672,"padding":{"top":10,"left":55,"bottom":40,"right":10},"data":[{"name":"f359f172-9e74-4451-8272-87f57432f4f7","values":[{"x":1182.0,"y":0},{"x":1699557.7500000002,"y":95.0},{"x":3397933.5000000005,"y":1.0},{"x":5096309.250000001,"y":1.0},{"x":6794685.000000001,"y":1.0},{"x":8493060.750000002,"y":0.0},{"x":1.0191436500000002E7,"y":1.0},{"x":1.1889812250000002E7,"y":0.0},{"x":1.3588188000000002E7,"y":1.0},{"x":1.5286563750000002E7,"y":0}]}],"marks":[{"type":"line","from":{"data":"f359f172-9e74-4451-8272-87f57432f4f7"},"properties":{"enter":{"x":{"scale":"x","field":"data.x"},"y":{"scale":"y","field":"data.y"},"interpolate":{"value":"step-before"},"fill":{"value":"steelblue"},"fillOpacity":{"value":0.4},"stroke":{"value":"steelblue"},"strokeWidth":{"value":2},"strokeOpacity":{"value":1}}}}],"scales":[{"name":"x","type":"linear","range":"width","zero":false,"domain":{"data":"f359f172-9e74-4451-8272-87f57432f4f7","field":"data.x"}},{"name":"y","type":"linear","range":"height","nice":true,"zero":false,"domain":{"data":"f359f172-9e74-4451-8272-87f57432f4f7","field":"data.y"}}],"axes":[{"type":"x","scale":"x"},{"type":"y","scale":"y"}]},"value":"#gorilla_repl.vega.VegaView{:content {:width 400, :height 247.2188, :padding {:top 10, :left 55, :bottom 40, :right 10}, :data [{:name \"f359f172-9e74-4451-8272-87f57432f4f7\", :values ({:x 1182.0, :y 0} {:x 1699557.7500000002, :y 95.0} {:x 3397933.5000000005, :y 1.0} {:x 5096309.250000001, :y 1.0} {:x 6794685.000000001, :y 1.0} {:x 8493060.750000002, :y 0.0} {:x 1.0191436500000002E7, :y 1.0} {:x 1.1889812250000002E7, :y 0.0} {:x 1.3588188000000002E7, :y 1.0} {:x 1.5286563750000002E7, :y 0})}], :marks [{:type \"line\", :from {:data \"f359f172-9e74-4451-8272-87f57432f4f7\"}, :properties {:enter {:x {:scale \"x\", :field \"data.x\"}, :y {:scale \"y\", :field \"data.y\"}, :interpolate {:value \"step-before\"}, :fill {:value \"steelblue\"}, :fillOpacity {:value 0.4}, :stroke {:value \"steelblue\"}, :strokeWidth {:value 2}, :strokeOpacity {:value 1}}}}], :scales [{:name \"x\", :type \"linear\", :range \"width\", :zero false, :domain {:data \"f359f172-9e74-4451-8272-87f57432f4f7\", :field \"data.x\"}} {:name \"y\", :type \"linear\", :range \"height\", :nice true, :zero false, :domain {:data \"f359f172-9e74-4451-8272-87f57432f4f7\", :field \"data.y\"}}], :axes [{:type \"x\", :scale \"x\"} {:type \"y\", :scale \"y\"}]}}"}
;; <=

;; @@
(defn coupon-collecting
  []
  (let [n-letters 9
        gen-rand-letter #(rand-int n-letters)
        update-count #(update %1 %2 (fnil inc 0))
        not-fill? #(< (count %) n-letters)]
    (count (take-while not-fill? (reductions update-count {} (repeatedly gen-rand-letter))))))
(histogram (repeatedly 10000 coupon-collecting))
;; @@
;; =>
;;; {"type":"vega","content":{"width":400,"height":247.2187957763672,"padding":{"top":10,"left":55,"bottom":40,"right":10},"data":[{"name":"3de77e5d-c357-437b-a01e-cde0b7ad6863","values":[{"x":9.0,"y":0},{"x":15.8,"y":1203.0},{"x":22.6,"y":3461.0},{"x":29.400000000000002,"y":2631.0},{"x":36.2,"y":1437.0},{"x":43.0,"y":644.0},{"x":49.8,"y":355.0},{"x":56.599999999999994,"y":163.0},{"x":63.39999999999999,"y":56.0},{"x":70.19999999999999,"y":26.0},{"x":76.99999999999999,"y":15.0},{"x":83.79999999999998,"y":5.0},{"x":90.59999999999998,"y":1.0},{"x":97.39999999999998,"y":1.0},{"x":104.19999999999997,"y":0.0},{"x":110.99999999999997,"y":1.0},{"x":117.79999999999997,"y":1.0},{"x":124.59999999999997,"y":0}]}],"marks":[{"type":"line","from":{"data":"3de77e5d-c357-437b-a01e-cde0b7ad6863"},"properties":{"enter":{"x":{"scale":"x","field":"data.x"},"y":{"scale":"y","field":"data.y"},"interpolate":{"value":"step-before"},"fill":{"value":"steelblue"},"fillOpacity":{"value":0.4},"stroke":{"value":"steelblue"},"strokeWidth":{"value":2},"strokeOpacity":{"value":1}}}}],"scales":[{"name":"x","type":"linear","range":"width","zero":false,"domain":{"data":"3de77e5d-c357-437b-a01e-cde0b7ad6863","field":"data.x"}},{"name":"y","type":"linear","range":"height","nice":true,"zero":false,"domain":{"data":"3de77e5d-c357-437b-a01e-cde0b7ad6863","field":"data.y"}}],"axes":[{"type":"x","scale":"x"},{"type":"y","scale":"y"}]},"value":"#gorilla_repl.vega.VegaView{:content {:width 400, :height 247.2188, :padding {:top 10, :left 55, :bottom 40, :right 10}, :data [{:name \"3de77e5d-c357-437b-a01e-cde0b7ad6863\", :values ({:x 9.0, :y 0} {:x 15.8, :y 1203.0} {:x 22.6, :y 3461.0} {:x 29.400000000000002, :y 2631.0} {:x 36.2, :y 1437.0} {:x 43.0, :y 644.0} {:x 49.8, :y 355.0} {:x 56.599999999999994, :y 163.0} {:x 63.39999999999999, :y 56.0} {:x 70.19999999999999, :y 26.0} {:x 76.99999999999999, :y 15.0} {:x 83.79999999999998, :y 5.0} {:x 90.59999999999998, :y 1.0} {:x 97.39999999999998, :y 1.0} {:x 104.19999999999997, :y 0.0} {:x 110.99999999999997, :y 1.0} {:x 117.79999999999997, :y 1.0} {:x 124.59999999999997, :y 0})}], :marks [{:type \"line\", :from {:data \"3de77e5d-c357-437b-a01e-cde0b7ad6863\"}, :properties {:enter {:x {:scale \"x\", :field \"data.x\"}, :y {:scale \"y\", :field \"data.y\"}, :interpolate {:value \"step-before\"}, :fill {:value \"steelblue\"}, :fillOpacity {:value 0.4}, :stroke {:value \"steelblue\"}, :strokeWidth {:value 2}, :strokeOpacity {:value 1}}}}], :scales [{:name \"x\", :type \"linear\", :range \"width\", :zero false, :domain {:data \"3de77e5d-c357-437b-a01e-cde0b7ad6863\", :field \"data.x\"}} {:name \"y\", :type \"linear\", :range \"height\", :nice true, :zero false, :domain {:data \"3de77e5d-c357-437b-a01e-cde0b7ad6863\", :field \"data.y\"}}], :axes [{:type \"x\", :scale \"x\"} {:type \"y\", :scale \"y\"}]}}"}
;; <=

;; @@
;; P1.1
(let [pows (range 2 9)
      get-error #(Math/abs (- Math/PI (buffon-needle-problem %)))]
  (list-plot (for [n pows] [n (get-error (Math/pow 10 n))])))
;; @@
;; =>
;;; {"type":"vega","content":{"width":400,"height":247.2187957763672,"padding":{"top":10,"left":55,"bottom":40,"right":10},"data":[{"name":"3803bea1-3f66-4f7f-af02-f80538773c9f","values":[{"x":2,"y":1.4038618918647527},{"x":3,"y":0.06353555153841217},{"x":4,"y":0.01099246620844152},{"x":5,"y":0.00974172969377074},{"x":6,"y":0.002478100578458875},{"x":7,"y":8.083922012747102E-4},{"x":8,"y":2.2885513174619732E-4}]}],"marks":[{"type":"symbol","from":{"data":"3803bea1-3f66-4f7f-af02-f80538773c9f"},"properties":{"enter":{"x":{"scale":"x","field":"data.x"},"y":{"scale":"y","field":"data.y"},"fill":{"value":"steelblue"},"fillOpacity":{"value":1}},"update":{"shape":"circle","size":{"value":70},"stroke":{"value":"transparent"}},"hover":{"size":{"value":210},"stroke":{"value":"white"}}}}],"scales":[{"name":"x","type":"linear","range":"width","zero":false,"domain":{"data":"3803bea1-3f66-4f7f-af02-f80538773c9f","field":"data.x"}},{"name":"y","type":"linear","range":"height","nice":true,"zero":false,"domain":{"data":"3803bea1-3f66-4f7f-af02-f80538773c9f","field":"data.y"}}],"axes":[{"type":"x","scale":"x"},{"type":"y","scale":"y"}]},"value":"#gorilla_repl.vega.VegaView{:content {:width 400, :height 247.2188, :padding {:top 10, :left 55, :bottom 40, :right 10}, :data [{:name \"3803bea1-3f66-4f7f-af02-f80538773c9f\", :values ({:x 2, :y 1.4038618918647527} {:x 3, :y 0.06353555153841217} {:x 4, :y 0.01099246620844152} {:x 5, :y 0.00974172969377074} {:x 6, :y 0.002478100578458875} {:x 7, :y 8.083922012747102E-4} {:x 8, :y 2.2885513174619732E-4})}], :marks [{:type \"symbol\", :from {:data \"3803bea1-3f66-4f7f-af02-f80538773c9f\"}, :properties {:enter {:x {:scale \"x\", :field \"data.x\"}, :y {:scale \"y\", :field \"data.y\"}, :fill {:value \"steelblue\"}, :fillOpacity {:value 1}}, :update {:shape \"circle\", :size {:value 70}, :stroke {:value \"transparent\"}}, :hover {:size {:value 210}, :stroke {:value \"white\"}}}}], :scales [{:name \"x\", :type \"linear\", :range \"width\", :zero false, :domain {:data \"3803bea1-3f66-4f7f-af02-f80538773c9f\", :field \"data.x\"}} {:name \"y\", :type \"linear\", :range \"height\", :nice true, :zero false, :domain {:data \"3803bea1-3f66-4f7f-af02-f80538773c9f\", :field \"data.y\"}}], :axes [{:type \"x\", :scale \"x\"} {:type \"y\", :scale \"y\"}]}}"}
;; <=

;; @@
;; P1.2
(mean (repeatedly 10000 #(buffon-needle-problem 200)))
;; 3.175938714613824
(mean (repeatedly 1000 #(buffon-needle-problem 2000)))
;; 3.1484694761628202
(mean (repeatedly 100 #(buffon-needle-problem 20000)))
;; 3.140771532901823
;; @@

;; @@
;; P1.3
;; (a)
(let [roll #(+ (rand-int 6) (rand-int 6) 2)]
  (histogram (repeatedly 1000 roll)))
;; @@
;; =>
;;; {"type":"vega","content":{"width":400,"height":247.2187957763672,"padding":{"top":10,"left":55,"bottom":40,"right":10},"data":[{"name":"421d13a7-44ff-4d6c-8621-a8894c5cba9d","values":[{"x":2.0,"y":0},{"x":2.909090909090909,"y":27.0},{"x":3.8181818181818183,"y":50.0},{"x":4.7272727272727275,"y":73.0},{"x":5.636363636363637,"y":119.0},{"x":6.545454545454546,"y":144.0},{"x":7.454545454545455,"y":169.0},{"x":8.363636363636365,"y":120.0},{"x":9.272727272727275,"y":113.0},{"x":10.181818181818185,"y":92.0},{"x":11.090909090909095,"y":53.0},{"x":12.000000000000005,"y":40.0},{"x":12.909090909090915,"y":0}]}],"marks":[{"type":"line","from":{"data":"421d13a7-44ff-4d6c-8621-a8894c5cba9d"},"properties":{"enter":{"x":{"scale":"x","field":"data.x"},"y":{"scale":"y","field":"data.y"},"interpolate":{"value":"step-before"},"fill":{"value":"steelblue"},"fillOpacity":{"value":0.4},"stroke":{"value":"steelblue"},"strokeWidth":{"value":2},"strokeOpacity":{"value":1}}}}],"scales":[{"name":"x","type":"linear","range":"width","zero":false,"domain":{"data":"421d13a7-44ff-4d6c-8621-a8894c5cba9d","field":"data.x"}},{"name":"y","type":"linear","range":"height","nice":true,"zero":false,"domain":{"data":"421d13a7-44ff-4d6c-8621-a8894c5cba9d","field":"data.y"}}],"axes":[{"type":"x","scale":"x"},{"type":"y","scale":"y"}]},"value":"#gorilla_repl.vega.VegaView{:content {:width 400, :height 247.2188, :padding {:top 10, :left 55, :bottom 40, :right 10}, :data [{:name \"421d13a7-44ff-4d6c-8621-a8894c5cba9d\", :values ({:x 2.0, :y 0} {:x 2.909090909090909, :y 27.0} {:x 3.8181818181818183, :y 50.0} {:x 4.7272727272727275, :y 73.0} {:x 5.636363636363637, :y 119.0} {:x 6.545454545454546, :y 144.0} {:x 7.454545454545455, :y 169.0} {:x 8.363636363636365, :y 120.0} {:x 9.272727272727275, :y 113.0} {:x 10.181818181818185, :y 92.0} {:x 11.090909090909095, :y 53.0} {:x 12.000000000000005, :y 40.0} {:x 12.909090909090915, :y 0})}], :marks [{:type \"line\", :from {:data \"421d13a7-44ff-4d6c-8621-a8894c5cba9d\"}, :properties {:enter {:x {:scale \"x\", :field \"data.x\"}, :y {:scale \"y\", :field \"data.y\"}, :interpolate {:value \"step-before\"}, :fill {:value \"steelblue\"}, :fillOpacity {:value 0.4}, :stroke {:value \"steelblue\"}, :strokeWidth {:value 2}, :strokeOpacity {:value 1}}}}], :scales [{:name \"x\", :type \"linear\", :range \"width\", :zero false, :domain {:data \"421d13a7-44ff-4d6c-8621-a8894c5cba9d\", :field \"data.x\"}} {:name \"y\", :type \"linear\", :range \"height\", :nice true, :zero false, :domain {:data \"421d13a7-44ff-4d6c-8621-a8894c5cba9d\", :field \"data.y\"}}], :axes [{:type \"x\", :scale \"x\"} {:type \"y\", :scale \"y\"}]}}"}
;; <=

;; @@
;; (b)
(let [roll #(vector (+ 1 (rand-int 6))
                    (+ 1 (rand-int 6)))
      fq (frequencies (repeatedly 1000 roll))
      ks (sort (keys fq))]
  (bar-chart ks (for [k ks] (get fq k))))
;; @@
;; =>
;;; {"type":"vega","content":{"width":400,"height":247.2187957763672,"padding":{"top":10,"left":55,"bottom":40,"right":10},"data":[{"name":"66452fb2-69dc-4b36-b623-30df6cb16aa5","values":[{"x":[1,1],"y":30},{"x":[1,2],"y":27},{"x":[1,3],"y":31},{"x":[1,4],"y":18},{"x":[1,5],"y":29},{"x":[1,6],"y":34},{"x":[2,1],"y":33},{"x":[2,2],"y":25},{"x":[2,3],"y":24},{"x":[2,4],"y":33},{"x":[2,5],"y":26},{"x":[2,6],"y":30},{"x":[3,1],"y":21},{"x":[3,2],"y":22},{"x":[3,3],"y":26},{"x":[3,4],"y":26},{"x":[3,5],"y":23},{"x":[3,6],"y":34},{"x":[4,1],"y":30},{"x":[4,2],"y":27},{"x":[4,3],"y":25},{"x":[4,4],"y":26},{"x":[4,5],"y":23},{"x":[4,6],"y":36},{"x":[5,1],"y":32},{"x":[5,2],"y":30},{"x":[5,3],"y":34},{"x":[5,4],"y":33},{"x":[5,5],"y":33},{"x":[5,6],"y":26},{"x":[6,1],"y":22},{"x":[6,2],"y":22},{"x":[6,3],"y":21},{"x":[6,4],"y":39},{"x":[6,5],"y":31},{"x":[6,6],"y":18}]}],"marks":[{"type":"rect","from":{"data":"66452fb2-69dc-4b36-b623-30df6cb16aa5"},"properties":{"enter":{"x":{"scale":"x","field":"data.x"},"width":{"scale":"x","band":true,"offset":-1},"y":{"scale":"y","field":"data.y"},"y2":{"scale":"y","value":0}},"update":{"fill":{"value":"steelblue"},"opacity":{"value":1}},"hover":{"fill":{"value":"#FF29D2"}}}}],"scales":[{"name":"x","type":"ordinal","range":"width","domain":{"data":"66452fb2-69dc-4b36-b623-30df6cb16aa5","field":"data.x"}},{"name":"y","range":"height","nice":true,"domain":{"data":"66452fb2-69dc-4b36-b623-30df6cb16aa5","field":"data.y"}}],"axes":[{"type":"x","scale":"x"},{"type":"y","scale":"y"}]},"value":"#gorilla_repl.vega.VegaView{:content {:width 400, :height 247.2188, :padding {:top 10, :left 55, :bottom 40, :right 10}, :data [{:name \"66452fb2-69dc-4b36-b623-30df6cb16aa5\", :values ({:x [1 1], :y 30} {:x [1 2], :y 27} {:x [1 3], :y 31} {:x [1 4], :y 18} {:x [1 5], :y 29} {:x [1 6], :y 34} {:x [2 1], :y 33} {:x [2 2], :y 25} {:x [2 3], :y 24} {:x [2 4], :y 33} {:x [2 5], :y 26} {:x [2 6], :y 30} {:x [3 1], :y 21} {:x [3 2], :y 22} {:x [3 3], :y 26} {:x [3 4], :y 26} {:x [3 5], :y 23} {:x [3 6], :y 34} {:x [4 1], :y 30} {:x [4 2], :y 27} {:x [4 3], :y 25} {:x [4 4], :y 26} {:x [4 5], :y 23} {:x [4 6], :y 36} {:x [5 1], :y 32} {:x [5 2], :y 30} {:x [5 3], :y 34} {:x [5 4], :y 33} {:x [5 5], :y 33} {:x [5 6], :y 26} {:x [6 1], :y 22} {:x [6 2], :y 22} {:x [6 3], :y 21} {:x [6 4], :y 39} {:x [6 5], :y 31} {:x [6 6], :y 18})}], :marks [{:type \"rect\", :from {:data \"66452fb2-69dc-4b36-b623-30df6cb16aa5\"}, :properties {:enter {:x {:scale \"x\", :field \"data.x\"}, :width {:scale \"x\", :band true, :offset -1}, :y {:scale \"y\", :field \"data.y\"}, :y2 {:scale \"y\", :value 0}}, :update {:fill {:value \"steelblue\"}, :opacity {:value 1}}, :hover {:fill {:value \"#FF29D2\"}}}}], :scales [{:name \"x\", :type \"ordinal\", :range \"width\", :domain {:data \"66452fb2-69dc-4b36-b623-30df6cb16aa5\", :field \"data.x\"}} {:name \"y\", :range \"height\", :nice true, :domain {:data \"66452fb2-69dc-4b36-b623-30df6cb16aa5\", :field \"data.y\"}}], :axes [{:type \"x\", :scale \"x\"} {:type \"y\", :scale \"y\"}]}}"}
;; <=

;; @@
;; (c)
(let [roll #(sum (repeatedly 6 (fn [] (+ (rand-int 6) 1))))]
  (histogram (repeatedly 4000 roll)))
;; @@
;; =>
;;; {"type":"vega","content":{"width":400,"height":247.2187957763672,"padding":{"top":10,"left":55,"bottom":40,"right":10},"data":[{"name":"c6ed12e9-7293-4e22-9e13-cce0ad476a13","values":[{"x":8.0,"y":0},{"x":10.0,"y":5.0},{"x":12.0,"y":28.0},{"x":14.0,"y":102.0},{"x":16.0,"y":244.0},{"x":18.0,"y":445.0},{"x":20.0,"y":627.0},{"x":22.0,"y":739.0},{"x":24.0,"y":686.0},{"x":26.0,"y":535.0},{"x":28.0,"y":329.0},{"x":30.0,"y":174.0},{"x":32.0,"y":62.0},{"x":34.0,"y":19.0},{"x":36.0,"y":5.0},{"x":38.0,"y":0}]}],"marks":[{"type":"line","from":{"data":"c6ed12e9-7293-4e22-9e13-cce0ad476a13"},"properties":{"enter":{"x":{"scale":"x","field":"data.x"},"y":{"scale":"y","field":"data.y"},"interpolate":{"value":"step-before"},"fill":{"value":"steelblue"},"fillOpacity":{"value":0.4},"stroke":{"value":"steelblue"},"strokeWidth":{"value":2},"strokeOpacity":{"value":1}}}}],"scales":[{"name":"x","type":"linear","range":"width","zero":false,"domain":{"data":"c6ed12e9-7293-4e22-9e13-cce0ad476a13","field":"data.x"}},{"name":"y","type":"linear","range":"height","nice":true,"zero":false,"domain":{"data":"c6ed12e9-7293-4e22-9e13-cce0ad476a13","field":"data.y"}}],"axes":[{"type":"x","scale":"x"},{"type":"y","scale":"y"}]},"value":"#gorilla_repl.vega.VegaView{:content {:width 400, :height 247.2188, :padding {:top 10, :left 55, :bottom 40, :right 10}, :data [{:name \"c6ed12e9-7293-4e22-9e13-cce0ad476a13\", :values ({:x 8.0, :y 0} {:x 10.0, :y 5.0} {:x 12.0, :y 28.0} {:x 14.0, :y 102.0} {:x 16.0, :y 244.0} {:x 18.0, :y 445.0} {:x 20.0, :y 627.0} {:x 22.0, :y 739.0} {:x 24.0, :y 686.0} {:x 26.0, :y 535.0} {:x 28.0, :y 329.0} {:x 30.0, :y 174.0} {:x 32.0, :y 62.0} {:x 34.0, :y 19.0} {:x 36.0, :y 5.0} {:x 38.0, :y 0})}], :marks [{:type \"line\", :from {:data \"c6ed12e9-7293-4e22-9e13-cce0ad476a13\"}, :properties {:enter {:x {:scale \"x\", :field \"data.x\"}, :y {:scale \"y\", :field \"data.y\"}, :interpolate {:value \"step-before\"}, :fill {:value \"steelblue\"}, :fillOpacity {:value 0.4}, :stroke {:value \"steelblue\"}, :strokeWidth {:value 2}, :strokeOpacity {:value 1}}}}], :scales [{:name \"x\", :type \"linear\", :range \"width\", :zero false, :domain {:data \"c6ed12e9-7293-4e22-9e13-cce0ad476a13\", :field \"data.x\"}} {:name \"y\", :type \"linear\", :range \"height\", :nice true, :zero false, :domain {:data \"c6ed12e9-7293-4e22-9e13-cce0ad476a13\", :field \"data.y\"}}], :axes [{:type \"x\", :scale \"x\"} {:type \"y\", :scale \"y\"}]}}"}
;; <=

;; @@
;; P1.4
(let [reward #(/ 1 (+ (rand-int 6) (rand-int 6) 2))
      samples (repeatedly 1000 reward)]
  (double (mean samples)))
;; 0.1625419552669553
;; @@

;; @@
;; P1.5
;; (a)
(defn get-coupon-count
  [gen-letter update-method not-fill?]
  (count (take-while not-fill? (reductions update-method {} (repeatedly gen-letter)))))

(defn get-milk-count-a
  []
  (let [letters "milk"]
    (get-coupon-count
     #(rand-nth letters)
     #(update %1 %2 (fnil inc 0))
     #(< (count %) (count letters)))))

(histogram (repeatedly 1000 get-milk-count-a))
;; @@
;; =>
;;; {"type":"vega","content":{"width":400,"height":247.2187957763672,"padding":{"top":10,"left":55,"bottom":40,"right":10},"data":[{"name":"0a50ea89-f136-4362-8716-0f2eb0109681","values":[{"x":4.0,"y":0},{"x":5.818181818181818,"y":242.0},{"x":7.636363636363637,"y":291.0},{"x":9.454545454545455,"y":188.0},{"x":11.272727272727273,"y":117.0},{"x":13.090909090909092,"y":76.0},{"x":14.90909090909091,"y":20.0},{"x":16.72727272727273,"y":30.0},{"x":18.54545454545455,"y":15.0},{"x":20.36363636363637,"y":10.0},{"x":22.18181818181819,"y":7.0},{"x":24.00000000000001,"y":4.0},{"x":25.81818181818183,"y":0}]}],"marks":[{"type":"line","from":{"data":"0a50ea89-f136-4362-8716-0f2eb0109681"},"properties":{"enter":{"x":{"scale":"x","field":"data.x"},"y":{"scale":"y","field":"data.y"},"interpolate":{"value":"step-before"},"fill":{"value":"steelblue"},"fillOpacity":{"value":0.4},"stroke":{"value":"steelblue"},"strokeWidth":{"value":2},"strokeOpacity":{"value":1}}}}],"scales":[{"name":"x","type":"linear","range":"width","zero":false,"domain":{"data":"0a50ea89-f136-4362-8716-0f2eb0109681","field":"data.x"}},{"name":"y","type":"linear","range":"height","nice":true,"zero":false,"domain":{"data":"0a50ea89-f136-4362-8716-0f2eb0109681","field":"data.y"}}],"axes":[{"type":"x","scale":"x"},{"type":"y","scale":"y"}]},"value":"#gorilla_repl.vega.VegaView{:content {:width 400, :height 247.2188, :padding {:top 10, :left 55, :bottom 40, :right 10}, :data [{:name \"0a50ea89-f136-4362-8716-0f2eb0109681\", :values ({:x 4.0, :y 0} {:x 5.818181818181818, :y 242.0} {:x 7.636363636363637, :y 291.0} {:x 9.454545454545455, :y 188.0} {:x 11.272727272727273, :y 117.0} {:x 13.090909090909092, :y 76.0} {:x 14.90909090909091, :y 20.0} {:x 16.72727272727273, :y 30.0} {:x 18.54545454545455, :y 15.0} {:x 20.36363636363637, :y 10.0} {:x 22.18181818181819, :y 7.0} {:x 24.00000000000001, :y 4.0} {:x 25.81818181818183, :y 0})}], :marks [{:type \"line\", :from {:data \"0a50ea89-f136-4362-8716-0f2eb0109681\"}, :properties {:enter {:x {:scale \"x\", :field \"data.x\"}, :y {:scale \"y\", :field \"data.y\"}, :interpolate {:value \"step-before\"}, :fill {:value \"steelblue\"}, :fillOpacity {:value 0.4}, :stroke {:value \"steelblue\"}, :strokeWidth {:value 2}, :strokeOpacity {:value 1}}}}], :scales [{:name \"x\", :type \"linear\", :range \"width\", :zero false, :domain {:data \"0a50ea89-f136-4362-8716-0f2eb0109681\", :field \"data.x\"}} {:name \"y\", :type \"linear\", :range \"height\", :nice true, :zero false, :domain {:data \"0a50ea89-f136-4362-8716-0f2eb0109681\", :field \"data.y\"}}], :axes [{:type \"x\", :scale \"x\"} {:type \"y\", :scale \"y\"}]}}"}
;; <=

;; @@
;; (b)
(defn get-milk-count-b
  []
  (let [letters "milk"]
    (get-coupon-count
     #(nth letters (int (/ (rand-int 31) 10)))
     #(update %1 %2 (fnil inc 0))
     #(< (count %) (count letters)))))

(histogram (repeatedly 1000 get-milk-count-b))
;; @@
;; =>
;;; {"type":"vega","content":{"width":400,"height":247.2187957763672,"padding":{"top":10,"left":55,"bottom":40,"right":10},"data":[{"name":"023673ec-5246-4e14-a8c0-ec4b5803b0df","values":[{"x":4.0,"y":0},{"x":23.454545454545457,"y":534.0},{"x":42.909090909090914,"y":216.0},{"x":62.363636363636374,"y":116.0},{"x":81.81818181818183,"y":69.0},{"x":101.27272727272728,"y":35.0},{"x":120.72727272727273,"y":11.0},{"x":140.1818181818182,"y":7.0},{"x":159.63636363636365,"y":8.0},{"x":179.09090909090912,"y":1.0},{"x":198.5454545454546,"y":2.0},{"x":218.00000000000006,"y":1.0},{"x":237.45454545454552,"y":0}]}],"marks":[{"type":"line","from":{"data":"023673ec-5246-4e14-a8c0-ec4b5803b0df"},"properties":{"enter":{"x":{"scale":"x","field":"data.x"},"y":{"scale":"y","field":"data.y"},"interpolate":{"value":"step-before"},"fill":{"value":"steelblue"},"fillOpacity":{"value":0.4},"stroke":{"value":"steelblue"},"strokeWidth":{"value":2},"strokeOpacity":{"value":1}}}}],"scales":[{"name":"x","type":"linear","range":"width","zero":false,"domain":{"data":"023673ec-5246-4e14-a8c0-ec4b5803b0df","field":"data.x"}},{"name":"y","type":"linear","range":"height","nice":true,"zero":false,"domain":{"data":"023673ec-5246-4e14-a8c0-ec4b5803b0df","field":"data.y"}}],"axes":[{"type":"x","scale":"x"},{"type":"y","scale":"y"}]},"value":"#gorilla_repl.vega.VegaView{:content {:width 400, :height 247.2188, :padding {:top 10, :left 55, :bottom 40, :right 10}, :data [{:name \"023673ec-5246-4e14-a8c0-ec4b5803b0df\", :values ({:x 4.0, :y 0} {:x 23.454545454545457, :y 534.0} {:x 42.909090909090914, :y 216.0} {:x 62.363636363636374, :y 116.0} {:x 81.81818181818183, :y 69.0} {:x 101.27272727272728, :y 35.0} {:x 120.72727272727273, :y 11.0} {:x 140.1818181818182, :y 7.0} {:x 159.63636363636365, :y 8.0} {:x 179.09090909090912, :y 1.0} {:x 198.5454545454546, :y 2.0} {:x 218.00000000000006, :y 1.0} {:x 237.45454545454552, :y 0})}], :marks [{:type \"line\", :from {:data \"023673ec-5246-4e14-a8c0-ec4b5803b0df\"}, :properties {:enter {:x {:scale \"x\", :field \"data.x\"}, :y {:scale \"y\", :field \"data.y\"}, :interpolate {:value \"step-before\"}, :fill {:value \"steelblue\"}, :fillOpacity {:value 0.4}, :stroke {:value \"steelblue\"}, :strokeWidth {:value 2}, :strokeOpacity {:value 1}}}}], :scales [{:name \"x\", :type \"linear\", :range \"width\", :zero false, :domain {:data \"023673ec-5246-4e14-a8c0-ec4b5803b0df\", :field \"data.x\"}} {:name \"y\", :type \"linear\", :range \"height\", :nice true, :zero false, :domain {:data \"023673ec-5246-4e14-a8c0-ec4b5803b0df\", :field \"data.y\"}}], :axes [{:type \"x\", :scale \"x\"} {:type \"y\", :scale \"y\"}]}}"}
;; <=

;; @@
;; (c)
(defn get-BC-count
  []
  (let [coupon (frequencies "BattleCreek")
        letters (keys coupon)]
    (get-coupon-count
     #(nth letters (rand-int (count letters)))
     #(update %1 %2 (fnil inc 0))
     #(some true? (for [[k v] coupon] (< (get % k 0) v))))))
(histogram (repeatedly 1000 get-BC-count))
;; @@
;; =>
;;; {"type":"vega","content":{"width":400,"height":247.2187957763672,"padding":{"top":10,"left":55,"bottom":40,"right":10},"data":[{"name":"6f432371-222a-41a5-a627-7aa2c30fff10","values":[{"x":12.0,"y":0},{"x":18.181818181818183,"y":105.0},{"x":24.363636363636367,"y":249.0},{"x":30.54545454545455,"y":239.0},{"x":36.727272727272734,"y":164.0},{"x":42.90909090909092,"y":110.0},{"x":49.09090909090911,"y":80.0},{"x":55.272727272727295,"y":18.0},{"x":61.45454545454548,"y":14.0},{"x":67.63636363636367,"y":10.0},{"x":73.81818181818186,"y":7.0},{"x":80.00000000000004,"y":4.0},{"x":86.18181818181823,"y":0}]}],"marks":[{"type":"line","from":{"data":"6f432371-222a-41a5-a627-7aa2c30fff10"},"properties":{"enter":{"x":{"scale":"x","field":"data.x"},"y":{"scale":"y","field":"data.y"},"interpolate":{"value":"step-before"},"fill":{"value":"steelblue"},"fillOpacity":{"value":0.4},"stroke":{"value":"steelblue"},"strokeWidth":{"value":2},"strokeOpacity":{"value":1}}}}],"scales":[{"name":"x","type":"linear","range":"width","zero":false,"domain":{"data":"6f432371-222a-41a5-a627-7aa2c30fff10","field":"data.x"}},{"name":"y","type":"linear","range":"height","nice":true,"zero":false,"domain":{"data":"6f432371-222a-41a5-a627-7aa2c30fff10","field":"data.y"}}],"axes":[{"type":"x","scale":"x"},{"type":"y","scale":"y"}]},"value":"#gorilla_repl.vega.VegaView{:content {:width 400, :height 247.2188, :padding {:top 10, :left 55, :bottom 40, :right 10}, :data [{:name \"6f432371-222a-41a5-a627-7aa2c30fff10\", :values ({:x 12.0, :y 0} {:x 18.181818181818183, :y 105.0} {:x 24.363636363636367, :y 249.0} {:x 30.54545454545455, :y 239.0} {:x 36.727272727272734, :y 164.0} {:x 42.90909090909092, :y 110.0} {:x 49.09090909090911, :y 80.0} {:x 55.272727272727295, :y 18.0} {:x 61.45454545454548, :y 14.0} {:x 67.63636363636367, :y 10.0} {:x 73.81818181818186, :y 7.0} {:x 80.00000000000004, :y 4.0} {:x 86.18181818181823, :y 0})}], :marks [{:type \"line\", :from {:data \"6f432371-222a-41a5-a627-7aa2c30fff10\"}, :properties {:enter {:x {:scale \"x\", :field \"data.x\"}, :y {:scale \"y\", :field \"data.y\"}, :interpolate {:value \"step-before\"}, :fill {:value \"steelblue\"}, :fillOpacity {:value 0.4}, :stroke {:value \"steelblue\"}, :strokeWidth {:value 2}, :strokeOpacity {:value 1}}}}], :scales [{:name \"x\", :type \"linear\", :range \"width\", :zero false, :domain {:data \"6f432371-222a-41a5-a627-7aa2c30fff10\", :field \"data.x\"}} {:name \"y\", :type \"linear\", :range \"height\", :nice true, :zero false, :domain {:data \"6f432371-222a-41a5-a627-7aa2c30fff10\", :field \"data.y\"}}], :axes [{:type \"x\", :scale \"x\"} {:type \"y\", :scale \"y\"}]}}"}
;; <=

;; @@
;; P1.6
(defn modified-coupon-collections
  [alphabet-size window-size]
  (let [not-full? #(< (count %) (Math/pow alphabet-size window-size))]
    (->> (reductions
          #(conj %1 %2)
          #{}
          (partition window-size (repeatedly #(rand-int alphabet-size))))
         (take-while not-full?)
         count)))

(histogram (repeatedly 1000 #(modified-coupon-collections 4 4)))
;; @@
;; =>
;;; {"type":"vega","content":{"width":400,"height":247.2187957763672,"padding":{"top":10,"left":55,"bottom":40,"right":10},"data":[{"name":"f8237e01-5462-4b17-9993-12f9314e78ad","values":[{"x":926.0,"y":0},{"x":1156.909090909091,"y":55.0},{"x":1387.818181818182,"y":253.0},{"x":1618.727272727273,"y":317.0},{"x":1849.636363636364,"y":190.0},{"x":2080.545454545455,"y":104.0},{"x":2311.454545454546,"y":48.0},{"x":2542.363636363637,"y":13.0},{"x":2773.272727272728,"y":12.0},{"x":3004.181818181819,"y":4.0},{"x":3235.09090909091,"y":2.0},{"x":3466.000000000001,"y":2.0},{"x":3696.909090909092,"y":0}]}],"marks":[{"type":"line","from":{"data":"f8237e01-5462-4b17-9993-12f9314e78ad"},"properties":{"enter":{"x":{"scale":"x","field":"data.x"},"y":{"scale":"y","field":"data.y"},"interpolate":{"value":"step-before"},"fill":{"value":"steelblue"},"fillOpacity":{"value":0.4},"stroke":{"value":"steelblue"},"strokeWidth":{"value":2},"strokeOpacity":{"value":1}}}}],"scales":[{"name":"x","type":"linear","range":"width","zero":false,"domain":{"data":"f8237e01-5462-4b17-9993-12f9314e78ad","field":"data.x"}},{"name":"y","type":"linear","range":"height","nice":true,"zero":false,"domain":{"data":"f8237e01-5462-4b17-9993-12f9314e78ad","field":"data.y"}}],"axes":[{"type":"x","scale":"x"},{"type":"y","scale":"y"}]},"value":"#gorilla_repl.vega.VegaView{:content {:width 400, :height 247.2188, :padding {:top 10, :left 55, :bottom 40, :right 10}, :data [{:name \"f8237e01-5462-4b17-9993-12f9314e78ad\", :values ({:x 926.0, :y 0} {:x 1156.909090909091, :y 55.0} {:x 1387.818181818182, :y 253.0} {:x 1618.727272727273, :y 317.0} {:x 1849.636363636364, :y 190.0} {:x 2080.545454545455, :y 104.0} {:x 2311.454545454546, :y 48.0} {:x 2542.363636363637, :y 13.0} {:x 2773.272727272728, :y 12.0} {:x 3004.181818181819, :y 4.0} {:x 3235.09090909091, :y 2.0} {:x 3466.000000000001, :y 2.0} {:x 3696.909090909092, :y 0})}], :marks [{:type \"line\", :from {:data \"f8237e01-5462-4b17-9993-12f9314e78ad\"}, :properties {:enter {:x {:scale \"x\", :field \"data.x\"}, :y {:scale \"y\", :field \"data.y\"}, :interpolate {:value \"step-before\"}, :fill {:value \"steelblue\"}, :fillOpacity {:value 0.4}, :stroke {:value \"steelblue\"}, :strokeWidth {:value 2}, :strokeOpacity {:value 1}}}}], :scales [{:name \"x\", :type \"linear\", :range \"width\", :zero false, :domain {:data \"f8237e01-5462-4b17-9993-12f9314e78ad\", :field \"data.x\"}} {:name \"y\", :type \"linear\", :range \"height\", :nice true, :zero false, :domain {:data \"f8237e01-5462-4b17-9993-12f9314e78ad\", :field \"data.y\"}}], :axes [{:type \"x\", :scale \"x\"} {:type \"y\", :scale \"y\"}]}}"}
;; <=

;; @@
;; P1.7
(defn coin-toss-partition
  [n]
  (->> (repeatedly 1000 #(rand-int 2))
       (partition n)
       frequencies))
(coin-toss-partition 1)
;; {(0) 495, (1) 505}
(coin-toss-partition 2)
;; {(0 1) 120,
;;  (1 1) 134,
;;  (1 0) 111,
;;  (0 0)
;;  135}
(coin-toss-partition 3)
;; {(1 0 1) 41,
;;  (0 0 1) 40,
;;  (0 1 0) 42,
;;  (1 0 0) 39,
;;  (0 0 0) 52,
;;  (0 1 1) 37,
;;  (1 1 1) 46,
;;  (1 1 0)
;;  36}
(defn succ-ones-count
  ([xs c]
   (let [xs (drop-while zero? xs)]
     (if (seq xs)
       (succ-ones-count (drop-while #(= 1 %) xs) (inc c))
       c)))
  ([xs] (succ-ones-count xs 0)))

(succ-ones-count (repeatedly 1000 #(rand-int 2)))
;; 258
;; @@

;; @@
;; P1.8
;; (a)
(let [N 30
      head? #(if (< (rand) 0.6) 1 0)
      xs (reductions + 0 (repeatedly N head?))]
  (list-plot xs :joined true))
;; @@
;; =>
;;; {"type":"vega","content":{"width":400,"height":247.2187957763672,"padding":{"top":10,"left":55,"bottom":40,"right":10},"data":[{"name":"b45b6c4e-f09e-4cb1-8f86-0ce87cc74cb7","values":[{"x":0,"y":0},{"x":1,"y":1},{"x":2,"y":2},{"x":3,"y":2},{"x":4,"y":3},{"x":5,"y":4},{"x":6,"y":5},{"x":7,"y":5},{"x":8,"y":6},{"x":9,"y":7},{"x":10,"y":8},{"x":11,"y":9},{"x":12,"y":10},{"x":13,"y":10},{"x":14,"y":11},{"x":15,"y":12},{"x":16,"y":13},{"x":17,"y":14},{"x":18,"y":15},{"x":19,"y":16},{"x":20,"y":17},{"x":21,"y":17},{"x":22,"y":18},{"x":23,"y":19},{"x":24,"y":19},{"x":25,"y":19},{"x":26,"y":20},{"x":27,"y":20},{"x":28,"y":20},{"x":29,"y":20},{"x":30,"y":21}]}],"marks":[{"type":"line","from":{"data":"b45b6c4e-f09e-4cb1-8f86-0ce87cc74cb7"},"properties":{"enter":{"x":{"scale":"x","field":"data.x"},"y":{"scale":"y","field":"data.y"},"stroke":{"value":"#FF29D2"},"strokeWidth":{"value":2},"strokeOpacity":{"value":1}}}}],"scales":[{"name":"x","type":"linear","range":"width","zero":false,"domain":{"data":"b45b6c4e-f09e-4cb1-8f86-0ce87cc74cb7","field":"data.x"}},{"name":"y","type":"linear","range":"height","nice":true,"zero":false,"domain":{"data":"b45b6c4e-f09e-4cb1-8f86-0ce87cc74cb7","field":"data.y"}}],"axes":[{"type":"x","scale":"x"},{"type":"y","scale":"y"}]},"value":"#gorilla_repl.vega.VegaView{:content {:width 400, :height 247.2188, :padding {:top 10, :left 55, :bottom 40, :right 10}, :data [{:name \"b45b6c4e-f09e-4cb1-8f86-0ce87cc74cb7\", :values ({:x 0, :y 0} {:x 1, :y 1} {:x 2, :y 2} {:x 3, :y 2} {:x 4, :y 3} {:x 5, :y 4} {:x 6, :y 5} {:x 7, :y 5} {:x 8, :y 6} {:x 9, :y 7} {:x 10, :y 8} {:x 11, :y 9} {:x 12, :y 10} {:x 13, :y 10} {:x 14, :y 11} {:x 15, :y 12} {:x 16, :y 13} {:x 17, :y 14} {:x 18, :y 15} {:x 19, :y 16} {:x 20, :y 17} {:x 21, :y 17} {:x 22, :y 18} {:x 23, :y 19} {:x 24, :y 19} {:x 25, :y 19} {:x 26, :y 20} {:x 27, :y 20} {:x 28, :y 20} {:x 29, :y 20} {:x 30, :y 21})}], :marks [{:type \"line\", :from {:data \"b45b6c4e-f09e-4cb1-8f86-0ce87cc74cb7\"}, :properties {:enter {:x {:scale \"x\", :field \"data.x\"}, :y {:scale \"y\", :field \"data.y\"}, :stroke {:value \"#FF29D2\"}, :strokeWidth {:value 2}, :strokeOpacity {:value 1}}}}], :scales [{:name \"x\", :type \"linear\", :range \"width\", :zero false, :domain {:data \"b45b6c4e-f09e-4cb1-8f86-0ce87cc74cb7\", :field \"data.x\"}} {:name \"y\", :type \"linear\", :range \"height\", :nice true, :zero false, :domain {:data \"b45b6c4e-f09e-4cb1-8f86-0ce87cc74cb7\", :field \"data.y\"}}], :axes [{:type \"x\", :scale \"x\"} {:type \"y\", :scale \"y\"}]}}"}
;; <=

;; @@
;; (b)(c)(d)
(let [N 30
      n-trials 200
      head? #(if (< (rand) 0.6) 1 0)
      finals (repeatedly n-trials #(sum (repeatedly N head?)))]
  (prn "mean:"(double (mean finals)))
  (prn "variance:"(variance finals))
  (histogram finals))
;; @@
;; ->
;;; &quot;mean:&quot; 17.79
;;; &quot;variance:&quot; 6.558693467336684
;;; 
;; <-
;; =>
;;; {"type":"vega","content":{"width":400,"height":247.2187957763672,"padding":{"top":10,"left":55,"bottom":40,"right":10},"data":[{"name":"6c7d7c9a-979f-41ed-9277-edd3dcac7833","values":[{"x":10.0,"y":0},{"x":11.555555555555555,"y":1.0},{"x":13.11111111111111,"y":8.0},{"x":14.666666666666666,"y":10.0},{"x":16.22222222222222,"y":43.0},{"x":17.77777777777778,"y":30.0},{"x":19.333333333333336,"y":56.0},{"x":20.888888888888893,"y":24.0},{"x":22.44444444444445,"y":21.0},{"x":24.000000000000007,"y":7.0},{"x":25.555555555555564,"y":0}]}],"marks":[{"type":"line","from":{"data":"6c7d7c9a-979f-41ed-9277-edd3dcac7833"},"properties":{"enter":{"x":{"scale":"x","field":"data.x"},"y":{"scale":"y","field":"data.y"},"interpolate":{"value":"step-before"},"fill":{"value":"steelblue"},"fillOpacity":{"value":0.4},"stroke":{"value":"steelblue"},"strokeWidth":{"value":2},"strokeOpacity":{"value":1}}}}],"scales":[{"name":"x","type":"linear","range":"width","zero":false,"domain":{"data":"6c7d7c9a-979f-41ed-9277-edd3dcac7833","field":"data.x"}},{"name":"y","type":"linear","range":"height","nice":true,"zero":false,"domain":{"data":"6c7d7c9a-979f-41ed-9277-edd3dcac7833","field":"data.y"}}],"axes":[{"type":"x","scale":"x"},{"type":"y","scale":"y"}]},"value":"#gorilla_repl.vega.VegaView{:content {:width 400, :height 247.2188, :padding {:top 10, :left 55, :bottom 40, :right 10}, :data [{:name \"6c7d7c9a-979f-41ed-9277-edd3dcac7833\", :values ({:x 10.0, :y 0} {:x 11.555555555555555, :y 1.0} {:x 13.11111111111111, :y 8.0} {:x 14.666666666666666, :y 10.0} {:x 16.22222222222222, :y 43.0} {:x 17.77777777777778, :y 30.0} {:x 19.333333333333336, :y 56.0} {:x 20.888888888888893, :y 24.0} {:x 22.44444444444445, :y 21.0} {:x 24.000000000000007, :y 7.0} {:x 25.555555555555564, :y 0})}], :marks [{:type \"line\", :from {:data \"6c7d7c9a-979f-41ed-9277-edd3dcac7833\"}, :properties {:enter {:x {:scale \"x\", :field \"data.x\"}, :y {:scale \"y\", :field \"data.y\"}, :interpolate {:value \"step-before\"}, :fill {:value \"steelblue\"}, :fillOpacity {:value 0.4}, :stroke {:value \"steelblue\"}, :strokeWidth {:value 2}, :strokeOpacity {:value 1}}}}], :scales [{:name \"x\", :type \"linear\", :range \"width\", :zero false, :domain {:data \"6c7d7c9a-979f-41ed-9277-edd3dcac7833\", :field \"data.x\"}} {:name \"y\", :type \"linear\", :range \"height\", :nice true, :zero false, :domain {:data \"6c7d7c9a-979f-41ed-9277-edd3dcac7833\", :field \"data.y\"}}], :axes [{:type \"x\", :scale \"x\"} {:type \"y\", :scale \"y\"}]}}"}
;; <=

;; @@
;; P1.9
;; I get OutOfMemoryError when trying to solve the problem
;; @@

;; @@
;; P1.10
(let [n 10000
      get-5-cards #(take 5 (shuffle (range 52)))
      get-card-num #(rem % 13)
      one-pair? #(if (= 4 (count (frequencies (map get-card-num %)))) 1 0)]
  (double (mean (map one-pair? (repeatedly n get-5-cards)))))
;; 0.4238
;; @@

;; @@
;; P1.11
(let [n 10000
      get-5-cards #(take 5 (shuffle (range 52)))
      get-card-num #(rem % 13)
      two-pair? #(if (= [1 2 2] (sort (vals (frequencies (map get-card-num %))))) 1 0)]
  (double (mean (map two-pair? (repeatedly n get-5-cards)))))
;; 0.0455
;; @@

;; @@
;; P1.12
(defn craps
  []
  (let [get-roll-result #(+ 2 (rand-int 6) (rand-int 6))
        init-roll (get-roll-result)
        ]
    (cond
      (contains? #{7 11} init-roll) :WIN
      (contains? #{2 3 12} init-roll) :LOSE
      :else
      (let [res-mapping {7 :LOSE init-roll :WIN}]
        (first (drop-while nil? (map res-mapping (repeatedly get-roll-result))))))))

(frequencies (repeatedly 1000 craps))
;; {:LOSE 509, :WIN 491}
;; @@

;; @@
;; P1.13
;; 0.03 * 0.98 / ((1 - 0.03) * (1 - 0.98) + 0.03 * 0.98)
;; @@

;; @@
;; P1.14
(time (dotimes [_ 1000000] (rand)))
;; "Elapsed time: 75.439993 msecs"
(list-plot (repeatedly 50 #(rand)) :joined true)
;; @@
;; ->
;;; &quot;Elapsed time: 91.885351 msecs&quot;
;;; 
;; <-
;; =>
;;; {"type":"vega","content":{"width":400,"height":247.2187957763672,"padding":{"top":10,"left":55,"bottom":40,"right":10},"data":[{"name":"31e29190-47d4-4ca2-a008-7bc01c6d72b6","values":[{"x":0,"y":0.27907527345410665},{"x":1,"y":0.6378569986593405},{"x":2,"y":0.7927470088464076},{"x":3,"y":0.6820203911837813},{"x":4,"y":0.9176362648557976},{"x":5,"y":0.6122997252760478},{"x":6,"y":0.8849552689466428},{"x":7,"y":0.8411650185305936},{"x":8,"y":0.6033404185001833},{"x":9,"y":0.5532813829651306},{"x":10,"y":0.19226264105020863},{"x":11,"y":0.39061300904659213},{"x":12,"y":0.1650030140332619},{"x":13,"y":0.07294551058998056},{"x":14,"y":0.0953673568524338},{"x":15,"y":0.526046782821984},{"x":16,"y":0.23467154049742556},{"x":17,"y":0.32522490693468853},{"x":18,"y":0.2555858109338126},{"x":19,"y":0.5620941849694683},{"x":20,"y":0.9397197263654794},{"x":21,"y":0.62352919752078},{"x":22,"y":0.43952295102163386},{"x":23,"y":0.8662967670955755},{"x":24,"y":0.6159612771200244},{"x":25,"y":0.07656069407240762},{"x":26,"y":0.6559154166472775},{"x":27,"y":0.733829170075213},{"x":28,"y":0.6223805024723559},{"x":29,"y":0.03306069192176253},{"x":30,"y":0.09198302491252208},{"x":31,"y":0.9512912715526296},{"x":32,"y":0.5343659570486856},{"x":33,"y":0.2366396826004754},{"x":34,"y":0.423537340313312},{"x":35,"y":0.4238707985495914},{"x":36,"y":0.2198717340250731},{"x":37,"y":0.98344676253167},{"x":38,"y":0.6912610799538624},{"x":39,"y":0.6474014412484767},{"x":40,"y":0.561721003411099},{"x":41,"y":0.24828466163285456},{"x":42,"y":0.08498561345414624},{"x":43,"y":0.9953175456321868},{"x":44,"y":0.8793044674082529},{"x":45,"y":0.6988789368897272},{"x":46,"y":0.6584579492757634},{"x":47,"y":0.7602331026517251},{"x":48,"y":0.4242165186064575},{"x":49,"y":0.7533609788096232}]}],"marks":[{"type":"line","from":{"data":"31e29190-47d4-4ca2-a008-7bc01c6d72b6"},"properties":{"enter":{"x":{"scale":"x","field":"data.x"},"y":{"scale":"y","field":"data.y"},"stroke":{"value":"#FF29D2"},"strokeWidth":{"value":2},"strokeOpacity":{"value":1}}}}],"scales":[{"name":"x","type":"linear","range":"width","zero":false,"domain":{"data":"31e29190-47d4-4ca2-a008-7bc01c6d72b6","field":"data.x"}},{"name":"y","type":"linear","range":"height","nice":true,"zero":false,"domain":{"data":"31e29190-47d4-4ca2-a008-7bc01c6d72b6","field":"data.y"}}],"axes":[{"type":"x","scale":"x"},{"type":"y","scale":"y"}]},"value":"#gorilla_repl.vega.VegaView{:content {:width 400, :height 247.2188, :padding {:top 10, :left 55, :bottom 40, :right 10}, :data [{:name \"31e29190-47d4-4ca2-a008-7bc01c6d72b6\", :values ({:x 0, :y 0.27907527345410665} {:x 1, :y 0.6378569986593405} {:x 2, :y 0.7927470088464076} {:x 3, :y 0.6820203911837813} {:x 4, :y 0.9176362648557976} {:x 5, :y 0.6122997252760478} {:x 6, :y 0.8849552689466428} {:x 7, :y 0.8411650185305936} {:x 8, :y 0.6033404185001833} {:x 9, :y 0.5532813829651306} {:x 10, :y 0.19226264105020863} {:x 11, :y 0.39061300904659213} {:x 12, :y 0.1650030140332619} {:x 13, :y 0.07294551058998056} {:x 14, :y 0.0953673568524338} {:x 15, :y 0.526046782821984} {:x 16, :y 0.23467154049742556} {:x 17, :y 0.32522490693468853} {:x 18, :y 0.2555858109338126} {:x 19, :y 0.5620941849694683} {:x 20, :y 0.9397197263654794} {:x 21, :y 0.62352919752078} {:x 22, :y 0.43952295102163386} {:x 23, :y 0.8662967670955755} {:x 24, :y 0.6159612771200244} {:x 25, :y 0.07656069407240762} {:x 26, :y 0.6559154166472775} {:x 27, :y 0.733829170075213} {:x 28, :y 0.6223805024723559} {:x 29, :y 0.03306069192176253} {:x 30, :y 0.09198302491252208} {:x 31, :y 0.9512912715526296} {:x 32, :y 0.5343659570486856} {:x 33, :y 0.2366396826004754} {:x 34, :y 0.423537340313312} {:x 35, :y 0.4238707985495914} {:x 36, :y 0.2198717340250731} {:x 37, :y 0.98344676253167} {:x 38, :y 0.6912610799538624} {:x 39, :y 0.6474014412484767} {:x 40, :y 0.561721003411099} {:x 41, :y 0.24828466163285456} {:x 42, :y 0.08498561345414624} {:x 43, :y 0.9953175456321868} {:x 44, :y 0.8793044674082529} {:x 45, :y 0.6988789368897272} {:x 46, :y 0.6584579492757634} {:x 47, :y 0.7602331026517251} {:x 48, :y 0.4242165186064575} {:x 49, :y 0.7533609788096232})}], :marks [{:type \"line\", :from {:data \"31e29190-47d4-4ca2-a008-7bc01c6d72b6\"}, :properties {:enter {:x {:scale \"x\", :field \"data.x\"}, :y {:scale \"y\", :field \"data.y\"}, :stroke {:value \"#FF29D2\"}, :strokeWidth {:value 2}, :strokeOpacity {:value 1}}}}], :scales [{:name \"x\", :type \"linear\", :range \"width\", :zero false, :domain {:data \"31e29190-47d4-4ca2-a008-7bc01c6d72b6\", :field \"data.x\"}} {:name \"y\", :type \"linear\", :range \"height\", :nice true, :zero false, :domain {:data \"31e29190-47d4-4ca2-a008-7bc01c6d72b6\", :field \"data.y\"}}], :axes [{:type \"x\", :scale \"x\"} {:type \"y\", :scale \"y\"}]}}"}
;; <=

;; @@
(list-plot (reductions + (repeatedly 50 #(rand))) :joined true)
;; @@
;; =>
;;; {"type":"vega","content":{"width":400,"height":247.2187957763672,"padding":{"top":10,"left":55,"bottom":40,"right":10},"data":[{"name":"3f53930f-3409-4bf7-8688-c148edcef44f","values":[{"x":0,"y":0.20900281962312384},{"x":1,"y":0.849852407247763},{"x":2,"y":1.5267622825549163},{"x":3,"y":1.879388425188755},{"x":4,"y":2.1790377924750395},{"x":5,"y":3.07616101483936},{"x":6,"y":3.631400459533767},{"x":7,"y":4.548480643186319},{"x":8,"y":5.2241867688137456},{"x":9,"y":5.934286303978855},{"x":10,"y":5.995090916221383},{"x":11,"y":6.031479224372763},{"x":12,"y":6.164911259613933},{"x":13,"y":6.5424601235157525},{"x":14,"y":6.877748641955906},{"x":15,"y":7.678941501682832},{"x":16,"y":7.914306584816721},{"x":17,"y":8.766820339600597},{"x":18,"y":9.343352234628515},{"x":19,"y":9.670719702731395},{"x":20,"y":9.80575439427116},{"x":21,"y":10.594859408168007},{"x":22,"y":11.367727473186827},{"x":23,"y":12.313954181891633},{"x":24,"y":12.48715161747375},{"x":25,"y":13.185512306692349},{"x":26,"y":13.93979699598358},{"x":27,"y":14.678009084582722},{"x":28,"y":15.516409426160308},{"x":29,"y":15.55311694486387},{"x":30,"y":16.183384144511944},{"x":31,"y":16.756310678807456},{"x":32,"y":16.90290010118161},{"x":33,"y":16.953442572665363},{"x":34,"y":17.240159974494286},{"x":35,"y":17.82515145242138},{"x":36,"y":18.31279743694662},{"x":37,"y":19.295474693843538},{"x":38,"y":19.543605197347382},{"x":39,"y":19.60665708101211},{"x":40,"y":20.11780350139627},{"x":41,"y":20.927669409292896},{"x":42,"y":21.803921519050256},{"x":43,"y":21.86227756880919},{"x":44,"y":22.5415835532228},{"x":45,"y":22.56694570694582},{"x":46,"y":23.139672129759024},{"x":47,"y":23.769361235825723},{"x":48,"y":24.471341866649876},{"x":49,"y":24.577771177968838}]}],"marks":[{"type":"line","from":{"data":"3f53930f-3409-4bf7-8688-c148edcef44f"},"properties":{"enter":{"x":{"scale":"x","field":"data.x"},"y":{"scale":"y","field":"data.y"},"stroke":{"value":"#FF29D2"},"strokeWidth":{"value":2},"strokeOpacity":{"value":1}}}}],"scales":[{"name":"x","type":"linear","range":"width","zero":false,"domain":{"data":"3f53930f-3409-4bf7-8688-c148edcef44f","field":"data.x"}},{"name":"y","type":"linear","range":"height","nice":true,"zero":false,"domain":{"data":"3f53930f-3409-4bf7-8688-c148edcef44f","field":"data.y"}}],"axes":[{"type":"x","scale":"x"},{"type":"y","scale":"y"}]},"value":"#gorilla_repl.vega.VegaView{:content {:width 400, :height 247.2188, :padding {:top 10, :left 55, :bottom 40, :right 10}, :data [{:name \"3f53930f-3409-4bf7-8688-c148edcef44f\", :values ({:x 0, :y 0.20900281962312384} {:x 1, :y 0.849852407247763} {:x 2, :y 1.5267622825549163} {:x 3, :y 1.879388425188755} {:x 4, :y 2.1790377924750395} {:x 5, :y 3.07616101483936} {:x 6, :y 3.631400459533767} {:x 7, :y 4.548480643186319} {:x 8, :y 5.2241867688137456} {:x 9, :y 5.934286303978855} {:x 10, :y 5.995090916221383} {:x 11, :y 6.031479224372763} {:x 12, :y 6.164911259613933} {:x 13, :y 6.5424601235157525} {:x 14, :y 6.877748641955906} {:x 15, :y 7.678941501682832} {:x 16, :y 7.914306584816721} {:x 17, :y 8.766820339600597} {:x 18, :y 9.343352234628515} {:x 19, :y 9.670719702731395} {:x 20, :y 9.80575439427116} {:x 21, :y 10.594859408168007} {:x 22, :y 11.367727473186827} {:x 23, :y 12.313954181891633} {:x 24, :y 12.48715161747375} {:x 25, :y 13.185512306692349} {:x 26, :y 13.93979699598358} {:x 27, :y 14.678009084582722} {:x 28, :y 15.516409426160308} {:x 29, :y 15.55311694486387} {:x 30, :y 16.183384144511944} {:x 31, :y 16.756310678807456} {:x 32, :y 16.90290010118161} {:x 33, :y 16.953442572665363} {:x 34, :y 17.240159974494286} {:x 35, :y 17.82515145242138} {:x 36, :y 18.31279743694662} {:x 37, :y 19.295474693843538} {:x 38, :y 19.543605197347382} {:x 39, :y 19.60665708101211} {:x 40, :y 20.11780350139627} {:x 41, :y 20.927669409292896} {:x 42, :y 21.803921519050256} {:x 43, :y 21.86227756880919} {:x 44, :y 22.5415835532228} {:x 45, :y 22.56694570694582} {:x 46, :y 23.139672129759024} {:x 47, :y 23.769361235825723} {:x 48, :y 24.471341866649876} {:x 49, :y 24.577771177968838})}], :marks [{:type \"line\", :from {:data \"3f53930f-3409-4bf7-8688-c148edcef44f\"}, :properties {:enter {:x {:scale \"x\", :field \"data.x\"}, :y {:scale \"y\", :field \"data.y\"}, :stroke {:value \"#FF29D2\"}, :strokeWidth {:value 2}, :strokeOpacity {:value 1}}}}], :scales [{:name \"x\", :type \"linear\", :range \"width\", :zero false, :domain {:data \"3f53930f-3409-4bf7-8688-c148edcef44f\", :field \"data.x\"}} {:name \"y\", :type \"linear\", :range \"height\", :nice true, :zero false, :domain {:data \"3f53930f-3409-4bf7-8688-c148edcef44f\", :field \"data.y\"}}], :axes [{:type \"x\", :scale \"x\"} {:type \"y\", :scale \"y\"}]}}"}
;; <=

;; @@
;; P1.24
;; p(stick with original choice) = 1/3
;; p(switch) = 2/3
;; @@

;; @@
;; P1.27
;; so what's the subtle problem?
(defn rng_prob
  []
  (loop [n 10000000
         low 0
         high 0]
    (if (zero? n)
      [low high]
      (if (< (rand) 0.1)
        (do #_(rand)
            (recur (dec n) (inc low) high))
        (do #_(rand)
            #_(rand)
            (recur (dec n) low (inc high)))))))
;; @@
