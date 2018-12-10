(ns flexiana-scramble.server-test
  (:require [clojure.test :refer :all]
            [flexiana-scramble.server :refer :all]
            [ring.mock.request :as mock]))

(deftest scramble-handler-test
  (testing "Valid input"
    (is (= (app (mock/request :get "/api/scramble?str1=aabbccd&str2=cab"))
           {:body    "true"
            :headers {"Content-Type" "application/json"}
            :status  200})))
  (testing "Invalid input"
    (is (= (app (mock/request :get "/api/scramble?str2=cab"))
           {:body    "Bad input"
            :headers {}
            :status  400}))))


;"Content-Type" "application/json; charset=utf-8"