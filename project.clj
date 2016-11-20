(defproject film-web "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [compojure "1.1.6"]
                 [sonian/carica "1.1.0" :exclusions [[cheshire]]]
                 [korma "0.3.1"]
                 [mysql/mysql-connector-java "5.1.30"]
                 [fogus/ring-edn "0.2.0"]
                 [ring "1.5.0"]
                 [ring/ring-json "0.4.0"]
                 [ring-json-response "0.2.0"]
                 [com.cemerick/friend "0.2.3"]
                 [http-kit "2.2.0"]]
  :plugins [[lein-ring "0.6.3"]]
  :ring {:handler core/app}
  :source-paths ["src/film_web"]
  :main core)
