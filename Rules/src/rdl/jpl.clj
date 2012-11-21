(ns rdl.jpl
  (:import (jpl Atom
                Compound
                JPL
                JPLException
                JRef
                PrologException
                Query
                Term
                Util
                Variable
                Version))
  (:use [clojure.set]
        [misc.core]))

(def Array-Type
  "Get the class of a java array for later use."
  (class (into-array Object [])))

(def ^:dynamic *relations*
  "This is a mapping of predicate names to mappings of argument names to positions.
Predicate names are represented as symbols.
The key :fields is used to map args to indices.
The key :unfields is the inverse of :fields."
  (atom {}))

(def ^:dynamic *modified-relations*
  "Keeps track of which relations have been modified recently."
  (atom #{}))

(def symbol-mappings
  {'_com_ ","
   '_el_ "[]"
   '!= "\\="})

(defn convert-symbol
  [sym]
  (let [st (symbol-mappings sym)]
    (if st st (name sym))))


(def clj-term)
(defn seq-to-comp
  [s]
  (if (empty? s)
    (Atom. "[]")
    (Compound.
      "."
      (into-array
        Term
        [(clj-term (first s))
         (seq-to-comp (rest s))]))))

(defn clj-term
  "Converts a Clojure term into prolog data types.
Clojure terms are just lists (and lists of lists).
Symbols map to atoms, and keywords map to variables."
  [val]
  (cond
    ;; If it is a function, executerize it!
    (fn? val) (clj-term (val))
    ;; First of all, if an element is a sequence, it is another term.
    (seq? val) (cond (empty? val) (throw (Exception. "Empty sequence not allowed!"))
                     :else (Compound. (convert-symbol (first val))
                                      (into-array Term (map clj-term (rest val)))))
    (symbol? val) (Atom. (convert-symbol val))
    (keyword? val) (Variable. (name val))
    (char? val) (jpl.Integer. (int val))
    (float? val) (jpl.Float. val)
    (integer? val) (jpl.Integer. val)
    (sequential? val) (seq-to-comp val)
    (isa? (class val)
          java.lang.Iterable) (seq-to-comp (vec val))
    (isa? (class val)
          Array-Type) (seq-to-comp (vec val))
    (nil? val) (Atom. "nil")
    :else (throw (Exception.
                   (str "Error converting value: " 
                        val " of type: " (class val)
                        " to prolog data type.")))))

(defn term-map
  "Convert a term representing a relation to a map representing the same relation."
  [term]
  (let [r (@*relations* (first term))
        ;; TODO: Throw an exception maybe at some point..?
        fk (filter keyword? term)
        _ (if-not (empty? fk)
            (throw
              (Exception. (str "Unbound variables in term: " term))))
        ]
    (if-not r
      nil
      (with-meta
        (reduce conj {}
              (map vector (r :unfields) (rest term)))
        {:rel-name (first term)}))))

(defn term-clj-1
  "Converts Prolog data types to native Clojure data types."
  [val]
  (let [cv (class val)]
    (cond
      (isa? cv Atom) (read-string (.name val))
      (isa? cv jpl.Integer) (.value val)
      (isa? cv jpl.Float) (.value val)
      (isa? cv Variable) (keyword (.name val))
      (isa? cv Compound) (cons (read-string (.name val))
                               (map term-clj-1 (.args val)))
      :else (throw (Exception.
                     (str "Error converting value: " val
                          " of type: " cv " to clojure data type."))))))

(defn term-clj-2
  "Postprocessing for term-clj."
  [term]
  (cond
    (seq? term) (let [f (first term)]
                  (cond
                    (@*relations* f) (term-map term)
                    (= f '.) (vec (cons (cadr term) (term-clj-2 (caddr term))))
                    :else term))
    :else term))
  
(def term-clj ($+ term-clj-2 term-clj-1))



(defn compose
  "Turns a list of terms into a compound term."
  [& terms]
  (with-meta
    (cond
      (empty? terms) (throw (Exception. "Empty terms not allowed"))
      (= 1 (count terms)) (do (first terms))
      (= 2 (count terms)) (do `(~'_com_ ~(first terms) ~(second terms)))
      :else `(~'_com_ ~(first terms)
                      ~(apply compose (rest terms))))
    (apply merge-with
           ;; Only execute set union if both elements are sets!
           #(if (and (set? %1) (set %2)) (clojure.set/union %1 %2) %2 )
           (map meta terms))))

(defn bind-var
  "Composes the execution of a term with the binding of a variable to the term."
  [vr term]
  (compose
    term
    (list '= vr term)))
  
  
(def query)
(defn defrel
  "Adds a relation definition to the *relations* map."
  [nme keys]
  (let [;;keys (cons :_ID_ keys)
        ;;res (every? (fn [k]
        ;;              (= (str k) (.toUpperCase (str k))))  keys)
        ;; Keys need to be all in uppercase letters!
        ;;_ (when-not res (throw (Exception. "Error.  All keys must be upper-case.")))
        kns (apply hash-map (mapcat vector keys (range))) ;; represents a map of keys to indices
        ]
    ;; Define the dynamic relation
    (query `(~'dynamic (~'/ ~nme ~(count keys))))
    (swap! *relations*
           assoc nme {:fields kns
                      ;; A map of indices to keys is best represented as a vector
                      :unfields (vec keys)})))

(defn rel
  "Convert relations to terms."
  [rl propmap]
  (let [unfields (get-in @*relations* [rl :unfields])
        terms (for [k unfields]
                (if (contains? propmap k) (propmap k) :_))]
    (with-meta
      `(~rl ~@terms)
      {:rels #{rl}})))

(defn assert-rel
  [rl propmap]
  (with-meta
    (compose
      `(~'not ~(rel rl propmap))
      `(~'assert ~(rel rl propmap)))
    {:rels #{rl}
     :mod-rels #{rl}
     :asserted #{{:relation rl, :props propmap}}}))
  
(defn retract-rel
  [rl propmap]
  (with-meta
    `(~'retract ~(rel rl propmap))
    {:rels #{rl}
     :mod-rels #{rl}
     :retracted #{ {:relation rl, :props propmap} }}))

(defn mod-rels
  "Modify a relation that satisfies a predicate."
  [rl propmap-1 propmap-2]
  (let [merged (merge propmap-1 propmap-2)
        fields (set (:unfields (@*relations* rl)))
        missing (difference fields (set (keys merged)))
        ;; For all missing fields, generate a symbol prefixed with V_
        missing-vars (into {} (for [x missing]
                                [x (keyword (gensym (str "V_" (name x) "_")))]))
        compt (compose
                (retract-rel rl (merge propmap-1 missing-vars))
                (assert-rel rl (merge merged missing-vars)))]
    (with-meta
      compt
      (assoc (meta compt) :modified #{{:relation rl
                                       :before propmap-1
                                       :after propmap-2}}))))
    


(defn jmap-map
  "Converts a java.util.Hashtable to a clojure hash map."
  [^java.util.Hashtable jmap]
  (when-not (nil? jmap)
    (let [keys (set (.keySet jmap))]
      (apply hash-map
             (mapcat (fn [k] [k (.get jmap k)]) keys)))))
  

(defn exec-query
  "Execute a query term."
  [q-term]
  (let [q (Query. q-term)
        jm (.allSolutions q)
        hts (map jmap-map (vec jm))
        ret (for [m hts]
              (->>
                (for [[k v] m]
                  [(keyword k) (term-clj v)])
                (into {})))
        ]
    ret))

(defn query
  "Compose the queries, transform them, then execute them.
Keep the meta of the composed query"
  [& terms]
  (let [compt (apply compose terms)
        met (meta compt)
        mods (:mod-rels met)]
    (swap! *modified-relations* union mods)
    (with-meta
      (exec-query (clj-term compt))
      met)))
  





    
                                                  
                 
                 
                 
    
    
    
    