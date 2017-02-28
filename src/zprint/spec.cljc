(ns zprint.spec
  #?@(:cljs [[:require-macros [zprint.smacros :refer [only-keys]]]])
  (:require #?@(:clj [[zprint.smacros :as sm] [clojure.spec :as s]]
                :cljs [[cljs.spec :as s]])))

;!zprint {:list {:constant-pair-min 2}}

;;
;; # Specs for the options map
;;

;;
;; ## Color keys
;;

(s/def ::color #{:red :purple :green :blue :magenta :yellow :black :cyan})

(s/def ::brace ::color)
(s/def ::bracket ::color)
(s/def ::comment ::color)
(s/def ::deref ::color)
(s/def ::fn ::color)
(s/def ::hash-brace ::color)
(s/def ::hash-paren ::color)
(s/def ::keyword ::color)
(s/def ::nil ::color)
(s/def ::none ::color)
(s/def ::number ::color)
(s/def ::paren ::color)
(s/def ::quote ::color)
(s/def ::string ::color)
(s/def ::uneval ::color)
(s/def ::user-fn ::color)

;;
;; # Fundamental values
;;

(s/def ::boolean (s/nilable boolean?))
(s/def ::fn-type
  #{:binding :arg1 :arg1-body :arg1-pair-body :arg1-pair :pair :hang :extend
    :arg1-extend-body :arg1-extend :fn :arg1-> :noarg1-body :noarg1 :arg2
    :arg2-pair :arg2-fn :none :none-body :arg1-force-nl :gt2-force-nl
    :gt3-force-nl :flow :flow-body :force-nl-body :force-nl :pair-fn})
