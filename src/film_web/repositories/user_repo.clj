(ns repositories.user-repo
  (:use repositories.repo-protocol
        korma.core
        (cemerick.friend [workflows :as workflows]
                         [credentials :as creds]))
  )

(defentity user)

(defprotocol user-repo-protocol
  (find-user-by-username [this username])
  )

(defrecord User-repo [] user-repo-protocol
  (find-user-by-username [this username] (select user (where {:username username})))
  repo-protocol
  (Insert [this entity]
    (insert user (values {:username (:username entity)
                          :email (:email entity)
                          :fio (:fio entity)
                          :password (creds/hash-bcrypt (:password entity))}))
    )
  )




