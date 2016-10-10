(ns dscript.app
  (:require [datascript.core :as d]))

;;; Create a DataScript "connection" (an atom with the current DB value)
(def conn (d/create-conn))

;; Define datoms to transact
(def datoms [{:db/id -1 :name "Bob" :age 30}
             {:db/id -2 :name "Sally" :age 15}])

;;; Add the datoms via transaction
(d/transact! conn datoms)

;;; Query to find names for entities (people) whose age is less than 18
(def q-young '[:find ?n
               :where
               :in ?min-age
               [?e :name ?n]
               [?e :age ?a]
               [(< ?a ?min-age)]])

;; execute query: q-young, passing 18 as min-age
(d/q q-young 18)

(defn init []
  (let [c (.. js/document (createElement "DIV"))]
    (aset c "innerHTML" "<p>i'm dynamically created</p>")
    (.. js/document (getElementById "container") (appendChild c))))
