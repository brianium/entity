(ns brianium.entity.storage
  (:require #?(:clj  [clojure.spec.alpha :as s]
               :cljs [cljs.spec.alpha :as s])
            [brianium.entity :as entity]
            [brianium.entity.spec :as entity-spec])
  (:refer-clojure :exclude [update remove]))


(defprotocol EntityStorage
  (-get-by-id [this id])
  (-insert [this entity])
  (-update [this entity])
  (-all [this])
  (-remove [this id]))


(defn entity-storage?
  [value]
  (satisfies? EntityStorage value))


(defn get-by-id
  "Gets an entity from storage by id"
  [storage id]
  (-get-by-id storage id))


(defn insert
  "Insert a new entity into storage"
  [storage entity]
  (-insert storage entity))


(defn update
  "Update an existing entity"
  [storage entity]
  (-update storage entity))


(defn all
  "Returns a sequence of all entities"
  [storage]
  (-all storage))


(defn remove
  "Removes an entity by id from storage"
  [storage id]
  (-remove storage id))


(defn insert-or-update
  "Inserts an entity if new - otherwise updates"
  [storage {:keys [::entity/id] :as entity}]
  (let [existing (get-by-id storage id)]
    (if (s/valid? ::entity-spec/entity entity)
      (update storage entity)
      (insert storage entity))))
