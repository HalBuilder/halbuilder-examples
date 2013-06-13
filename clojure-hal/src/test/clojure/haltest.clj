(ns haltest
  (:use [clojure.test]))

(deftest haltest
    (let [representation-factory (com.theoryinpractise.halbuilder.json.JsonRepresentationFactory.)
          representation (doto (.newRepresentation representation-factory "/foo")
                         (.withProperty "name" "Mark")
                         (.withLink "home" "/home"))]
         (println (.toString representation "application/hal+json"))) )
