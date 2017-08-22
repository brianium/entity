(ns brianium.entity.spec
  (:require #?(:clj  [clojure.spec.alpha :as s]
               :cljs [cljs.spec.alpha :as s])
            [brianium.entity :as entity]))


(s/def ::id uuid?)
(s/def ::entity/id ::id)
(s/def ::entity (s/keys :req [::entity/id]))
(s/def ::uuid-string entity/uuid-string?)


(s/fdef entity/uuid-string?
  :args (s/cat :str string?)
  :ret  boolean?)


(s/fdef entity/make-uuid
  :args empty?
  :ret  ::id)


(s/fdef entity/string->uuid
  :args (s/cat :str ::uuid-string)
  :ret  ::id)


(s/fdef entity/make-entity
  :args (s/cat :entity-map map? :id (s/? ::id))
  :ret  ::entity)
