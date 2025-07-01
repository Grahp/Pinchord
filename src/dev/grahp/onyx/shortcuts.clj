(ns dev.grahp.onyx.shortcuts
  (:require [dev.grahp.onyx.dict :refer [gen-dict]]
            [dev.grahp.onyx.symbols :as sym]))

(def intial-chord "*TL")
(def symbol-intial-chord "*TSL")

(def letters {"LRPKS" "a"
              "PT" "b"
              "K*" "c"
              "PKS" "d"
              "LRPK" "e"
              "PKT" "f"
              "PKTS" "g"
              "LS*" "h"
              "PK" "i"
              "LRPKTS*" "j"
              "K" "k"
              "L" "l"
              "PS" "m"
              "KS" "n"
              "LRPKTS" "o"
              "P" "p"
              "PT*" "q"
              "R" "r"
              "S" "s"
              "T" "t"
              "LRK" "u"
              "KTS" "v"
              "RP*" "w"
              "LRKS" "x"
              "LR*" "y"
              "TS*" "z"
              ;; Add F Keys
              "^LRK" "0"
              "^R" "1"
              "^RK" "2"
              "^K" "3"
              "^LR" "4"
              "^LRPK" "5"
              "^PK" "6"
              "^L" "7"
              "^LP" "8"
              "^P" "9"})

(def mods {"Y" #{"control"}
           "E" #{"shift"}
           "I" #{"alt"}
           "U" #{"super"}
           "YE" #{"control" "shift"}
           "YI" #{"control" "alt"}
           "YU" #{"control" "super"}
           "EI" #{"shift" "alt"}
           "EU" #{"shift" "super"}
           "IU" #{"alt" "super"}
           "YEI" #{"control" "shift" "alt"}
           "YEU" #{"control" "shift" "super"}
           "YIU" #{"control" "alt" "super"}
           "EIU" #{"shift" "alt" "super"}
           "YEIU" #{"control" "shift" "alt" "super"}})

(defn with-mod [combo mod]
  (str mod "(" combo ")"))

(defn shortcut [mods letter]
  (str "{#" (reduce with-mod letter mods) "}"))

(defn sym [sym symbol-chord alt-chord mods mod-chord]
  (when sym
    {(str symbol-intial-chord mod-chord symbol-chord alt-chord)
     (shortcut mods sym)}))

(gen-dict "onyx-shortcuts.json"
          (-> (into {} (for [[mod-chord mods] mods
                             [letter-chord letter] letters]
                         [(str intial-chord mod-chord letter-chord)
                          (shortcut mods letter)]))
              (merge (into {} (for [[mod-chord mods] mods
                                    [symbol-chord [sym1 sym2 sym3 sym4]] sym/special-symbols]
                                (merge (sym sym1 symbol-chord "" mods mod-chord)
                                       (sym sym2 symbol-chord "d" mods mod-chord)
                                       (sym sym3 symbol-chord "*" mods mod-chord)
                                       (sym sym4 symbol-chord "*d" mods mod-chord)))))))

