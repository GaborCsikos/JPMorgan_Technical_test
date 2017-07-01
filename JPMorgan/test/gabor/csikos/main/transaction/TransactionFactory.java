/**
 * 
 */
package gabor.csikos.main.transaction;

import gabor.csikos.main.api.Command;
import gabor.csikos.main.api.Currency;
import gabor.csikos.main.domain.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Factory tof creating test date
 * 
 * @author Gabor Csikos
 * 
 */
public class TransactionFactory {

    public Transaction crateTransaction(String entityName, Command command,
            Currency currency, BigDecimal pricePerUnit, BigDecimal agreedFx,
            Long units, LocalDate settlementDate) {
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

    public List<Transaction> generateDataForTest() {
        List<Transaction> result = new ArrayList<Transaction>();
        result.add(crateTransaction("foo", Command.BUY, Currency.SGP,
                BigDecimal.valueOf(10.0), BigDecimal.valueOf(2.0),
                Long.valueOf(2l), LocalDate.of(2017, 6, 30)));

        result.add(crateTransaction("bar", Command.BUY, Currency.SGP,
                BigDecimal.valueOf(10.0), BigDecimal.valueOf(2.0),
                Long.valueOf(2l), LocalDate.of(2017, 6, 30)));

        result.add(crateTransaction("foo", Command.BUY, Currency.SGP,
                BigDecimal.valueOf(30.0), BigDecimal.valueOf(2.0),
                Long.valueOf(2l), LocalDate.of(2017, 7, 3)));

        result.add(crateTransaction("foo", Command.SELL, Currency.SGP,
                BigDecimal.valueOf(10.0), BigDecimal.valueOf(2.0),
                Long.valueOf(2l), LocalDate.of(2017, 6, 30)));

        result.add(crateTransaction("bar", Command.SELL, Currency.SGP,
                BigDecimal.valueOf(10.0), BigDecimal.valueOf(2.0),
                Long.valueOf(2l), LocalDate.of(2017, 6, 30)));

        result.add(crateTransaction("foo", Command.SELL, Currency.SGP,
                BigDecimal.valueOf(30.0), BigDecimal.valueOf(2.0),
                Long.valueOf(2l), LocalDate.of(2017, 7, 3)));
        return result;
    }
}
