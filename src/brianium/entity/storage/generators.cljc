(ns brianium.entity.storage.generators
  (:require #?(:clj  [clojure.spec.alpha :as s]
               :cljs [cljs.spec.alpha :as s])
            [brianium.entity.storage.set :refer [make-storage]]))


(defn storage
  "Generates a storage implementation backed by a set"
  []
  (s/gen #{ (make-storage) }))

