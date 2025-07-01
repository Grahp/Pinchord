(ns dev.grahp.onyx.symbols
  (:require [clojure.data.json :as json]))

(def initial-chord "*SKL")

;; Turn these all into their special names
;; Maybe make a map for that

(def m {"á" "aacute"
        "â" "acircumflex"
        "´" "acute"
        "ä" "adiaeresis"
        "æ" "ae"
        "à" "agrave"
        "&" "ampersand"
        "'" "apostrophe"
        "å" "aring"
        "^" "asciicircum"
        "~" "asciitilde"
        "*" "asterisk"
        "@" "at"
        "ã" "atilde"
        "\\" "backslash"
        "|" "bar"
        "\\{" "braceleft"
        "\\}" "braceright"
        "[" "bracketleft"
        "]" "bracketright"
        "¦" "brokenbar"
        "ç" "ccedilla"
        "¸" "cedilla"
        "¢" "cent"
        ":" "colon"
        "," "comma"
        "©" "copyright"
        "¤" "currency"
        "°" "degree"
        "¨" "diaeresis"
        "÷" "division"
        "$" "dollar"
        "é" "eacute"
        "ê" "ecircumflex"
        "ë" "ediaeresis"
        "è" "egrave"
        "=" "equal"
        "ð" "eth"
        "!" "exclam"
        "¡" "exclamdown"
        "`" "grave"
        ">" "greater"
        "«" "guillemotleft"
        "»" "guillemotright"
        "­" "hyphen"
        "í" "iacute"
        "î" "icircumflex"
        "ï" "idiaeresis"
        "ì" "igrave"
        "<" "less"
        "¯" "macron"
        "º" "masculine"
        "-" "minus"
        "µ" "mu"
        "×" "multiply"
        " " "nobreakspace"
        "¬" "notsign"
        "ñ" "ntilde"
        "#" "numbersign"
        "ó" "oacute"
        "ô" "ocircumflex"
        "ö" "odiaeresis"
        "ò" "ograve"
        "½" "onehalf"
        "¼" "onequarter"
        "¹" "onesuperior"
        "Ø" "ooblique"
        "ª" "ordfeminine"
        "ø" "oslash"
        "õ" "otilde"
        "¶" "paragraph"
        "(" "parenleft"
        ")" "parenright"
        "%" "percent"
        "." "period"
        "·" "periodcentered"
        "+" "plus"
        "±" "plusminus"
        "?" "question"
        "¿" "questiondown"
        "\"" "quotedbl"
        "®" "registered"
        "§" "section"
        ";" "semicolon"
        "/" "slash"
        " " "space"
        "ß" "ssharp"
        "£" "sterling"
        "	" "tab"
        "þ" "thorn"
        "¾" "threequarters"
        "³" "threesuperior"
        "²" "twosuperior"
        "ú" "uacute"
        "û" "ucircumflex"
        "ü" "udiaeresis"
        "ù" "ugrave"
        "_" "underscore"
        "ý" "yacute"
        "ÿ" "ydiaeresis"
        "¥" "yen"
        "€" "euro"})

;;                       -  I  U  IU
(def symbols {"RP"     ["/"]
              "TS"     [";" "" "" ":"]
              "P"      ["."]
              "K"      [","]
              "LR"     ["!"]
              "LPK"    ["?"]
              "LP"     ["\""]
              "LRPK"   ["#" "©" "®" "™"]
              "RPKT"   ["$" "€" "¥" "£"]
              "LS"     ["%"]
              "LKS"    ["&"]
              "L"      ["'" "" "" "`"]
              "LPT"    ["(" "[" "<" "\\{"] ;; Escape to make plover happy
              "RKS"    [")" "]" ">" "\\}"]
              "T"      ["*"]
              "S"      ["+"]
              "PT"     ["-"]
              "KS"     ["_"]
              "PKTS"   ["="]
              "LRPKTS" ["@"]
              "LK"     ["\\"]
              "RPS"    ["^"]
              "PK"     ["|"]
              "LPKS"   ["~"]})

(def special-symbols
  (into {} (for [[chord syms] symbols]
             [chord
              (vec (map #(m % "") syms))])))

(def repetitions {"-" 1
                  "I" 2
                  "U" 3
                  "IU" 4})

(defn repeat-str [s n]
  (apply str (repeat n s)))

(defn sym [sym symbol-chord chord repetitions-chord n]
  (when (and sym (not= sym ""))
    {(str initial-chord repetitions-chord symbol-chord chord)
     (str "{^ ^}" (repeat-str sym n))
     (str "A" initial-chord repetitions-chord symbol-chord chord)
     (str "{^}" (repeat-str sym n))
     (str initial-chord "E" repetitions-chord symbol-chord chord)
     (str "{^ ^}" "#" (repeat-str sym n))
     (str "A" initial-chord "E" repetitions-chord symbol-chord chord)
     (str "{^}" "#" (repeat-str sym n))}))

(->> (for [[symbol-chord [sym1 sym2 sym3 sym4]] symbols
           [repetitions-chord n] repetitions]
       (merge (sym sym1 symbol-chord "" repetitions-chord n)
              (sym sym2 symbol-chord "d" repetitions-chord n)
              (sym sym3 symbol-chord "*" repetitions-chord n)
              (sym sym4 symbol-chord "*d" repetitions-chord n)))
     (apply merge)
     json/write-str
     (spit "/home/grahp/Projects/steno/dicts/symbols.json"))
