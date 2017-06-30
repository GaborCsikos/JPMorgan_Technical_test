/**
 * 
 */
package gabor.csikos.main.api;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CommandTest {

    @Test
    public void testBuyCommand() {
        assertEquals("B", Command.BUY.getCommand());
    }

    @Test
    public void testSellCommand() {
        assertEquals("S", Command.SELL.getCommand());
    }

    @Test
    public void testavailableCommand() {
        assertEquals(2, Command.values().length);
    }

}
