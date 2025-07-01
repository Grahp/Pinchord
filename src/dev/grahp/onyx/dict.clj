(ns dev.grahp.onyx.dict
  (:require [clojure.data.json :as json]
            [clojure.spec.alpha :as s]))

(s/def ::outline string?)
(s/def ::translation string?)
(s/def ::dict (s/map-of ::outline ::translation))

(defn spit-resource [name content]
  (spit (str "resources/" name) content))

(defn gen-dict
  "Takes a clojure map of string outlines to translations.
  spits a json file in the resources dir with the provided name"
  [name dict]
  (spit-resource name (-> dict
                          (dissoc "-")
                          json/write-str)))
