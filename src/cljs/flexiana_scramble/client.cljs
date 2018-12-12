(ns flexiana-scramble.client
  "Client application."
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [reagent.core :as r]
            [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]
            [flexiana-scramble.validation :refer [valid-string?]]
            [clojure.string :as string]))

(def ^:dynamic *request-timeout* 10000)

(defn format-result
  "Format result from"
  [result?]
  (if result?
    [:span {:style {:color "green"}} "Yes, it can!"]
    [:span {:style {:color "red"}} "No, it can't!"]))


(defn format-error
  [error-text error-message]
  [:span {:style {:background-color "red"
                  :color            "white"}}
   (str "Error! " error-text " - "
        (if-not (string/blank? error-message) error-message "Unexpected error"))])


(defn scramble-strings!
  "Handle scramble request"
  [str1 str2 result]
  (reset! result "Pending for scramble result...")          ;; TODO: Show this on long requests only
  (go
    (let [{:keys [body success error-text]}
          (<! (http/get "/api/scramble"
                        {:query-params {"str1" str1
                                        "str2" str2}
                         :timeout      *request-timeout*}))]
      (if success
        (reset! result (format-result body))
        (reset! result (format-error error-text body))))))


(defn string-input
  [value]
  [:input {:type      "text"
           :value     @value
           :on-change #(when (valid-string? (-> % .-target .-value))
                         (reset! value (-> % .-target .-value)))}])


(defn scramble-form
  "Form for scrambling strings."
  []
  (let [str1   (r/atom "qwertyuiopzxcvbnm")
        str2   (r/atom "tryme")
        result (r/atom "")]
    (fn []
      [:div
       [string-input str1]
       [string-input str2]
       [:input {:type "button"
                :value "Scramble"
                :on-click #(scramble-strings! @str1 @str2 result)}]
       [:p "Can it be scrambled? " @result]])))


(defn scramble-app
  "Main app."
  []
  (fn []
    [:div
     [:h1 "Scramble strings!"]
     [scramble-form]]))


(defn init!
  []
  (r/render [scramble-app] (.getElementById js/document "app")))

;; TODO: use profiles!
;(enable-console-print!)

(init!)
