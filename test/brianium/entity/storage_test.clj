(ns brianium.entity.storage-test
  (:require [clojure.test :refer :all]
            [clojure.test.check]
            [clojure.spec.test.alpha :as st]
            [brianium.entity :as entity]
            [brianium.entity.storage :refer :all]
            [brianium.entity.storage.set :refer [make-storage]]
            [brianium.entity.storage.spec :as storage-spec]
            [brianium.entity.storage.generators :as gen])
  (:refer-clojure :exclude [remove update]))


(deftest test-insert-or-update
  (testing "inserting new record"
    (let [storage   (make-storage)
          inserting (entity/make-entity {})]
      (insert-or-update storage inserting)
      (is (= inserting (first (all storage))))))
  (testing "updating a record"
    (let [current (entity/make-entity {:name "entity"})
          storage (make-storage #{current})]
      (insert-or-update storage (merge current {:name "updated"}))
      (is (= 1 (count (all storage))))
      (is (= "updated" (:name (first (all storage))))))))



(def gen-overrides {::storage-spec/entity-storage gen/storage})


(deftest generated-tests
  (doseq [test-output (st/check
                        (st/enumerate-namespace 'brianium.entity.storage)
                        {:gen gen-overrides})]
    (testing (-> test-output :sym name)
      (is
        (true? (-> test-output :clojure.spec.test.check/ret :result))
        test-output))))
