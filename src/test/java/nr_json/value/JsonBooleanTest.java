package nr_json.value;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JsonBooleanTest {
    @Test
    public void empty() {
        final var bool = new JsonBoolean("");
        assertFalse(bool.isValid());
        assertThrows(IllegalStateException.class, bool::value);
    }

    @Test
    public void convertsFalse() {
        final var value = false;
        final var bool = new JsonBoolean(Boolean.toString(value));
        assertTrue(bool.isValid());
        assertEquals(value, bool.value());
    }

    @Test
    public void convertsTrue() {
        final var value = true;
        final var bool = new JsonBoolean(Boolean.toString(value));
        assertTrue(bool.isValid());
        assertEquals(value, bool.value());
    }

    @Test
    public void falseToStringFormat() {
        final var value = "true";
        assertEquals(
            "JsonBoolean(\"" + value + "\")",
            new JsonBoolean(value).toString()
        );
    }

    @Test
    public void trueToStringFormat() {
        final var value = "false";
        assertEquals(
            "JsonBoolean(\"" + value + "\")",
            new JsonBoolean(value).toString()
        );
    }

    @Test
    public void toStringDoesntThrow() {
        final var value = "something else";
        assertEquals(
            "JsonBoolean(\"" + value + "\")",
            new JsonBoolean(value).toString()
        );
    }

    /**
     * Note: I convert the boolean to create new strings. This way I can test
     * that the equals method doesn't use the internal string and test it for
     * reference equality.
     */
    @Test
    public void equalsTrue() {
        final var value = true;
        assertEquals(
            new JsonBoolean(Boolean.toString(value)),
            new JsonBoolean(Boolean.toString(value))
        );
    }

    /**
     * @see #equalsTrue()
     */
    @Test
    public void equalsFalse() {
        final var value = false;
        assertEquals(
            new JsonBoolean(Boolean.toString(value)),
            new JsonBoolean(Boolean.toString(value))
        );
    }

    @Test
    public void equalsSameInvalidValues() {
        final var value = "sdadme";
        assertEquals(
            new JsonBoolean(value),
            new JsonBoolean(value)
        );
    }

    @Test
    public void equalsDifferentInvalidValues() {
        assertEquals(
            new JsonBoolean("sdjie"),
            new JsonBoolean("....")
        );
    }

    @Test
    public void notEqualsDifferentValues() {
        final var value = false;
        assertNotEquals(
            new JsonBoolean(Boolean.toString(value)),
            new JsonBoolean(Boolean.toString(!value))
        );
    }

    @Test
    public void notEqualsValidAndInvalidValues() {
        assertNotEquals(
            new JsonBoolean("true"),
            new JsonBoolean("dsuja")
        );
    }

    @Test
    public void hashCodeTrue() {
        final var value = true;
        assertEquals(
            Boolean.hashCode(value),
            new JsonBoolean(Boolean.toString(value)).hashCode()
        );
    }

    @Test
    public void hashCodeFalse() {
        final var value = false;
        assertEquals(
            Boolean.hashCode(value),
            new JsonBoolean(Boolean.toString(value)).hashCode()
        );
    }

    @Test
    public void hashCodeInvalidValue() {
        assertEquals(
            new JsonBoolean("elsd").hashCode(),
            new JsonBoolean("dunefn").hashCode()
        );
    }
}
