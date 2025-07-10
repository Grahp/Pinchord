(ns dev.grahp.onyx.numbers
  (:require [dev.grahp.onyx.dict :refer [gen-dict]]))

(def initial-chord "*TPL")

(def numpad1 {"" ""
              "LRK" "0"
              "R" "1"
              "RK" "2"
              "K" "3"
              "LR" "4"
              "LRPK" "5"
              "PK" "6"
              "L" "7"
              "LP" "8"
              "P" "9"})

(def numpad2 {"" ""
              "TS*" "0"
              "S" "1"
              "S*" "2"
              "*" "3"
              "TS" "4"
              "TS*d" "5"
              "*d" "6"
              "T" "7"
              "Td" "8"
              "d" "9"})

(def pre-vowels {"-" ""
                 "Y" "$"})

(def post-vowels {"" ""
                  "I" "0"
                  "U" "00"
                  "IU" "000"})

(gen-dict "onyx-numbers.json"
          (apply merge (for [[np1-chord num1] numpad1
                             [np2-chord num2] numpad2
                             [pre-vowel-chord pre-vowel] pre-vowels
                             [post-vowel-chord post-vowel] post-vowels]
                         {(str initial-chord pre-vowel-chord post-vowel-chord np1-chord np2-chord)
                          (str "{^ ^}" pre-vowel num1 num2 post-vowel)
                          (str "+" initial-chord pre-vowel-chord post-vowel-chord np1-chord np2-chord)
                          (str "{^}" pre-vowel num1 num2 post-vowel)})))