(s/def ::format-value #{:on :off :next :skip})
(s/def ::nilable-number (s/nilable number?))
(s/def ::vec-or-list-of-keyword (s/coll-of keyword? :kind sequential?))
(s/def ::style-value
  (s/or :multiple-styles ::vec-or-list-of-keyword
        :single-style (s/nilable keyword?)))
(s/def ::constant
  (s/or :string string?
        :number number?
        :keyword keyword?))
(s/def ::constant-seq (s/coll-of ::constant :kind sequential?))
(s/def ::key-or-ks-seq
  (s/coll-of (s/or :constant ::constant
                   :constant-seq ::constant-seq)
             :kind sequential?))
(s/def ::key-value (s/nilable (s/coll-of ::constant :kind sequential?)))
(s/def ::key-color-value (s/nilable (s/coll-of (s/nilable ::color) :kind sequential?)))
;(s/def ::key-color-seq (s/coll-of (s/nilable 
(s/def ::boolean-or-string
  (s/or :boolean ::boolean
        :string string?))
(s/def ::keep-or-drop #{:keep :drop})
(s/def ::fn-map-value (s/nilable (s/map-of string? ::fn-type)))

;;
;; # Leaf map keys
;;

(s/def ::comma? ::boolean)
(s/def ::constant-pair? ::boolean)
(s/def ::constant-pair-min number?)
(s/def ::count? ::boolean)
(s/def ::docstring? ::boolean)
(s/def ::expand? ::boolean)
(s/def ::flow? ::boolean)
(s/def ::force-nl? ::boolean)
(s/def ::general-hang-adjust number?)
(s/def ::hang? ::boolean)
(s/def ::hang-diff number?)
(s/def ::hang-expand number?)
(s/def ::hang-flow number?)
(s/def ::hang-flow-limit number?)
(s/def ::hang-if-equal-flow? ::boolean)
(s/def ::hang-type-flow number?)
(s/def ::hex? ::boolean)
(s/def ::indent number?)
(s/def ::indent-arg ::nilable-number)
(s/def ::interpose ::boolean-or-string)
(s/def ::justify? ::boolean)
(s/def ::justify-hang (only-keys :opt-un [::hang? ::hang-expand ::hang-diff]))
(s/def ::justify-tuning
  (only-keys :opt-un [::hang-flow ::hang-type-flow ::hang-flow-limit
                      ::general-hang-adjust]))
(s/def ::key-color (s/nilable (s/map-of any? ::color)))
(s/def ::key-depth-color ::key-color-value)
(s/def ::key-ignore (s/nilable ::key-or-ks-seq))
(s/def ::key-ignore-silent (s/nilable ::key-or-ks-seq))
(s/def ::key-order (s/nilable ::key-value))
(s/def ::left-space ::keep-or-drop)
(s/def ::modifiers (s/nilable (s/coll-of string? :kind set?)))
(s/def ::nl-separator? ::boolean)
(s/def ::object? ::boolean)
(s/def ::pair-hang? ::boolean)
(s/def ::record-type? ::boolean)
(s/def ::size number?)
(s/def ::sort? ::boolean)
(s/def ::sort-in-code? ::boolean)
(s/def ::to-string? ::boolean)
(s/def ::value any?)
(s/def ::wrap? ::boolean)
(s/def ::wrap-after-multi? ::boolean)
(s/def ::wrap-coll? ::boolean)


;;
;; # Elements of the top level options map
;;

(s/def ::agent (only-keys :opt-un [::object?]))
(s/def ::array (only-keys :opt-un [::hex? ::indent ::object? ::wrap?]))
(s/def ::atom (only-keys :opt-un [::object?]))
(s/def ::auto-width? ::boolean)
(s/def ::binding
  (only-keys :opt-un [::flow? ::force-nl? ::hang-diff ::hang-expand ::hang?
                      ::indent ::justify? ::justify-hang ::justify-tuning
                      ::nl-separator?]))
(s/def ::color-map
  (only-keys :opt-un [::brace ::bracket ::comment ::deref ::fn ::hash-brace
                      ::hash-paren ::keyword ::nil ::none ::number ::paren
                      ::quote ::string ::uneval ::user-fn]))
(s/def :alt/comment (only-keys :opt-un [::count? ::wrap?]))
(s/def ::configured? ::boolean)
(s/def ::dbg? ::boolean)
(s/def ::dbg-print? ::boolean)
(s/def ::dbg-ge any?)
(s/def ::delay (only-keys :opt-un [::object?]))
(s/def ::drop? ::boolean)
(s/def ::do-in-hang? ::boolean)
(s/def ::extend
  (only-keys :opt-un [::flow? ::force-nl? ::hang-diff ::hang-expand ::hang?
                      ::indent ::modifiers ::nl-separator?]))
(s/def :alt/extend (only-keys :opt-un [::modifiers]))
(s/def ::file? ::boolean)
(s/def ::fn-force-nl (s/nilable (s/coll-of ::fn-type :kind set?)))
(s/def ::fn-gt2-force-nl (s/nilable (s/coll-of ::fn-type :kind set?)))
(s/def ::fn-gt3-force-nl (s/nilable (s/coll-of ::fn-type :kind set?)))
(s/def ::fn-map ::fn-map-value)
(s/def ::fn-name any?)
(s/def ::fn-obj (only-keys :opt-un [::object?]))
(s/def ::format ::format-value)
(s/def ::future (only-keys :opt-un [::object?]))
(s/def ::indent number?)
(s/def ::list
  (only-keys :opt-un [::constant-pair-min ::constant-pair? ::hang-diff
                      ::hang-expand ::hang-size ::hang? ::indent ::indent-arg
                      ::pair-hang?]))
(s/def ::map
  (only-keys :opt-un [::comma? ::flow? ::force-nl? ::hang-adjust ::hang-diff
                      ::hang-expand ::hang? ::indent ::justify? ::justify-hang
                      ::justify-tuning ::key-color ::key-depth-color
                      ::key-ignore ::key-ignore-silent ::key-order
                      ::nl-separator? ::sort-in-code? ::sort?]))
(s/def ::max-depth number?)
(s/def ::max-hang-count number?)
(s/def ::max-hang-dept number?)
(s/def ::max-hang-span number?)
(s/def ::max-length number?)
(s/def ::object (only-keys :opt-un [::indent ::wrap-coll? ::wrap-after-multi?]))
(s/def ::old? ::boolean)
(s/def ::pair
  (only-keys :opt-un [::flow? ::force-nl? ::hang-diff ::hang-expand ::hang?
                      ::indent ::justify? ::justify-hang ::justify-tuning
                      ::nl-separator?]))
(s/def ::pair-fn
  (only-keys :opt-un [::hang-diff ::hang-expand ::hang-size ::hang?]))
(s/def ::parse (only-keys :opt-un [::interpose ::left-space]))
(s/def ::parse-string-all? ::boolean)
(s/def ::parse-string? ::boolean)
(s/def ::process-bang-zprint? ::boolean)
(s/def ::promise (only-keys :opt-un [::object?]))
(s/def ::reader-cond
  (only-keys :opt-un [::comma? ::force-nl? ::hang-diff ::hang-expand ::hang?
                      ::indent ::key-order ::sort-in-code? ::sort?]))
(s/def ::record (only-keys :opt-un [::hang? ::record-type? ::to-string?]))
(s/def ::remove
  (only-keys :opt-un [::fn-force-nl ::fn-gt2-force-nl ::fn-gt3-force-nl
                      :alt/extend]))
(s/def ::return-cvec? ::boolean)
(s/def ::set
  (only-keys :opt-un [::indent ::wrap-after-multi? ::wrap-coll? ::wrap?]))
(s/def ::spaces? ::boolean)
(s/def ::spec (only-keys :opt-un [::docstring? ::value]))
(s/def ::style ::style-value)
(s/def ::style-map map?)
(s/def ::tab (only-keys :opt-un [::expand? ::size]))
(s/def ::trim-comments? ::boolean)
(s/def ::tuning
  (only-keys :opt-un [::hang-flow ::hang-type-flow ::hang-flow-limit
                      ::general-hang-adjust ::hang-if-equal-flow?]))
(s/def :alt/uneval (only-keys :opt-un [::color-map]))
(s/def ::user-fn-map ::fn-map-value)
(s/def ::vector
  (only-keys :opt-un [::indent ::wrap-after-multi? ::wrap-coll? ::wrap?]))
(s/def ::version string?)
(s/def ::width number?)
(s/def ::zipper? ::boolean)

;;
;; # Top level options map
;;

(s/def ::options
  (only-keys
    :opt-un [::agent ::array ::atom ::auto-width? ::binding ::color-map
             :alt/comment ::configured? ::dbg? ::dbg-print? ::dbg-ge ::delay
             ::do-in-hang? ::drop? ::extend ::file? ::fn-force-nl
             ::fn-gt2-force-nl ::fn-gt3-force-nl ::fn-map ::fn-name ::fn-obj
             ::format ::future ::indent ::list ::map ::max-depth
             ::max-hang-count ::max-hang-depth ::max-hang-span ::max-length
             ::object ::old? ::pair ::pair-fn ::parse ::parse-string-all?
             ::parse-string? ::process-bang-zprint? ::promise ::reader-cond
             ::record ::remove ::return-cvec? ::set ::spaces? ::spec ::style
             ::style-map ::tab ::trim-comments? ::tuning :alt/uneval
             ::user-fn-map ::vector ::version ::width ::zipper?]))

(defn validate-basic
  "Using spec defined above, validate the given options map.  Return
  nil if no errors, or a string containing errors if any."
  ([options source-str]
   (try (if (s/valid? ::options options)
          nil
          (if source-str
            (str "In " source-str ", " (s/explain-str ::options options))
            (s/explain-str ::options options)))
        (catch :default e
          (if source-str
            (str "In " source-str
                 ", validation failed completely because: " (.-message e))
            (str "Validation failed completely because: " (.-message e))))))
  ([options] (validate-basic options nil)))
