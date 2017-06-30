package gabor.csikos.main.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import gabor.csikos.main.api.Currency;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Test;

public class DateUtilTest {

    @Test
    public void isSundayWeekend_SAR() {
        LocalDateTime dateTime = LocalDateTime.of(2017, 6, 25, 2, 0);
        Date converted = Date.from(dateTime.atZone(ZoneId.systemDefault())
                .toInstant());

        assertFalse(DateUtil.isWeekend(converted, Currency.SAR));
    }

    @Test
    public void isThursdayWeekend_SAR() {
        LocalDateTime dateTime = LocalDateTime.of(2017, 6, 29, 2, 0);
        Date converted = Date.from(dateTime.atZone(ZoneId.systemDefault())
                .toInstant());

        assertFalse(DateUtil.isWeekend(converted, Currency.SAR));
    }

    @Test
    public void isFridayWeekend_SAR() {
        LocalDateTime dateTime = LocalDateTime.of(2017, 6, 16, 2, 0);
        Date converted = Date.from(dateTime.atZone(ZoneId.systemDefault())
                .toInstant());

        assertTrue(DateUtil.isWeekend(converted, Currency.SAR));
    }

    @Test
    public void isSaturdayWeekend_SAR() {
        LocalDateTime dateTime = LocalDateTime.of(2017, 6, 17, 2, 0);
        Date converted = Date.from(dateTime.atZone(ZoneId.systemDefault())
                .toInstant());

        assertTrue(DateUtil.isWeekend(converted, Currency.SAR));
    }

    @Test
    public void isMondayyWeekend_SGP() {
        LocalDateTime dateTime = LocalDateTime.of(2017, 6, 19, 2, 0);
        Date converted = Date.from(dateTime.atZone(ZoneId.systemDefault())
                .toInstant());

        assertFalse(DateUtil.isWeekend(converted, Currency.SGP));
    }

    @Test
    public void isFridayWeekend_SGP() {
        LocalDateTime dateTime = LocalDateTime.of(2017, 6, 30, 2, 0);
        Date converted = Date.from(dateTime.atZone(ZoneId.systemDefault())
                .toInstant());

        assertFalse(DateUtil.isWeekend(converted, Currency.SGP));
    }

    @Test
    public void isSundayWeekend_SGP() {
        LocalDateTime dateTime = LocalDateTime.of(2017, 6, 25, 2, 0);
        Date converted = Date.from(dateTime.atZone(ZoneId.systemDefault())
                .toInstant());

        assertTrue(DateUtil.isWeekend(converted, Currency.SGP));
    }

    @Test
    public void isSaturdayWeekend_SGP() {
        LocalDateTime dateTime = LocalDateTime.of(2017, 6, 24, 2, 0);
        Date converted = Date.from(dateTime.atZone(ZoneId.systemDefault())
                .toInstant());

        assertTrue(DateUtil.isWeekend(converted, Currency.SGP));
    }

    @Test
    public void shiftFromFriday_SAR() {
        LocalDateTime dateTime = LocalDateTime.of(2017, 6, 23, 2, 0);
        Date converted = Date.from(dateTime.atZone(ZoneId.systemDefault())
                .toInstant());

        LocalDateTime dateTimeExpected = LocalDateTime.of(2017, 6, 25, 2, 0);
        Date convertedExpected = Date.from(dateTimeExpected.atZone(
                ZoneId.systemDefault()).toInstant());
        assertEquals(convertedExpected,
                DateUtil.shifted(converted, Currency.SAR));

    }

    @Test
    public void shiftFromSaturday_SAR() {
        LocalDateTime dateTime = LocalDateTime.of(2017, 6, 24, 2, 0);
        Date converted = Date.from(dateTime.atZone(ZoneId.systemDefault())
                .toInstant());

        LocalDateTime dateTimeExpected = LocalDateTime.of(2017, 6, 25, 2, 0);
        Date convertedExpected = Date.from(dateTimeExpected.atZone(
                ZoneId.systemDefault()).toInstant());
        assertEquals(convertedExpected,
                DateUtil.shifted(converted, Currency.SAR));
    }

    @Test
    public void noShift_SAR() {
        LocalDateTime dateTime = LocalDateTime.of(2017, 6, 22, 2, 0);
        Date converted = Date.from(dateTime.atZone(ZoneId.systemDefault())
                .toInstant());

        LocalDateTime dateTimeExpected = LocalDateTime.of(2017, 6, 22, 2, 0);
        Date convertedExpected = Date.from(dateTimeExpected.atZone(
                ZoneId.systemDefault()).toInstant());
        assertEquals(convertedExpected,
                DateUtil.shifted(converted, Currency.SAR));
    }

    @Test
    public void shiftFromSaturday_SGP() {
        LocalDateTime dateTime = LocalDateTime.of(2017, 6, 24, 2, 0);
        Date converted = Date.from(dateTime.atZone(ZoneId.systemDefault())
                .toInstant());

        LocalDateTime dateTimeExpected = LocalDateTime.of(2017, 6, 26, 2, 0);
        Date convertedExpected = Date.from(dateTimeExpected.atZone(
                ZoneId.systemDefault()).toInstant());
        assertEquals(convertedExpected,
                DateUtil.shifted(converted, Currency.SGP));

    }

    @Test
    public void shiftFromSunday_SGP() {
        LocalDateTime dateTime = LocalDateTime.of(2017, 6, 25, 2, 0);
        Date converted = Date.from(dateTime.atZone(ZoneId.systemDefault())
                .toInstant());

        LocalDateTime dateTimeExpected = LocalDateTime.of(2017, 6, 26, 2, 0);
        Date convertedExpected = Date.from(dateTimeExpected.atZone(
                ZoneId.systemDefault()).toInstant());
        assertEquals(convertedExpected,
                DateUtil.shifted(converted, Currency.SGP));
    }

    @Test
    public void noShift_SGP() {
        LocalDateTime dateTime = LocalDateTime.of(2017, 6, 26, 2, 0);
        Date converted = Date.from(dateTime.atZone(ZoneId.systemDefault())
                .toInstant());

        LocalDateTime dateTimeExpected = LocalDateTime.of(2017, 6, 26, 2, 0);
        Date convertedExpected = Date.from(dateTimeExpected.atZone(
                ZoneId.systemDefault()).toInstant());
        assertEquals(convertedExpected,
                DateUtil.shifted(converted, Currency.SGP));
    }
}
