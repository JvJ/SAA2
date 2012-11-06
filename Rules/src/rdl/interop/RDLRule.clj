(ns rdl.interop.RDLRule
  (:use [rdl.jpl]
        [rdl.rule])
  (:gen-class
    :prefix "rl-"
    :init init
    :state state
    :methods [
              [getName [] String]
              ]
    :constructors {[String] []}))

(defn rl-init
  "The constructor!"
  [s]
  [[] [(symbol s) (@*rules* (symbol s))]])

(defn rl-getName
  [this]
  (str (first (.state this))))

(defn rl-toString
  [this]
  (str (.state this)))

