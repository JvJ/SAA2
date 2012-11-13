(ns rdl.interop.RDLInterface
  (:use [rdl.jpl]
        [rdl.rule]
        [rdl.reflect]
        [rdl.interop.RDLRelation]
        [rdl.interop.RDLRule])
  (:gen-class
    :state state
    :init init
    :methods [
              [loadFile [String] void]
              
              [defRel [Class] rdl.interop.RDLRelation]
              [defRel [String] rdl.interop.RDLRelation]
              [defRel [String "[Ljava.lang.Object;"] rdl.interop.RDLRelation]
              
              [bindVar [String Object] Object]
              
              [defRule [String] rdl.interop.RDLRule]
              [defRule [String "[Ljava.lang.Object;"] rdl.interop.RDLRule]
              
              [pList ["[Ljava.lang.Object;"] Object]
              
              [trans [String String Object "[Ljava.lang.Object;"] Object]
              
              [query ["[Ljava.lang.Object;"] "[Lclojure.lang.IPersistentMap;"]
              
              [updateHead [] void]
              [updateTail [] void]
              ]
    :constructors {[] []}
    ))


(defn -init
  "The initializer."
  []
  [[] (atom {})])

(defn -loadFile
  [this s]
  (load-file s))

(defn -defRel
  "Evaluates a relation definition and returns a new Relation object."
  ([this st]
    
    ;; Looks like we have to handle dynamic dispatch on our own here!
    (cond
      ;; Is it a string?
      (isa? (class st) String) (let [evaled (eval (read-string st))]
                                 (apply defrel evaled)
                                 (rdl.interop.RDLRelation. (str (first evaled))))
      
      ;; Is it a class?
      (isa? (class st) Class) (let [class-sym (defrel-bean st)]
                                (rdl.interop.RDLRelation. (name class-sym)))))
      
  ([this st args]
    (defrel (symbol st) (map keyword args))
    (rdl.interop.RDLRelation. st)))

(defn -bindVar
  "Binds a variable to a term and executes the term."
  [this vr term]
  (when-not (seq? term)
    (throw (Exception. "Expected list.  Received " term)))
  (bind-var (read-string vr) term))
  
  
(defn -defRule
  ([this st]
    (let [evaled (eval (read-string st))]
      (apply defrel evaled)
      (rdl.interop.RDLRule. (str (first evaled)))))
  ([this st args]
    (apply defrule
           (symbol st)
           (map #(if (string? %) (eval (read-string %)) %) args))
    (rdl.interop.RDLRule. st)))

(defn -pList
  "Create a prolog list."
  [this args]
  (map #(if (string? %) (read-string %) %) args))

(defn -trans
  [this kw1 kw2 func args]
  (vec
    (concat 
      [[(read-string kw1) (read-string kw2)] 
       (if (string? func)
         (eval (read-string func))
         func)]
      args)))
  
(defn -query
  "Execute a query.  This should take an array of clojure lists.  It will return
an array of clojure maps"
  [this args]
  (into-array clojure.lang.IPersistentMap
              (map bean-post-process (apply query args))))

(defn -updateHead
  "Execute for the first pass of the update."
  [this]
  (update-head))

(defn -updateTail
  "Execute for the second pass of the update."
  [this]
  (update-tail))
  
  