import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

class MainTest {

    private String[] getOutputLines() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        try {
            System.setOut(new PrintStream(outputStream));
            Main.main(new String[]{});
        } finally {
            System.setOut(originalOut);
        }

        return outputStream.toString().lines()
            .filter(line -> !line.trim().isEmpty())
            .toArray(String[]::new);
    }

    private String primitiveValue(int lineNumber) {
        String[] lines = getOutputLines();

        assertTrue(lines.length >= 8,
            "Main should print at least eight non-empty lines for the eight primitive values.");

        String line = lines[lineNumber];
        int equalsIndex = line.lastIndexOf('=');
        int colonIndex = line.lastIndexOf(':');
        int separatorIndex = Math.max(equalsIndex, colonIndex);

        if (separatorIndex >= 0) {
            return line.substring(separatorIndex + 1).trim();
        }

        String[] parts = line.trim().split("\\s+");
        return parts[parts.length - 1];
    }

    @Test
    void printsAnIntValue() {
        String value = primitiveValue(0);
        assertDoesNotThrow(() -> Integer.parseInt(value),
            "The first primitive output should end with a valid int value.");
    }

    @Test
    void printsALongValue() {
        String value = primitiveValue(1);
        assertDoesNotThrow(() -> Long.parseLong(value),
            "The second primitive output should end with a valid long value.");
    }

    @Test
    void printsADoubleValue() {
        String value = primitiveValue(2);
        assertDoesNotThrow(() -> Double.parseDouble(value),
            "The third primitive output should end with a valid double value.");
        assertTrue(value.contains(".") || value.contains("E") || value.contains("e"),
            "The double output should be printed in decimal or scientific notation.");
    }

    @Test
    void printsAShortValue() {
        String value = primitiveValue(3);
        assertDoesNotThrow(() -> Short.parseShort(value),
            "The fourth primitive output should end with a valid short value.");
    }

    @Test
    void printsABooleanValue() {
        String value = primitiveValue(4);
        assertTrue(value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false"),
            "The fifth primitive output should end with true or false.");
    }

    @Test
    void printsACharValue() {
        String value = primitiveValue(5);
        assertEquals(1, value.length(),
            "The sixth primitive output should end with exactly one char value.");
    }

    @Test
    void printsAFloatValue() {
        String value = primitiveValue(6);
        assertDoesNotThrow(() -> Float.parseFloat(value),
            "The seventh primitive output should end with a valid float value.");
        assertTrue(value.contains(".") || value.contains("E") || value.contains("e"),
            "The float output should be printed in decimal or scientific notation.");
    }

    @Test
    void printsAByteValue() {
        String value = primitiveValue(7);
        assertDoesNotThrow(() -> Byte.parseByte(value),
            "The eighth primitive output should end with a valid byte value.");
    }
}
