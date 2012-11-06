(ns rdl.rule
  (:use [clojure.set]
        [rdl.jpl]))

(def ^:dynamic *rule-tags*
  "Maps of relations to sets of rules.
If the modified relation is in the *modified-relations* set, 
then those rules should be fired."
  (atom {}))

(def ^:dynamic *rules*
  "Map of rule names to rules."
  (atom {}))

(def ^:dynamic *update-fns*
  "Update functions queued up from rules."
  (atom ()))

(defn clear
  "Clear the modified relations."
  []
  (reset! *modified-relations* #{}))

(defn make-filter
  "Creates a filter from a given vector or function or map!
For instance, [[:X :Y] + 2] means that the value reprsented by :X
in a hash map should be incremented by 2 and re-associated in the
map as :Y.
Also, maps of keywords to functions bind the key as a variable to
the output of the associated function.
An argument that is a function returns itself."
  [arg]
  (cond
    
    (vector? arg) (let [[[kw1 kw2] f & args] arg
                        _ (when (some nil? [kw1 kw2 f])
                            (throw
                              (Exception.
                                (str "Cannot destructure transformer vector: " arg))))
                        _ (when (not-every? keyword? [kw1 kw2])
                            (throw
                              (Exception.
                                (str "Transformer vector args must be keywords: " [kw1 kw2]))))
                        _ (when-not (ifn? f)
                            (throw
                              (Exception.
                                (str "The following value is not a function: " f))))
                        ]
                    (fn [hm]
                      (assoc hm kw2 (apply f (hm kw1) args))))
    
    (map? arg) (let [_ (when-not (every? keyword? (keys arg))
                         (throw (Exception. "Every key in transformer map must be a keyword.")))
                     _ (when-not (every? ifn? (vals arg))
                         (throw (Exception. "Every value in transformer map must be a function.")))]
                 (fn [hm]
                   (println "Got arg: " hm)
                   (reduce
                     (fn [acc [k v]]
                       (assoc acc k (v acc)))
                     hm
                     hm))); LEFTOFF: Test this!
    
    (ifn? arg) arg
    
    :else (throw (Exception. (str "The hell is this: " arg)))))
                         

(defn defrule
  "Create a set of rules from a set of clojure terms."
  [nme & terms]
  (let [splits (partition-by #(= % :==>) terms)
        _ (when-not (or (= 5 (count splits)) (= 3 (count splits)))
            (throw (Exception. (str "Invalid rule def: \n" terms))))
        
        ;; Are the filters present?
        filters? (= 5 (count splits))
        
        precon (apply compose (first splits))
        precon-meta (meta precon)
        
        ;; Filters may or may not be present
        filters (if filters? (map make-filter (nth splits 2)) ())
        
        ;; Effect has a different index depending on whether
        ;; or not filters are present.
        effect (apply compose
                      (if filters?
                        (nth splits 4)
                        (nth splits 2)))
        effect-meta (meta effect)
        
        _ (when-not (empty? (:mod-rels precon-meta))
            (throw (Exception. "Preconditions cannot have side effects!")))
        ;; Enter the tags and the rules in the global maps
        rels (:rels precon-meta)
        
        ;; Change the map values to sets instead of symbols
        _ (swap! *rule-tags* (partial merge-with union) (into {}
                                                              (for [r rels]
                                                                [r #{nme}])))
        ;; Relations modified by the effects.
        mod-rels (:mod-rels effect-meta)
        
        ;; This is the function itself
        retfn
        (fn []
          (let [res (exec-query (clj-term precon))
               ;; _ (println "res: " res)
                
                ;; Execute the filters!
                newres (map (fn [r]
                              ;;(println "received r!" r)
                              (reduce #(%2 %1) r filters)) res)
                
                ;;_ (println "filters: " filters)
                ;;_ (println "newres: " newres)
                ;; We need to bind variables from the previous results
                eq-clauses (map (fn [m]
                                  (for [[k v] m]
                                    `(~'= ~k ~v))) newres)]
            (fn []
              (swap! *modified-relations* union mod-rels)
              (doseq [bind-list eq-clauses]
                (apply query
                       (concat bind-list [effect]))))))
        
        _ (swap! *rules* conj [nme retfn])
        ]
    retfn))

(defn update-head
  "Returns a set of update functions."
  []
  
  (->>
    (for [rel @*modified-relations*]
      (for [sym (@*rule-tags* rel)]
        ((@*rules* sym))))
    (apply concat)
    (#(do (println %) %))
    (reset! *update-fns*))
  
  (reset! *modified-relations* #{}))
  

(defn update-tail
  "Takes a set of update functions and executes them."
  []
  (doseq [f @*update-fns*]
    (f)))