(ns flexiana-scramble.server
  "Server with:
  * main page
  * scramble API hanlder"
  (:require
    [flexiana-scramble.scramble :refer [scramble?]]
    [flexiana-scramble.validation :refer [valid-input?]]
    [compojure.core :refer [defroutes GET]]
    [compojure.route :as route]
    [ring.middleware.params :refer [wrap-params]]
    [ring.adapter.jetty :as jetty]
    [hiccup.page :refer [html5 include-js]])
  (:gen-class))

(defn main-page
  [_]
  (html5
    [:body
     [:div#app
      [:h2 "Loading..."]]
     (include-js "js/app.js")]))


(defn scramble-handler
  "Handle scramble request. Takes 2 strings and scramles them.
   Respond with 200 with JSON boolean value on success. With 400 on invalid input. "
  [str1 str2]
  (if (valid-input? str1 str2)
    ;; `true` and `false` are valid JSON values
    {:status  200
     :headers {"Content-Type" "application/json"}
     :body    (str (scramble? str1 str2))}
    {:status 400
     :body "Invalid input"}))


(defroutes app-routes
           (GET "/" [] main-page)
           (GET "/api/scramble" [str1 str2] (scramble-handler str1 str2))
           (route/resources "/")
           (route/not-found "Not Found"))


(def app
  "Main webapp."
  (-> app-routes
      wrap-params))


;; TODO: Use some configuration system
(defn- get-port
  "Get port from env."
  []
  (when-let [port (System/getenv "PORT")]
    (Integer/parseInt port)))


(defn -main
  "Run server"
  [& args]
  (let [port (or (get-port) 8888)]
    (jetty/run-jetty app {:port port :join? true})))


;; Yeah, I know I should use mount or component
(comment
  (def server (jetty/run-jetty app {:port 8888 :join? false}))
  (.stop server))

