(ns flexiana-scramble.validation
  "Validators for scrumble requests.")

(defn valid-string?
  "String should contain only lower case latin characters."
  [s]
  (when s
    (re-matches #"[a-z]+" s)))

(defn valid-input?
  "Check is scramble input is valid."
  [s1 s2]
  (and (valid-string? s1) (valid-string? s2)))