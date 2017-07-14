;; gorilla-repl.fileformat = 1

;; @@
(ns emcm.chapter1
  (:require [clojure.core.matrix.random :refer [sample-uniform]]
            [clojure.core.matrix :as m]
            [gorilla-plot.core :refer [histogram list-plot]]))
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
        n-hits (apply + (repeatedly N hit?))]
    (double (/ N n-hits))))

(let [r 2000
      N 100
      samples (take r (repeatedly #(buffon-needle-problem N)))]
  (histogram samples :plot-range [[1.0 5.0] :all]))
;; @@
;; =>
;;; {"type":"vega","content":{"width":400,"height":247.2187957763672,"padding":{"top":10,"left":55,"bottom":40,"right":10},"data":[{"name":"dff89ad9-7447-47a0-802c-cce178b0c0e3","values":[{"x":1.0,"y":0},{"x":1.3333333333333335,"y":0.0},{"x":1.666666666666667,"y":0.0},{"x":2.0000000000000004,"y":1.0},{"x":2.333333333333334,"y":20.0},{"x":2.6666666666666674,"y":187.0},{"x":3.000000000000001,"y":508.0},{"x":3.3333333333333344,"y":658.0},{"x":3.666666666666668,"y":257.0},{"x":4.000000000000001,"y":260.0},{"x":4.333333333333334,"y":35.0},{"x":4.666666666666667,"y":42.0},{"x":5.0,"y":13.0},{"x":5.333333333333333,"y":14.0},{"x":5.666666666666666,"y":0}]}],"marks":[{"type":"line","from":{"data":"dff89ad9-7447-47a0-802c-cce178b0c0e3"},"properties":{"enter":{"x":{"scale":"x","field":"data.x"},"y":{"scale":"y","field":"data.y"},"interpolate":{"value":"step-before"},"fill":{"value":"steelblue"},"fillOpacity":{"value":0.4},"stroke":{"value":"steelblue"},"strokeWidth":{"value":2},"strokeOpacity":{"value":1}}}}],"scales":[{"name":"x","type":"linear","range":"width","zero":false,"domain":[1.0,5.0]},{"name":"y","type":"linear","range":"height","nice":true,"zero":false,"domain":{"data":"dff89ad9-7447-47a0-802c-cce178b0c0e3","field":"data.y"}}],"axes":[{"type":"x","scale":"x"},{"type":"y","scale":"y"}]},"value":"#gorilla_repl.vega.VegaView{:content {:width 400, :height 247.2188, :padding {:top 10, :left 55, :bottom 40, :right 10}, :data [{:name \"dff89ad9-7447-47a0-802c-cce178b0c0e3\", :values ({:x 1.0, :y 0} {:x 1.3333333333333335, :y 0.0} {:x 1.666666666666667, :y 0.0} {:x 2.0000000000000004, :y 1.0} {:x 2.333333333333334, :y 20.0} {:x 2.6666666666666674, :y 187.0} {:x 3.000000000000001, :y 508.0} {:x 3.3333333333333344, :y 658.0} {:x 3.666666666666668, :y 257.0} {:x 4.000000000000001, :y 260.0} {:x 4.333333333333334, :y 35.0} {:x 4.666666666666667, :y 42.0} {:x 5.0, :y 13.0} {:x 5.333333333333333, :y 14.0} {:x 5.666666666666666, :y 0})}], :marks [{:type \"line\", :from {:data \"dff89ad9-7447-47a0-802c-cce178b0c0e3\"}, :properties {:enter {:x {:scale \"x\", :field \"data.x\"}, :y {:scale \"y\", :field \"data.y\"}, :interpolate {:value \"step-before\"}, :fill {:value \"steelblue\"}, :fillOpacity {:value 0.4}, :stroke {:value \"steelblue\"}, :strokeWidth {:value 2}, :strokeOpacity {:value 1}}}}], :scales [{:name \"x\", :type \"linear\", :range \"width\", :zero false, :domain [1.0 5.0]} {:name \"y\", :type \"linear\", :range \"height\", :nice true, :zero false, :domain {:data \"dff89ad9-7447-47a0-802c-cce178b0c0e3\", :field \"data.y\"}}], :axes [{:type \"x\", :scale \"x\"} {:type \"y\", :scale \"y\"}]}}"}
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

;; @@
