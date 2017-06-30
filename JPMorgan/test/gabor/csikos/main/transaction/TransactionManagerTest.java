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
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

public class TransactionManagerTest {

    private TransactionManager manager = new TransactionManager();

    @Test
    public void rankEntityByIncomingAmount() {
        Date fooDate = Date.from(LocalDateTime.of(2017, 6, 25, 3, 0)
                .atZone(ZoneId.systemDefault()).toInstant());

        Date barDate = Date.from(LocalDateTime.of(2017, 6, 25, 2, 0)
                .atZone(ZoneId.systemDefault()).toInstant());

        Date wontUse = Date.from(LocalDateTime.of(2017, 6, 25, 1, 0)
                .atZone(ZoneId.systemDefault()).toInstant());

        List<Transaction> incoming = new ArrayList<Transaction>();
        incoming.add(crateTransaction("foo", Command.BUY, Currency.AED,
                BigDecimal.valueOf(10.0), BigDecimal.valueOf(1), 1l, fooDate));

        incoming.add(crateTransaction("bar", Command.BUY, Currency.SAR,
                BigDecimal.valueOf(20.0), BigDecimal.valueOf(1), 1l, barDate));

        incoming.add(crateTransaction("notCounting", Command.SELL,
                Currency.SAR, BigDecimal.valueOf(20.0), BigDecimal.valueOf(1),
                1l, wontUse));

        List<Transaction> result = manager.rankEntityByIncomingAmount(incoming);
        assertEquals(2, result.size());
        assertEquals("bar", result.get(0).getEntityName());
        assertEquals("foo", result.get(1).getEntityName());
    }

    @Test
    public void rankEntityByOutGoingAmount() {
        Date fooDate = Date.from(LocalDateTime.of(2017, 6, 25, 3, 0)
                .atZone(ZoneId.systemDefault()).toInstant());

        Date barDate = Date.from(LocalDateTime.of(2017, 6, 25, 2, 0)
                .atZone(ZoneId.systemDefault()).toInstant());

        Date wontUse = Date.from(LocalDateTime.of(2017, 6, 25, 1, 0)
                .atZone(ZoneId.systemDefault()).toInstant());

        List<Transaction> incoming = new ArrayList<Transaction>();
        incoming.add(crateTransaction("foo", Command.SELL, Currency.AED,
                BigDecimal.valueOf(10.0), BigDecimal.valueOf(1), 1l, fooDate));

        incoming.add(crateTransaction("bar", Command.SELL, Currency.SAR,
                BigDecimal.valueOf(20.0), BigDecimal.valueOf(1), 1l, barDate));

        incoming.add(crateTransaction("notCounting", Command.BUY, Currency.SAR,
                BigDecimal.valueOf(20.0), BigDecimal.valueOf(1), 1l, wontUse));

        List<Transaction> result = manager.rankEntityByOutGoingAmount(incoming);
        assertEquals(2, result.size());
        assertEquals("bar", result.get(0).getEntityName());
        assertEquals("foo", result.get(1).getEntityName());
    }

    @Test
    public void adjustTransactionsForWorkingDay() {
        List<Transaction> incoming = new ArrayList<Transaction>();
        Date adjust = Date.from(LocalDateTime.of(2017, 6, 18, 2, 0)
                .atZone(ZoneId.systemDefault()).toInstant());

        Date adjusted = Date.from(LocalDateTime.of(2017, 6, 19, 2, 0)
                .atZone(ZoneId.systemDefault()).toInstant());

        Date dontAdjust = Date.from(LocalDateTime.of(2017, 6, 19, 3, 0)
                .atZone(ZoneId.systemDefault()).toInstant());

        incoming.add(crateTransaction("foo", Command.SELL, Currency.SGP,
                BigDecimal.valueOf(10.0), BigDecimal.valueOf(1), 1l, adjust));

        incoming.add(crateTransaction("bar", Command.SELL, Currency.SGP,
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
        Date buyDate = Date.from(LocalDateTime.of(2017, 6, 25, 2, 0)
                .atZone(ZoneId.systemDefault()).toInstant());

        Date sellDate = Date.from(LocalDateTime.of(2017, 6, 25, 3, 0)
                .atZone(ZoneId.systemDefault()).toInstant());

        List<Transaction> managedTransactions = new ArrayList<Transaction>();
        managedTransactions.add(crateTransaction("buy", Command.BUY,
                Currency.AED, BigDecimal.valueOf(10.0), BigDecimal.valueOf(1),
                1l, buyDate));
        managedTransactions.add(crateTransaction("sell", Command.SELL,
                Currency.SAR, BigDecimal.valueOf(20.0), BigDecimal.valueOf(1),
                1l, sellDate));
        dto.setManagedTransactions(managedTransactions);
        manager.calculateAmount(dto);

        assertEquals(10.0,
                dto.getIncomingEveryDay().get(buyDate).doubleValue(), 0.01);
        assertEquals(20.0, dto.getOutgoingEveryDay().get(sellDate)
                .doubleValue(), 0.01);
    }

    private Transaction crateTransaction(String entityName, Command command,
            Currency currency, BigDecimal pricePerUnit, BigDecimal agreedFx,
            Long units, Date settlementDate) {
        Transaction transaction = new Transaction();
        transaction.setEntityName(entityName);
        transaction.setCommand(command);
        transaction.setCurrency(currency);
        transaction.setPricePerUnit(pricePerUnit);
        transaction.setAgreedFx(agreedFx);
        transaction.setUnits(units);
        transaction.setSettlementDate(settlementDate);
        return transaction;
    }

}
