/**
 * 
 */
package gabor.csikos.main.transaction;

import static org.junit.Assert.assertEquals;
import gabor.csikos.main.api.Command;
import gabor.csikos.main.api.Currency;
import gabor.csikos.main.api.TransactionDTO;
import gabor.csikos.main.domain.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TransactionManagerTest {

    private TransactionManager manager = new TransactionManager();
    private TransactionFactory factory = new TransactionFactory();

    @Test
    public void rankEntityByIncomingAmount() {
        LocalDate fooDate = LocalDate.of(2017, 6, 25);
        LocalDate barDate = LocalDate.of(2017, 6, 25);
        LocalDate wontUse = LocalDate.of(2017, 6, 25);

        List<Transaction> incoming = new ArrayList<Transaction>();
        incoming.add(factory.crateTransaction("foo", Command.SELL,
                Currency.AED, BigDecimal.valueOf(10.0), BigDecimal.valueOf(1),
                1l, fooDate));

        incoming.add(factory.crateTransaction("bar", Command.SELL,
                Currency.SAR, BigDecimal.valueOf(20.0), BigDecimal.valueOf(1),
                1l, barDate));

        incoming.add(factory.crateTransaction("notCounting", Command.BUY,
                Currency.SAR, BigDecimal.valueOf(20.0), BigDecimal.valueOf(1),
                1l, wontUse));

        List<Transaction> result = manager.rankEntityByIncomingAmount(incoming);
        assertEquals(2, result.size());
        assertEquals("bar", result.get(0).getEntityName());
        assertEquals("foo", result.get(1).getEntityName());
    }

    @Test
    public void rankEntityByIncomingAmountSameName() {
        LocalDate fooDate = LocalDate.of(2017, 6, 25);
        LocalDate barDate = LocalDate.of(2017, 6, 25);
        LocalDate wontUse = LocalDate.of(2017, 6, 25);

        List<Transaction> incoming = new ArrayList<Transaction>();
        incoming.add(factory.crateTransaction("foo", Command.SELL,
                Currency.AED, BigDecimal.valueOf(10.0), BigDecimal.valueOf(1),
                1l, fooDate));

        incoming.add(factory.crateTransaction("foo", Command.SELL,
                Currency.SAR, BigDecimal.valueOf(20.0), BigDecimal.valueOf(1),
                1l, barDate));

        incoming.add(factory.crateTransaction("notCounting", Command.BUY,
                Currency.SAR, BigDecimal.valueOf(20.0), BigDecimal.valueOf(1),
                1l, wontUse));

        List<Transaction> result = manager.rankEntityByIncomingAmount(incoming);
        assertEquals(1, result.size());
        assertEquals("foo", result.get(0).getEntityName());
        assertEquals(BigDecimal.valueOf(20.0), result.get(0).getUSDAmount());
    }

    @Test
    public void rankEntityByOutGoingAmount() {
        LocalDate fooDate = LocalDate.of(2017, 6, 25);
        LocalDate barDate = LocalDate.of(2017, 6, 25);
        LocalDate wontUse = LocalDate.of(2017, 6, 25);

        List<Transaction> incoming = new ArrayList<Transaction>();
        incoming.add(factory.crateTransaction("foo", Command.BUY, Currency.AED,
                BigDecimal.valueOf(10.0), BigDecimal.valueOf(1), 1l, fooDate));

        incoming.add(factory.crateTransaction("bar", Command.BUY, Currency.SAR,
                BigDecimal.valueOf(20.0), BigDecimal.valueOf(1), 1l, barDate));

        incoming.add(factory.crateTransaction("notCounting", Command.SELL,
                Currency.SAR, BigDecimal.valueOf(20.0), BigDecimal.valueOf(1),
                1l, wontUse));

        List<Transaction> result = manager.rankEntityByOutGoingAmount(incoming);
        assertEquals(2, result.size());
        assertEquals("bar", result.get(0).getEntityName());
        assertEquals("foo", result.get(1).getEntityName());
    }

    @Test
    public void rankEntityByOutGoingAmountSameName() {
        LocalDate fooDate = LocalDate.of(2017, 6, 25);
        LocalDate barDate = LocalDate.of(2017, 6, 25);
        LocalDate wontUse = LocalDate.of(2017, 6, 25);

        List<Transaction> incoming = new ArrayList<Transaction>();
        incoming.add(factory.crateTransaction("foo", Command.BUY, Currency.AED,
                BigDecimal.valueOf(10.0), BigDecimal.valueOf(1), 1l, fooDate));

        incoming.add(factory.crateTransaction("foo", Command.BUY, Currency.SAR,
                BigDecimal.valueOf(20.0), BigDecimal.valueOf(1), 1l, barDate));

        incoming.add(factory.crateTransaction("notCounting", Command.SELL,
                Currency.SAR, BigDecimal.valueOf(20.0), BigDecimal.valueOf(1),
                1l, wontUse));

        List<Transaction> result = manager.rankEntityByOutGoingAmount(incoming);
        assertEquals(1, result.size());
        assertEquals("foo", result.get(0).getEntityName());
        assertEquals(BigDecimal.valueOf(20.0), result.get(0).getUSDAmount());
    }

    @Test
    public void adjustTransactionsForWorkingDay() {
        List<Transaction> incoming = new ArrayList<Transaction>();
        LocalDate adjust = LocalDate.of(2017, 6, 18);
        LocalDate adjusted = LocalDate.of(2017, 6, 19);
        LocalDate dontAdjust = LocalDate.of(2017, 6, 19);

        incoming.add(factory.crateTransaction("foo", Command.BUY, Currency.SGP,
                BigDecimal.valueOf(10.0), BigDecimal.valueOf(1), 1l, adjust));

        incoming.add(factory.crateTransaction("bar", Command.BUY, Currency.SGP,
                BigDecimal.valueOf(20.0), BigDecimal.valueOf(1), 1l, dontAdjust));

        List<Transaction> result = manager
                .adjustTransactionsForWorkingDay(incoming);
        assertEquals(2, result.size());
        assertEquals(adjusted, result.get(0).getSettlementDate());
        assertEquals(dontAdjust, result.get(1).getSettlementDate());

    }

    @Test
    public void calculateAmount() {
        TransactionDTO dto = new TransactionDTO();
        LocalDate buyDate = LocalDate.of(2017, 6, 25);
        LocalDate sellDate = LocalDate.of(2017, 6, 25);

        List<Transaction> managedTransactions = new ArrayList<Transaction>();
        managedTransactions.add(factory.crateTransaction("sell", Command.SELL,
                Currency.AED, BigDecimal.valueOf(10.0), BigDecimal.valueOf(1),
                1l, sellDate));
        managedTransactions.add(factory.crateTransaction("buy", Command.BUY,
                Currency.SAR, BigDecimal.valueOf(20.0), BigDecimal.valueOf(1),
                1l, buyDate));
        dto.setManagedTransactions(managedTransactions);
        manager.calculateAmount(dto);

        assertEquals(10.0,
                dto.getIncomingEveryDay().get(buyDate).doubleValue(), 0.01);
        assertEquals(20.0, dto.getOutgoingEveryDay().get(sellDate)
                .doubleValue(), 0.01);
    }

    @Test
    public void calculateAmountAddingDays() {
        TransactionDTO dto = new TransactionDTO();
        LocalDate buyDate = LocalDate.of(2017, 6, 25);
        LocalDate sameBuyDate = LocalDate.of(2017, 6, 25);

        LocalDate sellDate = LocalDate.of(2017, 6, 26);
        LocalDate sameSellDate = LocalDate.of(2017, 6, 26);

        List<Transaction> managedTransactions = new ArrayList<Transaction>();
        managedTransactions.add(factory.crateTransaction("sell", Command.SELL,
                Currency.AED, BigDecimal.valueOf(15.0), BigDecimal.valueOf(1),
                1l, sellDate));
        managedTransactions.add(factory.crateTransaction("sell", Command.SELL,
                Currency.AED, BigDecimal.valueOf(15.0), BigDecimal.valueOf(1),
                1l, sameSellDate));

        managedTransactions.add(factory.crateTransaction("buy", Command.BUY,
                Currency.SAR, BigDecimal.valueOf(25.0), BigDecimal.valueOf(1),
                1l, buyDate));

        managedTransactions.add(factory.crateTransaction("buy", Command.BUY,
                Currency.SAR, BigDecimal.valueOf(25.0), BigDecimal.valueOf(1),
                1l, sameBuyDate));
        dto.setManagedTransactions(managedTransactions);
        manager.calculateAmount(dto);

        assertEquals(30.0, dto.getIncomingEveryDay().get(sellDate)
                .doubleValue(), 0.01);
        assertEquals(50.0,
                dto.getOutgoingEveryDay().get(buyDate).doubleValue(), 0.01);
    }

}
