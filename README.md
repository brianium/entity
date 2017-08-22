# entity

[![Clojars Project](https://img.shields.io/clojars/v/brianium/entity.svg)](https://clojars.org/brianium/entity)

Just a basic entity library for Clojure(Script). Helps you deal with maps that have ids

## Usage

This library only deals with entities having uuid ids (for now). It provides the `brianium.entity` namespace for creating uuids and entities with them in a way that works with both Clojure and ClojureScript.

```clojure
(require '[brianium.entity :refer :all])

(def uuid-str "e5aedf45-cf9c-4488-897e-8462fcaacd94")

(uuid-string? uuid-str)
;; => true

(string->uuid uuid-str)
;; => #uuid "e5aedf45-cf9c-4488-897e-8462fcaacd94"

(make-uuid)
;; => #uuid "3ef75e99-66ca-415d-92c2-85b96c38b038"

(make-entity {:name "brian" :title "computer"})
;; => {:brianium.entity/id #uuid "3ef75e99-66ca-415d-92c2-85b96c38b038"
       :name "brian"
	   :title "computer}
```

There is also a general purpose `EntityStorage` protocol defined in `brianium.entity.storage`

```clojure
(defprotocol EntityStorage
  (-get-by-id [this id])
  (-insert [this entity])
  (-update [this entity])
  (-all [this])
  (-remove [this id]))
```

There are functions for each protocol method available as well:

```clojure
(require '[brianium.entity.storage :as store])

;; there is a set backed implementation useful for testing
(require '[brianium.entity.storage.set :refer [make-storage]])

(def storage (make-storage))

(store/insert storage some-entity)
(store/get-by-id storage some-uuid)

;; there is also a useful function built on the others
(store/insert-or-update storage some-entity)
```

## Specs

There are specs for all public functions and types - including `EntityStorage`

Specs for entities can be found in `brianium.entity.spec` and specs for storage can be found in `brianium.entity.storage.spec`

Generators for entities and storage can be found at `brianium.entity.generators` and `brianium.entity.storage.generators`.

## Testing

Tests are a combination of unit tests and generative tests a-la `clojure/test.check`

```
$ lein test
```
