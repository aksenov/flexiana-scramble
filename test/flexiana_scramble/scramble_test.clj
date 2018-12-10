(ns flexiana-scramble.scramble-test
  (:require [clojure.test :refer :all]
            [flexiana-scramble.scramble :refer :all]
            [criterium.core :as criterium]))

(deftest scramble-test
  (testing "Given examples"
    (is (true? (scramble? "rekqodlw" "world")))
    (is (true? (scramble? "cedewaraaossoqqyt" "codewars")))
    (is (false? (scramble? "katas" "steak"))))

  (testing "Equal string or part of string"
    (let [s "qwertyui"
          c (count s)]
      (is (true? (scramble? s s)))
      (is (true? (scramble? s (->> s
                                   (take (rand-int c))
                                   (apply str)))))))
  (testing "Any combination of original string are allowed"
    (let [s "qwertyui"]
      (is (true? (scramble? s (->> s
                                   seq
                                   shuffle
                                   (apply str)))))))
  (testing "No such character"
    (is (false? (scramble? "aaabbccc" "abcd"))))

  (testing "Not enough repeating characters"
    (is (false? (scramble? "aaabbcccd" "abcdd"))))

  (testing "First string less than second"
    (is (false? (scramble? "abc" "abca")))))


(comment
  ;; criterium benchmark

  (criterium/quick-bench (scramble? "qwertyuiop" "qwertyuiop"))
  (criterium/quick-bench (scramble? "abc" "abca"))
  (criterium/quick-bench (scramble? "qwertyuiop" (->> "qwertyuiop"
                                                      seq
                                                      shuffle
                                                      (apply str))))
  ; On my machine:
  ;
  ;   Evaluation count : 42162 in 6 samples of 7027 calls.
  ;             Execution time mean : 14.255085 µs
  ;    Execution time std-deviation : 199.840654 ns
  ;   Execution time lower quantile : 14.065766 µs ( 2.5%)
  ;   Execution time upper quantile : 14.551491 µs (97.5%)
  ;                   Overhead used : 2.090967 ns
  )