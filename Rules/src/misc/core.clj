(ns misc.core)

;;; Some utility functions
(defn ><
  "Flips order of arguments for f."
  [f]
  #(apply f (reverse %&)))


;;; Standard aliases
(defn $
  "Standard function exectution operator."
  [f & args] (apply f args))
(def >$< (>< $))
(def $- partial)
(def $> apply)
(def $+ comp)
(def +$ (>< $+))

(def car first)
(def cdr rest)
(def caar ($+ car car))
(def cadr ($+ car cdr))
(def cdar ($+ cdr car))
(def cddr ($+ cdr cdr))
(def caaar ($+ car car car))
(def caadr ($+ car car cdr))
(def cadar ($+ car cdr car))
(def caddr ($+ car cdr cdr))
(def cdaar ($+ cdr car car))
(def cdadr ($+ cdr car cdr))
(def cddar ($+ cdr cdr car))
(def cdddr ($+ cdr cdr cdr))
(def caaaar ($+ car car car car))
(def caaadr ($+ car car car cdr))
(def caadar ($+ car car cdr car))
(def caaddr ($+ car car cdr cdr))
(def cadaar ($+ car cdr car car))
(def cadadr ($+ car cdr car cdr))
(def caddar ($+ car cdr cdr car))
(def cadddr ($+ car cdr cdr cdr))
(def cdaaar ($+ cdr car car car))
(def cdaadr ($+ cdr car car cdr))
(def cdadar ($+ cdr car cdr car))
(def cdaddr ($+ cdr car cdr cdr))
(def cddaar ($+ cdr cdr car car))
(def cddadr ($+ cdr cdr car cdr))
(def cdddar ($+ cdr cdr cdr car))
(def cddddr ($+ cdr cdr cdr cdr))

