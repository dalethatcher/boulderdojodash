(ns boulder-dash.core
	(:use quil.core))

(defn setup []
  (smooth)                          ;;Turn on anti-aliasing
  (frame-rate 1)                    ;;Set framerate to 1 FPS
  (background 0))                 ;;Set the background colour to
                                    ;;  a nice shade of grey.
(def example-map
  [[:e :e :e :e :e :e :e]
   [:e :r :e :e :e :r :e]
   [:m nil :e :e :r :e :e]

   [:e :r :e :e :e :r :e]
   [:e :r :e :r :e :e :e]
   [:r :e :e :e :r :e :r]
   [:e :r :e :r :e :r :e]
   [:e :r :e :r :e :e :e]
   [:e :r :e :e :e :r :e]
   [:r :e :e :e :r :e :r]
   [:e :r :e :r :e :r :e]
])

(defn square-color[]
   {:e (load-image "data/earth.png")
    :r (load-image "data/boulder.png")
    :m (load-image "data/man.png")})


(defn coordinate-y [f matrix]
  (mapcat f (iterate inc 0) matrix))

(defn coordinate-x [coord-y row]
  (map (fn[x y] [coord-y x y]) (iterate inc 0) row))

(defn draw-square [[x y type]]
  (let [xScreen (* x 26)
	yScreen (* y 26)
	screenColor ((square-color) type)]
    (if screenColor (image screenColor xScreen yScreen))
    ;(apply fill screenColor)
    ;(rect xScreen yScreen 26 26)
))

(defn draw []
  (let [coordinates-colors (coordinate-y coordinate-x example-map)]
     (doall (map draw-square coordinates-colors))
;(println coordinates-colors )
;    (draw-square 0 0 :m)
;    (draw-square 1 0 :e)
;    (draw-square 2 0 :r)
))

(defsketch example                  ;;Define a new sketch named example
  :title "Oh so many grey circles"  ;;Set the title of the sketch
  :setup setup                      ;;Specify the setup fn
  :draw draw                        ;;Specify the draw fn
  :size [323 200])                  ;;You struggle to beat the golden ratio

(defn -main
  "I don't do a whole lot."
  [& args]
  (println "Hello, World!"))
