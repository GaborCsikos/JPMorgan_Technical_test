/**
 * 
 */
package gabor.csikos.main.transaction;

import gabor.csikos.main.api.Command;
import gabor.csikos.main.api.TransactionDTO;
import gabor.csikos.main.domain.Transaction;
import gabor.csikos.main.util.DateUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Class to handle transanction operations
 * 
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

    public List<Transaction> rankEntityByIncomingAmount(
            List<Transaction> incoming) {
        return rankEntity(Command.BUY, incoming);
    }

    public List<Transaction> rankEntityByOutGoingAmount(
            List<Transaction> incoming) {
        return rankEntity(Command.SELL, incoming);
    }

    public void calculateAmount(TransactionDTO dto) {
        Map<LocalDate, BigDecimal> incomingEveryDay = new TreeMap<LocalDate, BigDecimal>();
        Map<LocalDate, BigDecimal> outgoingEveryDay = new TreeMap<LocalDate, BigDecimal>();
        for (Transaction transaction : dto.getManagedTransactions()) {
            if (Command.BUY.equals(transaction.getCommand())) {
                if (incomingEveryDay.get(transaction.getSettlementDate()) == null) {
                    incomingEveryDay.put(transaction.getSettlementDate(),
                            transaction.getUSDAmount());
                } else {
                    BigDecimal oldValue = incomingEveryDay.get(transaction
                            .getSettlementDate());
                    incomingEveryDay.put(transaction.getSettlementDate(),
                            oldValue.add(transaction.getUSDAmount()));
                }

            } else if (Command.SELL.equals(transaction.getCommand())) {
                if (outgoingEveryDay.get(transaction.getSettlementDate()) == null) {
                    outgoingEveryDay.put(transaction.getSettlementDate(),
                            transaction.getUSDAmount());
                } else {
                    BigDecimal oldValue = outgoingEveryDay.get(transaction
                            .getSettlementDate());
                    outgoingEveryDay.put(transaction.getSettlementDate(),
                            oldValue.add(transaction.getUSDAmount()));
                }
            }
        }

        dto.setIncomingEveryDay(incomingEveryDay);
        dto.setOutgoingEveryDay(outgoingEveryDay);
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

    /*
     * The rankink follows the logic: We add add all elements by command type
     * what we want to sort.
     * 
     * Then we sort the elements, and add them to a Set, the result will be a
     * unique List with the highest elements.
     * 
     * We sort the result.
     */
    private List<Transaction> rankEntity(Command command,
            List<Transaction> incoming) {
        List<Transaction> sorted = new ArrayList<Transaction>();
        for (Transaction transaction : incoming) {
            if (command.equals(transaction.getCommand())) {
                sorted.add(transaction);
            }
        }
        Collections.sort(sorted);
        Set<Transaction> uniqueElements = new HashSet<Transaction>(sorted);
        List<Transaction> uniqueElementsSorted = new ArrayList<Transaction>(
                uniqueElements);
        Collections.sort(uniqueElementsSorted);
        return uniqueElementsSorted;
    }

}
