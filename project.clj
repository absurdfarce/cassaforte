(defproject com.datastax.opscenter/cassaforte "2.0.1-30drivers-1.0.9"
  :min-lein-version "2.5.0"
  :description "A Clojure client for Apache Cassandra"
  :url "http://clojurecassandra.info"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure                          "1.6.0"]
                 [cc.qbits/hayt                                "2.0.0"]
                 [com.datastax.cassandra/cassandra-driver-core "3.1.2"]
                 [com.datastax.cassandra/dse-driver            "1.1.1-beta1"]]
  :source-paths      ["src/clojure"]
  :java-source-paths ["src/java"]
  :profiles       {:1.7 {:dependencies [[org.clojure/clojure "1.7.0-alpha4"]]}
                   :master {:dependencies [[org.clojure/clojure "1.7.0-master-SNAPSHOT"]]}
                   :dev {:jvm-opts     ["-Dlog4j.configuration=log4j.properties.unit"
                                        "-Xmx2048m"
                                        "-javaagent:lib/jamm-0.2.5.jar"]
                         :resource-paths ["resources"]
                         :plugins [[codox "0.8.10"]]
                         :dependencies [[com.codahale.metrics/metrics-core "3.0.2"]
                                        [org.xerial.snappy/snappy-java     "1.1.0.1"]
                                        [org.clojure/tools.trace           "0.7.6"]
                                        [clj-time                          "0.9.0-beta1"]]}}
  :aliases        {"all" ["with-profile" "dev:dev,1.7:dev,master"]}
  :test-selectors {:focus   :focus
                   :client  :client
                   :cql     :cql
                   :schema  :schema
                   :stress  :stress
                   :indexes :indexes
                   :default (fn [m] (not (:stress m)))
                   :ci      (complement :skip-ci)}
  :repositories {"new-artifactory" {:url "tobesuppliedlater"
                                    :snapshots false
                                    :releases {:checksum :fail :update :always}
                                    :sign-releases false}}
  :global-vars {*warn-on-reflection* true}
  :pedantic :warn
  :codox {:src-dir-uri "https://github.com/clojurewerkz/cassaforte/blob/master/"
          :sources ["src/clojure/"]
          :src-linenum-anchor-prefix "L"
          :exclude [clojurewerkz.cassaforte.conversion
                    clojurewerkz.cassaforte.aliases
                    clojurewerkz.cassaforte.metrics
                    clojurewerkz.cassaforte.debug
                    clojurewerkz.cassaforte.bytes]
          :output-dir "doc/api"})
