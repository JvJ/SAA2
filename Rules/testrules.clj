(use 'rdl.jpl)
(use 'rdl.rule)

(defrel 'smack [:INST :TARG])
(defrel 'anger [:SELF :VAL])

(defrule
  'getsmacked
  (rel 'smack {:INST :X :TARG :Y})
  (rel 'anger {:SELF :Y :VAL :V})
  :==>
  [[:V :V1] + :V]
  (fn [m]
    (println "Current map : " m)
    m) ; This is just a debug function
  :==>
  (mod-rels 'anger {:SELF :Y :VAL :V} {:VAL :V1}))

(query
  (assert-rel 'anger {:SELF 'johnny :VAL 0.1})
  (assert-rel 'smack {:INST 'ace :TARG 'johnny}))