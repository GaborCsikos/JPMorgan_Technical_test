package gabor.csikos.main.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import gabor.csikos.main.api.Currency;

import java.time.LocalDate;

import org.junit.Test;

public class DateUtilTest {

    @Test
    public void isSundayWeekend_SAR() {
        LocalDate date = LocalDate.of(2017, 6, 25);

        assertFalse(DateUtil.isWeekend(date, Currency.SAR));
    }

    @Test
    public void isThursdayWeekend_SAR() {
        LocalDate date = LocalDate.of(2017, 6, 29);

        assertFalse(DateUtil.isWeekend(date, Currency.SAR));
    }

    @Test
    public void isFridayWeekend_SAR() {
        LocalDate date = LocalDate.of(2017, 6, 16);

        assertTrue(DateUtil.isWeekend(date, Currency.SAR));
    }

    @Test
    public void isSaturdayWeekend_SAR() {
        LocalDate date = LocalDate.of(2017, 6, 17);

        assertTrue(DateUtil.isWeekend(date, Currency.SAR));
    }

    @Test
    public void isMondayyWeekend_SGP() {
        LocalDate date = LocalDate.of(2017, 6, 19);

        assertFalse(DateUtil.isWeekend(date, Currency.SGP));
    }

    @Test
    public void isFridayWeekend_SGP() {
        LocalDate date = LocalDate.of(2017, 6, 30);

        assertFalse(DateUtil.isWeekend(date, Currency.SGP));
    }

    @Test
    public void isSundayWeekend_SGP() {
        LocalDate date = LocalDate.of(2017, 6, 25);

        assertTrue(DateUtil.isWeekend(date, Currency.SGP));
    }

    @Test
    public void isSaturdayWeekend_SGP() {
        LocalDate date = LocalDate.of(2017, 6, 24);

        assertTrue(DateUtil.isWeekend(date, Currency.SGP));
    }

    @Test
    public void shiftFromFriday_SAR() {
        LocalDate date = LocalDate.of(2017, 6, 23);

        LocalDate dateExpected = LocalDate.of(2017, 6, 25);

        assertEquals(dateExpected, DateUtil.shifted(date, Currency.SAR));

    }

    @Test
    public void shiftFromSaturday_SAR() {
        LocalDate date = LocalDate.of(2017, 6, 24);

        LocalDate dateExpected = LocalDate.of(2017, 6, 25);

        assertEquals(dateExpected, DateUtil.shifted(date, Currency.SAR));
    }

    @Test
    public void noShift_SAR() {
        LocalDate date = LocalDate.of(2017, 6, 22);

        LocalDate dateExpected = LocalDate.of(2017, 6, 22);

        assertEquals(dateExpected, DateUtil.shifted(date, Currency.SAR));
    }

    @Test
    public void shiftFromSaturday_SGP() {
        LocalDate date = LocalDate.of(2017, 6, 24);

        LocalDate dateExpected = LocalDate.of(2017, 6, 26);

        assertEquals(dateExpected, DateUtil.shifted(date, Currency.SGP));

    }

    @Test
    public void shiftFromSunday_SGP() {
        LocalDate date = LocalDate.of(2017, 6, 25);

        LocalDate dateExpected = LocalDate.of(2017, 6, 26);

        assertEquals(dateExpected, DateUtil.shifted(date, Currency.SGP));
    }

    @Test
    public void noShift_SGP() {
        LocalDate date = LocalDate.of(2017, 6, 26);

        LocalDate dateExpected = LocalDate.of(2017, 6, 26);

        assertEquals(dateExpected, DateUtil.shifted(date, Currency.SGP));
    }
}
