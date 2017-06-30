/**
 * 
 */
package gabor.csikos.main.api;

import gabor.csikos.main.domain.Transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Gabor Csikos
 * 
 */
public class TransactionDTO {

    private Map<Date, BigDecimal> incomingEveryDay = new TreeMap<Date, BigDecimal>();
    private Map<Date, BigDecimal> outgoingEveryDay = new TreeMap<Date, BigDecimal>();
    private List<Transaction> managedTransactions = new ArrayList<Transaction>();

    public Map<Date, BigDecimal> getIncomingEveryDay() {
        return incomingEveryDay;
    }

    public void setIncomingEveryDay(Map<Date, BigDecimal> incomingEveryDay) {
        this.incomingEveryDay = incomingEveryDay;
    }

    public Map<Date, BigDecimal> getOutgoingEveryDay() {
        return outgoingEveryDay;
    }

    public void setOutgoingEveryDay(Map<Date, BigDecimal> outgoingEveryDay) {
        this.outgoingEveryDay = outgoingEveryDay;
    }

    public List<Transaction> getManagedTransactions() {
        return managedTransactions;
    }

    public void setManagedTransactions(List<Transaction> managedTransactions) {
        this.managedTransactions = managedTransactions;
    }

}
