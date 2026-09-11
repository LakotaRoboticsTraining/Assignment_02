import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

class MainTest {

    @Test
    void challenge1_printsMatchInfo() {
        List<String> lines = nonEmptyLines(runMain());

        assertTrue(
            lines.size() >= 3,
            "Challenge 1: print at least three lines (Team, Driver, and Match)."
        );
        assertTrue(
            hasLineContaining(lines, "team"),
            "Challenge 1: print a line that includes the label Team."
        );
        assertTrue(
            hasLineContaining(lines, "driver"),
            "Challenge 1: print a line that includes the label Driver."
        );
        assertTrue(
            hasLineContaining(lines, "match"),
            "Challenge 1: print a line that includes the label Match."
        );
    }

    @Test
    void challenge2_printsDriveSettings() {
        List<String> lines = nonEmptyLines(runMain());

        assertTrue(
            hasLineContaining(lines, "drive"),
            "Challenge 2: print a line that includes the label Drive (for example Drive speed)."
        );
        assertTrue(
            hasLineContaining(lines, "autonomous"),
            "Challenge 2: print a line that includes the label Autonomous."
        );
    }

    @Test
    void challenge3_tracksCargoCount() {
        List<String> lines = nonEmptyLines(runMain());

        List<String> cargoLines = lines.stream()
            .filter(line -> line.toLowerCase(Locale.ROOT).contains("cargo")
                && !line.toLowerCase(Locale.ROOT).contains("has cargo"))
            .collect(Collectors.toList());

        assertTrue(
            cargoLines.stream().anyMatch(line -> line.contains("0")),
            "Challenge 3: print cargoCount when it is 0 (include Cargo and 0 in the line)."
        );
        assertTrue(
            cargoLines.stream().anyMatch(line -> line.contains("3")),
            "Challenge 3: after cargoCount = cargoCount + 3, print again (include Cargo and 3)."
        );
        assertTrue(
            lines.stream().anyMatch(line -> {
                String lower = line.toLowerCase(Locale.ROOT);
                return lower.contains("has cargo") && lower.contains("true");
            }),
            "Challenge 3: print hasCargo after cargoCount > 0 (include Has cargo and true)."
        );

        int indexOfZero = indexOfCargoWith(lines, "0");
        int indexOfThree = indexOfCargoWith(lines, "3");
        assertTrue(
            indexOfZero >= 0 && indexOfThree > indexOfZero,
            "Challenge 3: print Cargo 0 before Cargo 3 (start at 0, then add 3)."
        );
    }

    private static int indexOfCargoWith(List<String> lines, String value) {
        for (int i = 0; i < lines.size(); i++) {
            String lower = lines.get(i).toLowerCase(Locale.ROOT);
            if (lower.contains("cargo") && !lower.contains("has cargo") && lines.get(i).contains(value)) {
                return i;
            }
        }
        return -1;
    }

    private static boolean hasLineContaining(List<String> lines, String text) {
        return lines.stream().anyMatch(line -> line.toLowerCase(Locale.ROOT).contains(text));
    }

    private static String runMain() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        try {
            System.setOut(new PrintStream(outputStream));
            Main.main(new String[] {});
            return outputStream.toString();
        } finally {
            System.setOut(originalOut);
        }
    }

    private static List<String> nonEmptyLines(String output) {
        return Arrays.stream(output.split("\\R"))
            .map(String::trim)
            .filter(line -> !line.isEmpty())
            .collect(Collectors.toList());
    }
}
