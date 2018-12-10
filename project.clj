(defproject flexiana-scramble "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.0-RC4"]
                 [ring "1.7.1"]
                 [compojure "1.6.1"]
                 [cheshire "5.8.1"]
                 [hiccup "1.0.5"]
                 [org.clojure/clojurescript "1.10.439" :scope "provided"]
                 [reagent "0.8.1"]
                 [cljs-http "0.1.45"]]
  :plugins [[lein-cljsbuild "1.1.7"]]
  :uberjar-name "flexiana-scramble.jar"
  :min-lein-version "2.8.1"
  :main ^:skip-aot flexiana-scramble.server
  :target-path "target/%s"
  :source-paths ["src/clj" "src/cljc"]
  :resource-paths ["resources" "target/cljsbuild"]
  :cljsbuild {:builds
              {:min
               {:source-paths ["src/cljs" "src/cljc"]
                :compiler
                              {:output-to        "target/cljsbuild/public/js/app.js"
                               :output-dir       "target/cljsbuild/public/js"
                               :source-map       "target/cljsbuild/public/js/app.js.map"
                               :optimizations :advanced
                               :pretty-print  false}}
               :app
               {:source-paths ["src/cljs" "src/cljc"]
                :compiler
                              {:main "flexiana-scramble.dev"
                               :asset-path "/js/out"
                               :output-to "target/cljsbuild/public/js/app.js"
                               :output-dir "target/cljsbuild/public/js/out"
                               :source-map true
                               :optimizations :none
                               :pretty-print  true}}}}
  :codox {:metadata {:doc/format :markdown}}
  :profiles {:uberjar {:aot :all}
             :dev {:dependencies [[ring/ring-mock "0.3.2"]
                                  [criterium "0.4.4"]]}})
