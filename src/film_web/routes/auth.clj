(ns routes.auth
  (:require [compojure.core :refer [GET POST ANY defroutes]]
            [cemerick.friend :as friend])
  )

(defroutes routes
           (GET "/" request (-> request (str)))
           (friend/logout (ANY "/logout" request (ring.util.response/redirect "/")))
           (GET "/login" [login_failed username]
             (if (= login_failed "Y")  (-> "NEVERNII " (str))
                                       (-> "public/index.html"
                                           (ring.util.response/file-response {:root "resources"})
                                           (ring.util.response/content-type "text/html")))
             )
           (GET "/register" [] (-> "public/register.html"
                                   (ring.util.response/file-response {:root "resources"})
                                   (ring.util.response/content-type "text/html")))

           )
