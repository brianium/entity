(ns brianium.entity.generators
  (:require #?(:clj  [clojure.spec.alpha :as s]
               :cljs [cljs.spec.alpha :as s])
            [brianium.entity :refer [make-uuid]]))


(defn uuid-string
  []
  (s/gen #{(str (make-uuid))}))
