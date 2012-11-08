(use 'rdl.jpl)
(use 'rdl.rule)

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

