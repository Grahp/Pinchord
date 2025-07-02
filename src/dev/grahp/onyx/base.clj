(ns dev.grahp.onyx.base
  (:require [dev.grahp.onyx.dict :refer [gen-dict]]))

(def initials {"" ""
               "T" "t"
               "TLR" "tw"
               "TR" "tr"

               "*T" "ch"
               "*TLR" "chw"
               "*TR" "chr"

               "TSP" "f"
               "TSPL" "fl"
               "TSPLR" "fw"
               "TSPR" "fr"

               "*TSP" "ph"
               "*TSPL" "phl"
               "*TSPLR" "phw"
               "*TSPR" "phr"

               "TSK" "v"
               "TSKL" "vl"
               "TSKLR" "vw"
               "TSKR" "vr"

               "TSPK" "g"
               "TSPKL" "gl"
               "TSPKLR" "gw"
               "TSPKR" "gr"

               "*TSPK" "comp"
               "*TSPKL" "compl"
               "*TSPKR" "compr"

               "TP" "b"
               "TPL" "bl"
               "TPLR" "bw"
               "TPR" "br"

               "*TPL" "bel"
               "*TPLR" "beh"
               "*TPR" "ber"

               "TL" "m"
               "TSL" "sm"

               "TPK" "w"
               "TPKL" "wl"
               "TPKLR" "wh"
               "TPKR" "wr"
               "SR" "sw"
               "*SR" "sv"

               "*TPK" "int"
               "*TPKR" "inter"

               "TK" "sh"
               "TKL" "shl"
               "TKLR" "j"
               "TKR" "shr"

               "*TK" "sch"
               "*TKL" "schl"
               "*TKLR" "schn"
               "*TKR" "schr"

               "S" "s"
               "TS" "st"
               "TSR" "str"

               "*TS" "scht"

               "SPK" "d"
               "SPKLR" "dw"
               "SPKR" "dr"
               "SPKL" "z"

               "*SPKLR" "div"

               "P" "p"
               "PL" "pl"
               "PLR" "pw"
               "PR" "pr"
               "SP" "sp"
               "SPL" "spl"
               "SPLR" "spw"
               "SPR" "spr"

               "*P" "ex"
               "*PL" "ext"
               "*PR" "extr"
               "*PLR" "exc"
               "*SP" "exp"
               "*SPL" "expl"
               "*SPR" "expr"
               "*SPLR" "excl"

               "PK" "th"
               "PKLR" "thw"
               "PKR" "thr"
               "PKL" "h"

               "*PK" "ent"
               "*PKR" "enter"

               "K" "c"
               "KL" "cl"
               "KLR" "qu"
               "KR" "cr"
               "SK" "sc"
               "SKLR" "squ"
               "SKR" "scr"
               "SKL" "y"

               "*K" "k"
               "*KL" "kl"
               "*KLR" "q"
               "*KR" "kr"
               "*SK" "sk"
               "*SKLR" "sq"
               "*SKR" "skr"

               "L" "l"
               "SL" "sl"

               "LR" "n"
               "SLR" "sn"

               "*LR" "kn"

               "R" "r"})

(def vowels {"^"     [""]
             "Y"     ["y"]
             "Y^"    ["y" "e"]

             "E"     ["e"]
             "E^"    ["e" "e"]

             "I"     ["i"]
             "I^"    ["i" "e"]

             "U"     ["u"]
             "U^"    ["u" "e"]

             "YU"    ["o"]
             "YU^"   ["o" "e"]

             "YE"    ["a"]
             "YE^"   ["a" "e"]

             "IU"    ["ee"]
             "IU^"   ["ia"]

             "EIU"   ["ai"]
             "EIU^"  ["ei"]

             "YEIU"  ["ie"]
             "YEIU^" ["eo"]

             "YIU"   ["au"]
             "YIU^"  ["ua"]

             "EU"    ["ue"]
             "EU^"   ["ui"]

             "YEU"   ["oo"]
             "YEU^"  ["oa"]

             "YI"    ["ea"]
             "YI^"   ["ae"]

             "EI"    ["oe"]
             "EI^"   ["oi"]

             "YEI"   ["ou"]
             "YEI^"  ["io"]})

