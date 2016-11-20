(ns core
  (:require [compojure.route :as route]
            [clojure.java.io :as io]
            [org.httpkit.server :as kit]
            [cemerick.friend :as friend]
            (cemerick.friend [workflows :as workflows]
                             [credentials :as creds])
            [clojure.set :refer [rename-keys]]
            [ring.middleware.reload :as reload]
            [routes
             [auth :as auth]]
            [middleware
             [auth :refer [credential-fn reg-workflow]]]
            )

  (:use compojure.core
        compojure.handler
        ring.middleware.params
        carica.core
        korma.db
        korma.core
        ring.util.json-response
        org.httpkit.server
        db.core
        ))

(defn app-routes []
     (routes
       auth/routes
       ))

(def app
    (-> (app-routes)
        (friend/authenticate {
                              :login-uri "/login"
                              :credential-fn (credential-fn)
                              :workflows [(workflows/interactive-form)
                                          (reg-workflow)]
                              })
        site
        ))

(defn -main [& args]
  (kit/run-server (reload/wrap-reload app) {:port 3000}))
