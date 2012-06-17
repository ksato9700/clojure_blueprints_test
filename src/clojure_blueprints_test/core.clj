(ns clojure_blueprints_test.core
  (:import (com.tinkerpop.blueprints.impls.neo4j Neo4jGraph)
           (com.tinkerpop.blueprints.util.io.graphson GraphSONWriter)
           ))

(defn named-vertex [graph name]
  (let [v (.addVertex graph nil)]
    (.setProperty v "name" name)
    v))

(defn -main [& args]
  (let [graph (Neo4jGraph. "/tmp/my_graph")]
    (.setProperty (.addEdge graph nil
                            (named-vertex graph "marko")
                            (named-vertex graph "peter")
                            "knows") 
                  "since" 2006)

    (GraphSONWriter/outputGraph graph java.lang.System/out)
    (.shutdown graph)
    ))


