package pl.za.community.forum;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PreviousSchedulerRunDateAdjusterTests {
    private final PreviousSchedulerRunDateAdjuster testedAdjuster = new PreviousSchedulerRunDateAdjuster();

    public static Stream<Arguments> sixteenth() {
        return Stream.of(Arguments.of(
                LocalDate.of(2024, 9, 16),
                LocalDate.of(2024, 9, 16)));
    }
    public static Stream<Arguments> twentyFifth() {
        return Stream.of(Arguments.of(
                LocalDate.of(2024, 9, 25),
                LocalDate.of(2024, 9, 25)));
    }

    public static Stream<Arguments> firstToFifteenth() {
        var expected = LocalDate.of(2024, 8, 25);
        return IntStream.range(1, 16).mapToObj(day ->
                Arguments.of(LocalDate.of(2024, 9, day), expected)
        );
    }

    public static Stream<Arguments> seventeenthToTwentyFourth() {
        var expected = LocalDate.of(2024, 9, 16);
        return IntStream.range(17, 25).mapToObj(day ->
                Arguments.of(LocalDate.of(2024, 9, day), expected)
        );
    }

    public static Stream<Arguments> twentySixthToThirtieth() {
        var expected = LocalDate.of(2024, 9, 25);
        return IntStream.range(26, 31).mapToObj(day ->
                Arguments.of(LocalDate.of(2024, 9, day), expected)
        );
    }

    @ParameterizedTest
    @MethodSource({"sixteenth", "twentyFifth",
            "firstToFifteenth", "seventeenthToTwentyFourth", "twentySixthToThirtieth"})
    void test(LocalDate given, LocalDate expected) {
        assertEquals(expected, given.with(testedAdjuster));
    }
}