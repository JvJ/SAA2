(use 'rdl.jpl)
(use 'rdl.rule)

(defrel 'mother[:SELF :CHILD] )
(defrel 'danger[:SELF])
(defrel 'protect [:SELF :TARGET])
(defrule 'protection
  (rel 'mother{:SELF :S, :CHILD :C})
  (rel 'danger{:SELF :C})
  :==>
   ;;(assert-rel 'cry {:SELF :X})
  (assert-rel 'protect {:SELF :S :TARGET :C})
  )
(defrel 'agent [:SELF])
(defrel 'goalweight [:GOAL :AGENT :WEIGHT])
(defrel 'goalsatistank[:GOAL :AGENT :VALUESTATS] )
(defrel 'goalmain[:GOAL :STATSMAIN :DECAYRATE])
(defrel 'goaldecay[:AGENT :GOAL :DECAY])
(defrel 'desire[:GOAL :AGENT :VALUE])
(defrel 'agentgoal[:GOAL :AGENT :STATE])

(defrule 'desireassign 
  ( rel 'agent{:SELF :A})
  ( rel 'agentgoal{:GOAL :G :AGENT :A :STATE :S})
  ( rel 'goalsatistank{:GOAL :G :AGENT :A :VALUESTATS :VS})
  ( rel 'desire{:GOAL :G :AGENT :A :VALUE :V})
  ( rel 'goalweight{:GOAL :G :AGENT :A :WEIGHT :W})
  :==>
  [[:VS  :VN]- :S]
  [[:VN :VN2]* :W]
  :==>
  (mod-rels 'desire{:GOAL :G :AGENT :A :VALUE :V}{:VALUE :VN2})
   )
;;(defrel 'smack [:INST :TARG])
;;(defrel 'anger [:SELF :VAL])
;;(defrule
 ;;'getsmacked
 ;;#(list '= :Y *current-agent*) ;; This line binds :Y to the current agent!
 ;;(rel 'smack {:INST :X :TARG :Y})
 ;;(rel 'anger {:SELF :Y :VAL :V})
;; :==>
;; [[:V :V1] + :V]
;; :==>
;; (mod-rels 'anger {:SELF :Y :VAL :V} {:VAL :V1}))

(defrule 'happiness-half
  (rel 'Emotion {:agent :A, :happiness :H, :anger :Ang, :sadness :Sad})
  '(> :H 0)
  :==>
  [[:H :H2] / 2]
  (fn [m] (println "m" m))
  :==>
  (mod-rels 'Emotion {:agent :A  :anger :Ang, :sadness :Sad} {:happiness :H2}))
  ;;(mod-rels 'Emotion {:agent :A :happiness :H :anger :Ang, :sadness :Sad} {:happiness :H2}))

