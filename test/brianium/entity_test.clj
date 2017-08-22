(ns brianium.entity-test
  (:require [clojure.test :refer :all]
            [clojure.test.check]
            [clojure.spec.test.alpha :as st]
            [brianium.entity.generators :as entity-gen]
            [brianium.entity.spec :as entity-spec]))


(def gen-overrides {::entity-spec/uuid-string entity-gen/uuid-string})


(deftest generated-tests
  (doseq [test-output (st/check
                        (st/enumerate-namespace 'brianium.entity)
                        {:gen gen-overrides})]
    (testing (-> test-output :sym name)
      (is
        (true? (-> test-output :clojure.spec.test.check/ret :result))
        test-output))))
