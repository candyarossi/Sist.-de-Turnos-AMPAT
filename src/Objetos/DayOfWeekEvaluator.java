package Objetos;

import com.toedter.calendar.IDateEvaluator;
import java.awt.Color;
import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DayOfWeekEvaluator implements IDateEvaluator {

    private final List<DayOfWeek> validDaysOfWeek;

    public DayOfWeekEvaluator(List<DayOfWeek> validDaysOfWeek) {
        this.validDaysOfWeek = validDaysOfWeek;
    }

    private DayOfWeek determineDayOfWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        return DayOfWeek.of(convertSundayFirstToMondayFirst(dayOfWeek));
    }

    private int convertSundayFirstToMondayFirst(int sundayFirstValue) {
        // Converts from weeks that are Sunday-Saturday (1-7)
        // to weeks that are Monday-Sunday (1-7)
        if (sundayFirstValue == 1) {
            return 7;
        }
        return sundayFirstValue - 1;
    }

    @Override
    public boolean isSpecial(Date date) {
        return false;
    }

    @Override
    public Color getSpecialForegroundColor() {
        return null;
    }

    @Override
    public Color getSpecialBackroundColor() {
        return null;
    }

    @Override
    public String getSpecialTooltip() {
        return null;
    }

    @Override
    public boolean isInvalid(Date date) {
        DayOfWeek dayOfWeek = determineDayOfWeek(date);
        
        // Estaba return !validDaysOfWeek.contains(dayOfWeek);
        return validDaysOfWeek.contains(dayOfWeek);
    }

    @Override
    public Color getInvalidForegroundColor() {
        return null;
    }

    @Override
    public Color getInvalidBackroundColor() {
        return null;
    }

    @Override
    public String getInvalidTooltip() {
        return null;
    }

}
