(defproject jeremy-miller/life-clojure "0.1.0-SNAPSHOT"
  :description "Conway's Game of Life"
  :url "https://github.com/jeremy-miller/life-clojure"
  :license {:name "MIT License"
            :url "https://github.com/jeremy-miller/life-clojure/blob/master/LICENSE"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [net.mikera/core.matrix "0.57.0"]]
  :main ^:skip-aot life-clojure.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}
             :dev {:plugins [[jonase/eastwood "0.2.3"]
                             [lein-kibit "0.1.3"]]}})
