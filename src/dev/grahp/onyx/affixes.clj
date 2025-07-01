(ns dev.grahp.onyx.affixes
  (:require [dev.grahp.onyx.dict :refer [gen-dict]]))

(def affixes {"+" "{*!}"
              "A" "{*-|}"
              "A+" "{*-|}{*!}"

              "-r" "{^er}"
              "-d" "{^ed}"
              "-s" "{^s}"
              "-rs" "{^er}{^s}"
              "-rd" "{^y}"
              "-ds" "{^ing}"
              "-rds" "{^y}{^s}"

              "+-r" "{*!}{^er}"
              "+-d" "{*!}{^ed}"
              "+-s" "{*!}{^s}"
              "+-rs" "{*!}{^er}{^s}"
              "+-rd" "{*!}{^y}"
              "+-ds" "{*!}{^ing}"
              "+-rds" "{*!}{^y}{^s}"

              "A-r" "{*-|}{^er}"
              "A-d" "{*-|}{^ed}"
              "A-s" "{*-|}{^s}"
              "A-rs" "{*-|}{^er}{^s}"
              "A-rd" "{*-|}{^y}"
              "A-ds" "{*-|}{^ing}"
              "A-rds" "{*-|}{^y}{^s}"

              "A+-r" "{*-|}{*!}{^er}"
              "A+-d" "{*-|}{*!}{^ed}"
              "A+-s" "{*-|}{*!}{^s}"
              "A+-rs" "{*-|}{*!}{^er}{^s}"
              "A+-rd" "{*-|}{*!}{^y}"
              "A+-ds" "{*-|}{*!}{^ing}"
              "A+-rds" "{*-|}{*!}{^y}{^s}"})

(gen-dict "onyx-affixes.json" affixes)
