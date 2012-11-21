(use 'rdl.jpl)
(use 'rdl.rule)

(defrel 'mother[:SELF :CHILD])
(defrel 'wolf[:SELF :WOLF])
(defrel 'hunter[:SELF])
(defrel 'hunger [:SELF :VALUE])
(defrel 'searchfood[:SELF])
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
(defrule 'happiness-half
  (rel 'Emotion {:agent :A, :happiness :H, :anger :Ang, :sadness :Sad})
  '(> :H 0)
  :==>
  [[:H :H2] / 2]
  (fn [m] (println "m" m))
  :==>
  (mod-rels 'Emotion {:agent :A  :anger :Ang, :sadness :Sad} {:happiness :H2})
  )
(defrule 'getFood
  (rel 'hunger{:SELF :S :VALUE :V})
  '(<v 20)
  :==>
  assert-rel 'searchfood {:SELF :S }
  
  )
