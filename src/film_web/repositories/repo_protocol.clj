(ns repositories.repo-protocol)

(defprotocol repo-protocol
  ;;find all entities
  (FindAll [this])
  ;;find entity by id
  (FindOne [this id])
  ;;insert entity
  (Insert [this entity])
  ;;update entity
  (Update [this id entity])
  ;;delete entity
  (Delete [this id])
  )