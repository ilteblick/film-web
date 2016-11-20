(ns routes.user
  (:use [repositories.user-repo]
    )
  (:require [compojure.core :refer [GET POST ANY defroutes]]
            [cemerick.friend :as friend]
            (cemerick.friend [workflows :as workflows]
                             [credentials :as creds])
            )
  (:import [repositories.user_repo User-repo])
  )

(defroutes routes
           (GET "/changePass" [] (-> "public/passChange.html"
                                     (ring.util.response/file-response {:root "resources"})
                                     (ring.util.response/content-type "text/html")))
           (POST "/changePass" request
             (let [oldpass (:oldpass (:params request)) newpass (:newpass (:params request)) confirmedpass
                   (:confirmedpass (:params request)) current (:current (:cemerick.friend/identity (:session request)))]
               (let [user (get (.find-user-by-username (User-repo.) current) 0)]
                 (if (= newpass confirmedpass)
                   (if (creds/bcrypt-verify oldpass (:password user))
                     (let [newuser (assoc user :password (creds/hash-bcrypt newpass))]
                        (.Update (User-repo.) (:id user) newuser)
                       (str current oldpass newpass confirmedpass user newuser))
                     (str "nevernii pass"))
                   (str "raznii novie paroli")
                   ))
           ))
           (GET "/userInfoChange" [] (-> "public/userInfoChange.html"
                                     (ring.util.response/file-response {:root "resources"})
                                     (ring.util.response/content-type "text/html")))
           (POST "/userInfoChange" request
             (let [email (:email (:params request)) fio (:fio (:params request))
                   current (:current (:cemerick.friend/identity (:session request)))]
               (let [user (get (.find-user-by-username (User-repo.) current) 0)]
                  (let [newuser (assoc user :fio fio :email email)]
                       (.Update (User-repo.) (:id user) newuser)
                         (str newuser)
                       )
                  )
               ))
             )
