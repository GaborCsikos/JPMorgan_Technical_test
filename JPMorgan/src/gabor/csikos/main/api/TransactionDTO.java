/**
 * 
 */
package gabor.csikos.main.api;

import gabor.csikos.main.domain.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Gabor Csikos
 * 
 */
public class TransactionDTO {

    private Map<LocalDate, BigDecimal> incomingEveryDay = new TreeMap<LocalDate, BigDecimal>();
    private Map<LocalDate, BigDecimal> outgoingEveryDay = new TreeMap<LocalDate, BigDecimal>();
    private List<Transaction> managedTransactions = new ArrayList<Transaction>();

    public Map<LocalDate, BigDecimal> getIncomingEveryDay() {
        return incomingEveryDay;
    }

    public void setIncomingEveryDay(Map<LocalDate, BigDecimal> incomingEveryDay) {
        this.incomingEveryDay = incomingEveryDay;
    }

    public Map<LocalDate, BigDecimal> getOutgoingEveryDay() {
        return outgoingEveryDay;
    }

    public void setOutgoingEveryDay(Map<LocalDate, BigDecimal> outgoingEveryDay) {
        this.outgoingEveryDay = outgoingEveryDay;
    }

    public List<Transaction> getManagedTransactions() {
        return managedTransactions;
    }

    public void setManagedTransactions(List<Transaction> managedTransactions) {
        this.managedTransactions = managedTransactions;
    }

}
