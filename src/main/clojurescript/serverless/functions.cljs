(ns serverless.functions
  (:require [cljs.nodejs :as nodejs]))

(nodejs/enable-util-print!)
(defonce dayjs (nodejs/require "dayjs"))

(defn hello [event ctx cb]
  (println ctx)
  (cb nil (clj->js
            {:statusCode 200
             :headers    {"Content-Type" "application/json", "Access-Control-Allow-Origin" "*", "Access-Control-Allow-Credentials" true}
             })))


(defn now [event ctx cb]
  (println ctx)
  (cb nil (clj->js
            {:statusCode 200
             :headers    {"Content-Type" "text/html" "Access-Control-Allow-Origin" "*" "Access-Control-Allow-Credentials" true}
             :body       (str "<h1>"(.format (dayjs.) "dddd, MMMM D, YYYY h:mm A")"</h1>")}))) ; call nodejs package

(set! (.-exports js/module) #js
    {:hello hello
     :now now})
