(defproject jeremy-miller/life-clojure "1.0"
  :description "Conway's Game of Life"
  :url "https://github.com/jeremy-miller/life-clojure"
  :license {:name "MIT License"
            :url "https://github.com/jeremy-miller/life-clojure/blob/master/LICENSE"}
  :dependencies [[net.mikera/core.matrix "0.57.0"]
                 [org.clojure/clojure "1.8.0"]]
  :main ^:skip-aot life-clojure.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}
             :dev {:dependencies [[pjstadig/humane-test-output "0.8.1"]]
                   :injections [(require 'pjstadig.humane-test-output)
                                (pjstadig.humane-test-output/activate!)]
                   :plugins [[com.jakemccrary/lein-test-refresh "0.18.1"]
                             [jonase/eastwood "0.2.3"]
                             [lein-cljfmt "0.5.6"]
                             [lein-cloverage "1.0.9"]
                             [lein-kibit "0.1.3"]]
                   :test-refresh {:quiet true
                                  :changes-only true}}})
