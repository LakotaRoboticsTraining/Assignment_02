import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MainTest {

    @Test
    @DisplayName("Challenge 1: Team / Driver / Match lines")
    void challenge1_printsMatchInfo() {
        List<String> lines = nonEmptyLines(runMain());

        assertTrue(
            lines.size() >= 3,
            "challenge1 failed - print at least 3 separate lines (Team, Driver, and Match)."
        );
        assertTrue(
            hasLabel(lines, "team"),
            "challenge1 failed - no line includes the label Team (example: Team: 1038)."
        );
        assertTrue(
            hasLabel(lines, "driver"),
            "challenge1 failed - no line includes the label Driver (example: Driver: Alex)."
        );
        assertTrue(
            hasLabel(lines, "match"),
            "challenge1 failed - no line includes the label Match (example: Match: 3)."
        );
    }

    @Test
    @DisplayName("Challenge 2: Drive speed and Autonomous")
    void challenge2_printsDriveSettings() {
        List<String> lines = nonEmptyLines(runMain());

        assertTrue(
            hasDriveLabel(lines),
            "challenge2 failed - print a Drive speed line (example: Drive speed: 0.75). "
                + "Note: a Driver line from Challenge 1 does not count."
        );
        assertTrue(
            hasLabel(lines, "autonomous"),
            "challenge2 failed - print an Autonomous line (example: Autonomous: true)."
        );
    }

    @Test
    @DisplayName("Challenge 3: Cargo tracking")
    void challenge3_tracksCargoCount() {
        List<String> lines = nonEmptyLines(runMain());

        List<String> cargoLines = lines.stream()
            .filter(MainTest::isCargoCountLine)
            .collect(Collectors.toList());

        assertTrue(
            cargoLines.stream().anyMatch(line -> containsNumber(line, "0")),
            "challenge3 failed - print cargoCount when it is 0 (example: Cargo: 0)."
        );
        assertTrue(
            cargoLines.stream().anyMatch(line -> containsNumber(line, "3")),
            "challenge3 failed - after adding 3, print cargoCount again (example: Cargo: 3)."
        );
        assertTrue(
            hasHasCargoTrue(lines),
            "challenge3 failed - print hasCargo as true after cargoCount > 0 "
                + "(example: Has cargo: true or hasCargo: true)."
        );

        int indexOfZero = indexOfCargoWith(lines, "0");
        int indexOfThree = indexOfCargoWith(lines, "3");
        assertTrue(
            indexOfZero >= 0 && indexOfThree > indexOfZero,
            "challenge3 failed - print Cargo 0 before Cargo 3 (start at 0, then add 3)."
        );
    }

    /** Cargo count lines, not the hasCargo boolean line. */
    private static boolean isCargoCountLine(String line) {
        String compact = compact(line);
        if (compact.contains("hascargo")) {
            return false;
        }
        return compact.contains("cargo");
    }

    private static boolean hasHasCargoTrue(List<String> lines) {
        return lines.stream().anyMatch(line -> {
            String compact = compact(line);
            return compact.contains("hascargo") && compact.contains("true");
        });
    }

    /** True drive label; does not treat "driver" as "drive". */
    private static boolean hasDriveLabel(List<String> lines) {
        return lines.stream().anyMatch(line -> {
            String lower = line.toLowerCase(Locale.ROOT);
            return lower.matches("(?s).*\\bdrive\\b.*");
        });
    }

    private static boolean hasLabel(List<String> lines, String label) {
        String wanted = label.toLowerCase(Locale.ROOT);
        return lines.stream().anyMatch(line -> line.toLowerCase(Locale.ROOT).contains(wanted));
    }

    private static boolean containsNumber(String line, String value) {
        return line.matches("(?s).*\\b" + value + "\\b.*") || line.contains(value);
    }

    private static String compact(String line) {
        return line.toLowerCase(Locale.ROOT).replaceAll("[^a-z0-9]", "");
    }

    private static int indexOfCargoWith(List<String> lines, String value) {
        for (int i = 0; i < lines.size(); i++) {
            if (isCargoCountLine(lines.get(i)) && containsNumber(lines.get(i), value)) {
                return i;
            }
        }
        return -1;
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
