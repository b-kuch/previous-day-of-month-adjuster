import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class PreviousOrSameDayOfMonthDayOfMonthTests {
    @ParameterizedTest
    @MethodSource({"sixteenth", "firstToSixteenth", "seventeenthToThirtieth", "inPreviousYear"})
    void testDates(int dayOfMonth, LocalDate given, LocalDate expected) {
        var adjuster = new PreviousOrSameDayOfMonth(dayOfMonth);

        assertEquals(expected, given.with(adjuster));
    }

    public static Stream<Arguments> sixteenth() {
        var expected = LocalDate.of(2024, 9, 16);
        return Stream.of(
                Arguments.of(expected.getDayOfMonth(), LocalDate.of(2024, 9, 16), expected)
        );
    }

    public static Stream<Arguments> firstToSixteenth() {
        var expected = LocalDate.of(2024, 8, 16);
        return IntStream.range(1, 16).mapToObj(day ->
                Arguments.of(expected.getDayOfMonth(), LocalDate.of(2024, 9, day), expected)
        );
    }

    public static Stream<Arguments> seventeenthToThirtieth() {
        var expected = LocalDate.of(2024, 9, 16);
        return IntStream.range(17, 31).mapToObj(day ->
                Arguments.of(expected.getDayOfMonth(), LocalDate.of(2024, 9, day), expected)
        );
    }

    public static Stream<Arguments> inPreviousYear() {
        var expected = LocalDate.of(2023, 12, 16);
        return IntStream.range(1, 16).mapToObj(day ->
                Arguments.of(expected.getDayOfMonth(), LocalDate.of(2024, 1, day), expected)
        );
    }

}
