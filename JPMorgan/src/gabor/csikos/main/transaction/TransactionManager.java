/**
 * 
 */
package gabor.csikos.main.transaction;

import gabor.csikos.main.api.Command;
import gabor.csikos.main.api.TransactionDTO;
import gabor.csikos.main.domain.Transaction;
import gabor.csikos.main.util.DateUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Gabor Csikos
 * 
 */
public class TransactionManager {

    public List<Transaction> adjustTransactionsForWorkingDay(
            List<Transaction> incoming) {
        List<Transaction> result = new ArrayList<Transaction>();
        if (incoming != null) {
            for (Transaction transaction : incoming) {
                if (isOnWorkingDay(transaction)) {
                    result.add(transaction);
                } else {
                    result.add(addJustTransaction(transaction));
                }

            }
        }
        return result;
    }

    private Transaction addJustTransaction(Transaction transaction) {
        Transaction copy = new Transaction(transaction);
        copy.setSettlementDate(DateUtil.shifted(
                transaction.getSettlementDate(), transaction.getCurrency()));
        return copy;
    }

    private boolean isOnWorkingDay(Transaction transaction) {
        if (transaction.getCurrency() != null
                && transaction.getSettlementDate() != null) {
            return !DateUtil.isWeekend(transaction.getSettlementDate(),
                    transaction.getCurrency());
        }

        return true;
    }

    public List<Transaction> rankEntityByIncomingAmount(
            List<Transaction> incoming) {
        return rankEntity(Command.BUY, incoming);
    }

    public List<Transaction> rankEntityByOutGoingAmount(
            List<Transaction> incoming) {
        return rankEntity(Command.SELL, incoming);
    }

    public void calculateAmount(TransactionDTO dto) {
        Map<Date, BigDecimal> incomingEveryDay = new HashMap<Date, BigDecimal>();
        Map<Date, BigDecimal> outgoingEveryDay = new HashMap<Date, BigDecimal>();
        for (Transaction transaction : dto.getManagedTransactions()) {
            if (Command.BUY.equals(transaction.getCommand())) {
                incomingEveryDay.put(transaction.getSettlementDate(),
                        transaction.getUSDAmount());
            } else if (Command.SELL.equals(transaction.getCommand())) {
                outgoingEveryDay.put(transaction.getSettlementDate(),
                        transaction.getUSDAmount());
            }
        }

        dto.setIncomingEveryDay(incomingEveryDay);
        dto.setOutgoingEveryDay(outgoingEveryDay);
    }

    private List<Transaction> rankEntity(Command command,
            List<Transaction> incoming) {
        List<Transaction> sorted = new ArrayList<Transaction>();
        for (Transaction transaction : incoming) {
            if (command.equals(transaction.getCommand())) {
                sorted.add(transaction);
            }
        }
        Collections.sort(sorted);
        Collections.reverse(sorted);
        return sorted;
    }

}
