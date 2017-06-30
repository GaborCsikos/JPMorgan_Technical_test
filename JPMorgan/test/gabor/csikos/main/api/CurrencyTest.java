package gabor.csikos.main.api;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CurrencyTest {

    @Test
    public void isWorkingDaysOnFridayTest() {
        assertFalse(Currency.AED.startsOnMondayEndOnFriday());
        assertFalse(Currency.SAR.startsOnMondayEndOnFriday());
        assertTrue(Currency.SGP.startsOnMondayEndOnFriday());
    }

}
