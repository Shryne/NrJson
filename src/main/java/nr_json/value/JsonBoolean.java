package nr_json.value;

/**
 * A parser for a string. Converts it into a boolean. Example(s):
 * <pre>
 *     {@code
 *     "true".value() =>  true
 *     "false".value() => false
 *     "...".value() => IllegalStateException()
 *     }
 * </pre>
 * Instances of this class are equal to all objects that have the same interface
 * and return the same values from {@link #isValid()} and {@link #value()}.
 * Example(s):
 * <pre>
 *     {@code
 *     "true".equals("true") =>  true
 *     "false".equals("true") => false
 *     "...".equals("dasdas") => true (value-method won't be called)
 *     "...".equals("true") => false
 *     }
 * </pre>
 * <p>This class is immutable and thread-safe.</p>
 */
public class JsonBoolean {
    /**
     * The string to convert.
     */
    private final String source;

    /**
     * @param source The string to convert.
     */
    public JsonBoolean(String source) {
        this.source = source;
    }

    public final boolean isValid() {
        return source.equals("true") || source.equals("false");
    }

    public final boolean value() {
        if (source.equals("true")) {
            return true;
        } else if (source.equals("false")) {
            return false;
        } else {
            throw new IllegalStateException(
                String.join(
                    "",
                    "The given source doesn't provide a correct",
                    "boolean. Source: ",
                    source
                )
            );
        }
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof JsonBoolean)) return false;
        final var other = (JsonBoolean) obj;
        return (!other.isValid() && !isValid()) ||
            (other.isValid() && isValid() && other.value() == value());
    }

    @Override
    public final int hashCode() {
        if (isValid()) return Boolean.hashCode(value());
        else return 17;
    }

    @Override
    public final String toString() {
        return getClass().getSimpleName() + "(\"" + source + "\")";
    }
}
