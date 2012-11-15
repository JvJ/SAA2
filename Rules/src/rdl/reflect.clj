(ns rdl.reflect
  (:use [rdl.jpl]))

;;;; This namespace allows conversion of Java classes
;;;; into relation objects.

(def #^:dynamic *relation-classes*
  "This is a map from relation names to classes."
  (atom {}))

(defn bean-type-filter
  "Filters types from java to clojure."
  [obj]
  (cond
    (string? obj) (symbol obj)
    :else obj))

(defn clean-bean
  "Produces a bean from a java object with no class attribute."
  [obj]
  (->>
    (for [[k v] (dissoc (bean obj) :class)]
      [k (bean-type-filter v)])
    (into {})))
  
(defn short-name
  "Gets the short name of a class (after all the .s)."
  [clazz]
  (apply str (last (partition-by #{\.} (.getName clazz)))))   

(defn type-filter
  "Filters types from clojure to java."
  [typ obj]
  (cond
    (and (= typ Integer/TYPE) (integer? obj)) (Integer. obj)
    (and (= typ Float/TYPE) (float? obj)) (Float. obj)
    (= (class obj) clojure.lang.Symbol) (name obj)
    :else obj))

(defn unbean
  "Just a helper function"
  [clazz props]
  (let [x (.newInstance clazz)
        pmap (reduce (fn [m pd]
                       (let [name (.getName pd)
                             method (.getWriteMethod pd)
                             ]
                         
                         (if (and method (= 1 (alength (. method (getParameterTypes)))))
                           (let [ptype (first (. method (getParameterTypes)))]
                                              (assoc m (keyword name)
                                                     (fn [v] (. method
                                                               (invoke
                                                                 x 
                                                                 (into-array Object
                                                                             [(type-filter ptype v)]))))))
                           m)))
                     {}
                     (.getPropertyDescriptors (java.beans.Introspector/getBeanInfo
                                                clazz)))
        ]
    ;; Execute them all!
    (doseq [[k v] pmap]
      (v (props k)))
    x))

(defn defrel-bean
  "Defines a bean relation from a class."
  [clazz]
  (let [class-name (short-name clazz)
        class-sym (symbol class-name)
        the-bean (clean-bean (.newInstance clazz))
        fields (keys the-bean)]
    (defrel class-sym fields)
    (swap! *relation-classes* assoc class-sym clazz)
    class-sym))

(defn rel-bean
  "Creates a relation term from a bean."
  [obj]
  (rel (symbol (short-name (class obj)))
              (clean-bean obj)))

(defn assert-bean
  "Asserts a relation from a bean."
  [obj]
  (assert-rel (symbol (short-name (class obj)))
              (clean-bean obj)))

(defn bean-post-process
  "Scans the results of queries for maps and converts them to beans if possible."
  [mp]
 (->>
   (map
    (fn [[k v]]
      (if-let [clazz (and (map? v) (meta v)
                          (@*relation-classes* ((meta v) :rel-name)))]
        [k (unbean clazz v)]
        [k v]))
    mp)
   (into {})))
        
        
    