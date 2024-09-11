import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

public class PreviousSchedulerRunDateAdjuster implements TemporalAdjuster {
    private final int firstSchedulerRunDay = 16;
    private final int secondSchedulerRunDay = 25;

    private final PreviousOrSameDayOfMonth firstSchedulerRun = new PreviousOrSameDayOfMonth(firstSchedulerRunDay);
    private final PreviousOrSameDayOfMonth secondSchedulerRun = new PreviousOrSameDayOfMonth(secondSchedulerRunDay);
    @Override
    public Temporal adjustInto(Temporal temporal) {
        var temporalDay = temporal.get(ChronoField.DAY_OF_MONTH);
        if (temporalDay >= firstSchedulerRunDay && temporalDay < secondSchedulerRunDay) {
            return temporal.with(firstSchedulerRun);
        } else {
            return temporal.with(secondSchedulerRun);
        }
    }
}
