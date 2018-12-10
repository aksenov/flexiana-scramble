(ns flexiana-scramble.scramble)

;; This is the general solution I've got so far.
;; That's bugz me. I've never used (a-z) limitation.
;; It can be done something with it, but failed to solve it in a given time.
;; My best gusses:
;; - a-z can be represented as bytes
;; - a..z sequence can be used for a dictionary tree

(defn scramble?
  "Returns true if a portion of `str1` characters can be rearranged to match `str2`, otherwise returns false."
  [str1 str2]
  (cond
    (= str1 str2) true
    (< (count str1) (count str2)) false
    :else
    (let [str1-freq (frequencies str1)
          str2-freq (frequencies str2)]
      (reduce-kv
        (fn [r c n]
          (let [cn1 (get str1-freq c 0)]
            (if (>= cn1 n)
              r
              (reduced false))))
        true
        str2-freq))))

;; I guess (loop .. recur) instead of reduce can be more idiomatic here.
;; But reduce shorter and more readable.