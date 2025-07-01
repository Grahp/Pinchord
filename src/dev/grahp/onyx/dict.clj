(ns dev.grahp.onyx.dict
  (:require [clojure.data.json :as json]
            [clojure.spec.alpha :as s]))

(s/def ::outline string?)
(s/def ::translation string?)
(s/def ::dict (s/map-of ::outline ::translation))

(defn gen-dict
  "Takes a clojure map of string outlines to translations.
  spits a json file in the recources dir with the provided name"
  [name dict]
  (spit (str "resources/" name)
        (json/write-str (dissoc dict "-"))))
