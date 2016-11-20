(ns middleware.auth
  (:require
    [cemerick.friend :as friend]
    (cemerick.friend [workflows :as workflows]
                             [credentials :as creds])
    [repositories.user-repo]
    )
  (:import [repositories.user_repo User-repo])
  )


(defn register [{:keys [username password]}]
  (try
    (.Insert (User-repo. ) {:username username :password password})
    (workflows/make-auth {:identity username :roles #{::user} :username username})
    (catch Exception e
      (.println System/out e)
      (str "Username address already in use")))
  )

(defn reg-workflow []
  (fn [{:keys [uri request-method params]}]
    (when (and (= uri "/register")
               (= request-method :post))
      (register params))))

(defn credential-fn []
  (fn [auth-map]
    (let [user (get (.find-user-by-username (User-repo.) (:username auth-map))0)]
      (if (not (= user nil))
        (if (creds/bcrypt-verify (:password auth-map) (:password user))
          {:identity (:id user) :roles #{::user} :user user})
        )
      )))