(def finals {"" ""
             "L" "l"
             "L*" "ll"
             "LT" "lt"
             "LT*" "ttl"

             "R" "r"
             "RT" "rt"
             "RT*" "rgh"

             "LR" "rl"
             "LR*" "y"

             "K" "k"
             "K*" "c"
             "LK" "lk"
             "LK*" "lc"
             "LRK" "nk"
             "LRK*" "nc"
             "RK" "rk"
             "RK*" "rc"

             "PK" "th"
             "PK*" "the"
             "LPK" "lth"
             "LPK*" "dth"
             "LRPK" "nth"
             "LRPK*" "mth"
             "RPK" "rth"
             "RPK*" "rmth"

             "PKT" "f"
             "PKT*" "ff"
             "LPKT" "lf"
             "LRPKT" "nf"
             "LRPKT*" "mpf"
             "RPKT" "rf"

             "PKTS" "g"
             "PKTS*" "gg"
             "LPKTS" "lg"
             "LRPKTS" "ng"
             "LRPKTS*" "j"
             "RPKTS" "rg"

             "PKS" "d"
             "PKS*" "dd"
             "LPKS" "ld"
             "LPKS*" "ce"
             "LRPKS" "nd"
             "LRPKS*" "rld"
             "RPKS" "rd"
             "RPKS*" "gh"

             "KS" "n"
             "KS*" "ck"
             "LKS" "ln"
             "LKS*" "ckl"
             "RKS" "rn"
             "RKS*" "rnt"
             "LRT" "nt"
             "LRT*" "mt"

             "LRKS" "x"
             "LRKS*" "sk"

             "PTS" "ch"
             "PTS*" "tch"
             "LPTS" "lch"
             "LPTS*" "dg"
             "LRPTS" "nch"
             "RPTS" "rch"
             "RPTS*" "ction"

             "KT" "sh"
             "KT*" "tion"
             "LKT" "lsh"
             "LKT*" "sion"
             "RKT" "rsh"
             "RKT*" "rtian"
             "LRKT" "ct"
             "LRKT*" "kt"

             "P" "p"
             "P*" "pl"
             "LP" "lp"
             "LP*" "ft"
             "RP" "rp"
             "RP*" "w"

             "PS" "m"
             "PS*" "mpt"
             "LPS" "lm"
             "LPS*" "mn"
             "RPS" "rm"
             "RPS*" "wd"
             "LRP" "mp"
             "LRP*" "mpl"
             "LRPS" "sp"

             "KTS" "v"
             "KTS*" "ve"
             "LKTS" "lv"
             "LKTS*" "cal"
             "LRKTS" "nv"
             "LRKTS*" "xt"
             "RKTS" "rv"
             "RKTS*" "nl"

             "PT" "b"
             "PT*" "q"
             "LPT" "lb"
             "LPT*" "bl"
             "RPT" "rb"
             "RPT*" "wn"
             "LRPT" "pt"
             "LRPT*" "mb"

             "T" "t"
             "T*" "ght"

             "S" "s"
             "S*" "ss"
             "LS" "ls"
             "LS*" "se"
             "LRS" "ns"
             "LRS*" "son"
             "RS" "rs"
             "RS*" "h"
             "TS" "st"
             "TS*" "z"
             "LTS" "lst"
             "LTS*" "ze"
             "LRTS" "nst"
             "LRTS*" "nz"
             "RTS" "rst"})

(gen-dict "onyx-base.json"
          (apply merge (for [[inital-chord initial] initials
                             [vowel-chord [pre-vowel post-vowel]] vowels
                             [final-chord final] finals]
                         {(str inital-chord vowel-chord final-chord)
                          (str "{^ ^}" initial pre-vowel final post-vowel)})))
