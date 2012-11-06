(ns rdl.interop.RDLRelation
  (:use [rdl.jpl]
        [rdl.rule])
  (:gen-class
    :prefix "relt-"
    :init init
    :state state
    :methods [
              [getName [] String]
              [getFields [] "[Ljava.lang.Object;"]
              [term [ "[Ljava.lang.Object;"] Object]
              [assertRel [ "[Ljava.lang.Object;"] Object]
              [retractRel [ "[Ljava.lang.Object;"] Object]
              [modRel ["[Ljava.lang.Object;"] Object]
              ]
    :constructors {[String] []}))

(defn relt-init
  "The constructor!"
  [s]
  [[] [(symbol s) (@*relations* (symbol s))]])

(defn relt-toString
  [this]
  (str (.state this)))

(defn relt-getName
  [this]
  (str (first (.state this))))

(defn relt-getFields
  [this]
  (map name (:unfields (second (.state this)))))

(defn term-retract-assert
  [this f objs]
  (let [res 
  (f (first (.state this))
     (apply hash-map
            (mapcat
              #(vector (keyword (first %))
                       (if (string? (second %))
                         (read-string (second %))
                         (second %)))
              (partition 2 objs))))
     ]
    res))

(defn relt-term
  [this objs]
  (term-retract-assert this rel objs))

(defn relt-assertRel
  [this objs]
  (term-retract-assert this assert-rel objs))

(defn relt-retractRel
  [this objs]
  (term-retract-assert this retract-rel objs))

(defn relt-modRel
  [this objs]
  (let [parts (partition-by #(= ":==>" %) objs)
        splitter #(vector (keyword (first %)) (if (string? (second %)) (read-string (second %)) (second %)))
        map1 (apply hash-map (mapcat splitter (partition 2 (first parts))))
        map2 (apply hash-map (mapcat splitter (partition 2 (second (rest parts)))))
        ]
    (mod-rels
      (first (.state this))
      map1
      map2)))

 
       