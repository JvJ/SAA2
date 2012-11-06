(ns misc.compscript)

;;; Three step process
;;; Load this file in REPL (STRL+Alt+S)
;;; Execute (misc.compscript/runcompile) in REPL
;;; Refresh project

(defn runcompile
  []
  (load-file "src/rdl/jpl.clj")
  (load-file "src/rdl/rule.clj")
  (load-file "src/rdl/interop/RDLRule.clj")
  (compile 'rdl.interop.RDLRule)
  (load-file "src/rdl/interop/RDLRelation.clj")
  (compile 'rdl.interop.RDLRelation)
  (load-file "src/rdl/interop/RDLInterface.clj")
  (compile 'rdl.interop.RDLInterface))