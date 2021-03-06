(use 'rdl.jpl)
(use 'rdl.rule)



;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defrel 'agent [:SELF])
;;(defrel 'emotion [:SELF :ANGER :SADNESS :HAPPINESS])
(defrel 'emotiontrsh [:SELF :ANGERTRESH :SADNESSTRESH :HAPPINESSTRESH])
(defrel 'cry [:SELF])

(defrule 'crying 
  (rel 'agent{:SELF :X})
  (rel 'Emotion {:agent :X :anger :C :sadness :Z :happiness :S})
  (rel 'emotiontrsh {:SELF :X :ANGERTRESH :E :SADNESSTRESH :Q :HAPPINESSTRESH :D})
  '(< :Q :Z)
  :==>
  (assert-rel 'cry {:SELF :X}))
(defrel 'smiling [:SELF])
(defrule 'halfhappy
  (rel 'Emotion{:agent :X :anger :C :sadness :Z :happiness :S})
   :==>
   [[:S :S2] / 2]
   :==>
   (mod-rels 'Emotion{:agent :X}
             {:happiness :S2})
  )

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defrel 'goalweight [:AGENT :GOAL :WEIGHT])
(defrel 'goalsatistank[:AGENT :GOAL :VALUESATIS])
(defrel 'goalmain[:GOAL :SATISMAIN :DECAYRATE])
(defrel 'goaldecay[:AGENT :GOAL :DECAY])
(defrel 'desire[:GOAL :AGENT :VALUE])
(defrel 'agentgoal[:GOAL :AGENT :STATE])

(defrule 'desireassign
  (rel 'agentgoal{:GOAL :G :AGENT :A :STATE :S})
  (rel 'goalsatistank{:AGENT :A :GOAL :G :VALUESATIS :VS})
  (rel 'goalweight {:AGENT :A :GOAL :G :WEIGHT :W})
  (rel 'desire{:GOAL :G :AGENT :A :VALUE :VX})
  :==>
  ;;modify vx=(vs-s)*w
  ;;{:VX1 (fn [m] (* (- (m :VS) (m :S)) (m :W)) )}
  [[:VS :V1]- :S]
  [[:V1 :V2]* :W]
  

  :==>
   (mod-rels 'desire {:GOAL :G :AGENT :A :VALUE :VX} {:VALUE  :V2})
  )




;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defrel 'mother [:SELF :CHILD])
(defrel 'grandmother [:SELF :GCHILD])

(defrule 'granny
  (rel 'mother {:SELF :X :CHILD :Y})
  (rel 'mother {:SELF :Y :CHILD :Z})
  :==>
  (assert-rel 'grandmother {:SELF :X :GCHILD :Z}))
  
;;(query
;;  (assert-rel 'mother {:SELF 'sue :CHILD 'kaylen}))
(defrel 'grab [:SELF :OBJECT])



;;(defrel 'emotionbias [:SELF :OPENNESS :CONSC :AGREE])
;;(defrel 'actionaffect [:SELF :DESIREABILITY :DESIREABILITYFOROTHER])

;;(defrel 'place[:SELF :CITY])
(defrel 'owner [:SELF :OBJECT] )
(defrel 'beingtheif [:SELF])
(defrule 'thief 
  (rel 'grab{:SELF :X :OBJECT :Y})
  (rel 'owner{:SELF :Z :OBJECT :Y  })
  '(!= :X :Y)
  :==>
  (assert-rel 'beingtheif {:SELF :X})
)

(defrel 'sadness [:SELF :VALUE])

(defrel 'smack [:INST :TARG])
(defrel 'emotions [:SELF :ANGER ])
;;(defrel 'emotions [:SELF :ANGER :GRAT :HAPPY :SAD :HOP :FEAR ])
(query
  (assert-rel 'emotions{:SELF 'sue :ANGER 1.0 }))
(defrel 'action[:ACTION])
(defrule 'getsmacked
  (rel 'smack {:INST :I :TARG :T})
  (rel 'emotions {:SELF :T :ANGER :A})
  :==>
  [[:A :A1] + 0.1]
  :==>
  (mod-rels 'emotions {:SELF :T} {:ANGER :A1})
  (retract-rel 'smack {:INST :I :TARG :T}))

(defrel 'emotional[:SELF :VALUE])
(defrel 'smiling [:SELF])

;;(defrule 'crying
  ;; (rel 'sadness {:SELF :T :VALUE :V})
   ;;(rel 'emotional {:SELF :T :VALUE :W })  
   ;;'(< (+ :V :W) 1.0)
    ;; :==>
     ;;(assert-rel 'cry {:SELF :T})
    ;; )


(defrel 'punch[:SELF :TARGET :TYPE])
(defrel 'affect [:DESIREABILITY :DESIREABILITYFOROTHER :TYPE])

(defrule 'actionaffect
  (rel 'punch{:SELF :X :TARGET :Y :TYPE :Z})
  (rel 'affect{:DESIREABILITY :X :DESIREABILITYFOROTHER :E :TYPE :Z})
  (rel 'emotion{:SELF :Y :ANGER :C :SADNESS :Z :HAPPINESS :S})
  :==>
  [[:S :S] + 10]
 ;; (fn [m] (assoc m :S (+ (m :S1) (m :E))))
  ;;(fn [m] (println "Here's the map: " m) m)
  :==>
  (mod-rels 'emotion{:SELF :Y  :SADNESS :Z :ANGER :C} {:HAPPINESS :S})
  )

  ;;(assert-rel ')

