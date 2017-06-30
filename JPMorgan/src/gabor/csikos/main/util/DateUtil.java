/**
 * 
 */
package gabor.csikos.main.util;

import gabor.csikos.main.api.Currency;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Gabor Csikos
 * 
 */
public final class DateUtil {

    private DateUtil() {

    }

    public static boolean isWeekend(Date date, Currency currency) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (currency.startsOnMondayEndOnFriday()) {
            return (Calendar.SUNDAY == dayOfWeek || Calendar.SATURDAY == dayOfWeek);
        } else {
            return (Calendar.FRIDAY == dayOfWeek || Calendar.SATURDAY == dayOfWeek);
        }
    }

    public static Date shifted(Date date, Currency currency) {
        Calendar cal = new GregorianCalendar();
        Date copy = new Date(date.getTime());
        cal.setTime(copy);

        while (DateUtil.isWeekend(cal.getTime(), currency)) {
            cal.add(Calendar.DATE, 1);
        }
        return cal.getTime();
    }
}
