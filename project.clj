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
             :dev {:plugins [[jonase/eastwood "0.2.3"]
                             [lein-cloverage "1.0.9"]
                             [lein-kibit "0.1.3"]]}})
