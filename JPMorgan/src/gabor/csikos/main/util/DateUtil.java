/**
 * 
 */
package gabor.csikos.main.util;

import gabor.csikos.main.api.Currency;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * @author Gabor Csikos
 * 
 */
public final class DateUtil {

    private DateUtil() {

    }

    public static boolean isWeekend(LocalDate date, Currency currency) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        if (currency.startsOnMondayEndOnFriday()) {
            return (DayOfWeek.SUNDAY == dayOfWeek || DayOfWeek.SATURDAY == dayOfWeek);
        } else {
            return (DayOfWeek.FRIDAY == dayOfWeek || DayOfWeek.SATURDAY == dayOfWeek);
        }
    }

    public static LocalDate shifted(LocalDate date, Currency currency) {

        while (DateUtil.isWeekend(date, currency)) {
            date = date.plusDays(1);
        }
        return date;
    }
}
