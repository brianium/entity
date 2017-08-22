(ns brianium.entity.storage.spec
  (:require #?(:clj  [clojure.spec.alpha :as s]
               :cljs [cljs.spec.alpha :as s])
            [brianium.entity.storage :as storage]
            [brianium.entity.spec :as entity]))


(s/def ::entity-storage storage/entity-storage?)


(s/fdef storage/entity-storage?
  :args (s/cat :value any?)
  :ret  boolean?)


(s/fdef storage/get-by-id
  :args (s/cat :storage ::entity-storage :id ::entity/id)
  :ret  (s/or :entity ::entity/entity :not-found nil?))


(s/fdef storage/insert
  :args (s/cat :storage ::entity-storage :entity ::entity/entity)
  :ret  ::entity/entity)


(s/fdef storage/update
  :args (s/cat :storage ::entity-storage :entity ::entity/entity)
  :ret  ::entity/entity)


(s/fdef storage/all
  :args (s/cat :storage ::entity-storage)
  :ret  (s/* ::entity/entity))


(s/fdef storage/remove
  :args (s/cat :storage ::entity-storage :id ::entity/id)
  :ret  boolean?)


(s/fdef storage/insert-or-update
  :args (s/cat :storage ::entity-storage :entity ::entity/entity)
  :ret  ::entity/entity)
