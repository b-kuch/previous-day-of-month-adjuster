package pl.za.community.forum;

import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;


public class PreviousOrSame implements TemporalAdjuster {
    private final int dayOfMonth;

    public PreviousOrSame(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    @Override
    public Temporal adjustInto(Temporal temporal) {
        var temporalDay = temporal.get(ChronoField.DAY_OF_MONTH);
        if (temporalDay < dayOfMonth) {
            return temporal
                    .with(ChronoField.MONTH_OF_YEAR, temporal.get(ChronoField.MONTH_OF_YEAR) - 1)
                    .with(ChronoField.DAY_OF_MONTH, dayOfMonth);
        }
        return temporal.with(ChronoField.DAY_OF_MONTH, dayOfMonth);
    }
}
