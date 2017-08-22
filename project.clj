(defproject brianium/entity "0.1.0"
  :description "A simple starting point for Clojure(Script) entities"
  
  :url "https://github.com/brianium/entity"
  
  :license {:name "MIT"}
  
  :dependencies [[org.clojure/clojure "1.9.0-alpha17"]
                 [org.clojure/clojurescript "1.9.854"]]

  :jar-exclusions [#"test"]

  :source-paths ["src"]

  :profiles {:dev {:dependencies [[org.clojure/test.check "0.10.0-alpha2"]
                                  [com.cemerick/piggieback "0.2.2"]]

                   :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}

                   :source-paths ["src" "test"]}})
