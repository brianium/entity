(ns brianium.entity.storage.set
  "A pretty naive storage implementation backed by a set"
  (:require [brianium.entity :as entity]
            [brianium.entity.storage :as storage]))


(defn- find-entity
  "Naive lookup of an entity by id"
  [*entities id]
  (first (filter #(.equals id (::entity/id %)) @*entities)))


(defn- update-entity
  [*entities next prev]
  (let [updated (merge prev next)]
    (-> @*entities
        (disj prev)
        (conj updated)
        (as-> newset (reset! *entities newset)))
    updated))


(defn- insert
  [*entities entity]
  (swap! *entities conj entity)
  entity)


(defn- remove-entity
  "Removes a todo by id from the collection"
  [*entities id]
  (let [current (find-entity *entities id)]
    (if-not (nil? current)
      (-> @*entities
          (disj current)
          (as-> updated (reset! *entities updated))
          (as-> reset (reset current))
          nil?)
      false)))


(defrecord SetStorage [*coll]
  storage/EntityStorage
  (-get-by-id [_ id] (find-entity *coll id))
  (-insert!  [_ entity] (insert *coll entity))
  (-update! [_ entity] (update-entity
                        *coll
                        entity
                        (find-entity *coll (::entity/id entity))))
  (-all [_] @*coll)
  (-remove! [_ id] (remove-entity *coll id)))


(defn make-storage
  ([coll]
   (->SetStorage (atom (set coll))))
  ([]
   (make-storage #{})))
