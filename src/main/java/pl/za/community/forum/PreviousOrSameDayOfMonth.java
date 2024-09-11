package pl.za.community.forum;

import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;


public class PreviousOrSameDayOfMonth implements TemporalAdjuster {
    private final int dayOfMonth;

    public PreviousOrSameDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    @Override
    public Temporal adjustInto(Temporal temporal) {
        var temporalDay = temporal.get(ChronoField.DAY_OF_MONTH);
        if (temporalDay < dayOfMonth) {
            return temporal
                    .minus(1, ChronoUnit.MONTHS)
                    .with(ChronoField.DAY_OF_MONTH, dayOfMonth);
        }
        return temporal.with(ChronoField.DAY_OF_MONTH, dayOfMonth);
    }
}
