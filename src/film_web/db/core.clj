(ns db.core
  (:use
    korma.db
    korma.core
    carica.core
    )
  )

(defdb db {:classname   "com.mysql.jdbc.Driver"
           :subprotocol "mysql"
           :user        (config :db :user)
           :password    (config :db :password)
           :subname     (str "//127.0.0.1:3306/" (config :db :name) "?useUnicode=true&characterEncoding=utf8")
           :delimiters  "`"
           })
