/**
 * 
 */
package gabor.csikos.main.transaction;

import gabor.csikos.main.domain.Transaction;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Gabor Csikos
 * 
 */
public class RiportGenerator {

    private static final String HEADER_INCOMING = "Highest Incoming to Lowest";
    private static final String HEADER_OUTGOING = "Highest Outgoing to Lowest";

    public void printAmuntSettled(String header, Map<Date, BigDecimal> incoming) {
        System.out.println(header);
        for (Map.Entry<Date, BigDecimal> entry : incoming.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

    }

    public void printRankIncoming(List<Transaction> transactions) {
        printRank(transactions, HEADER_INCOMING);
    }

    public void printRankOutGoing(List<Transaction> transactions) {
        printRank(transactions, HEADER_OUTGOING);
    }

    private void printRank(List<Transaction> transactions, String header) {
        System.out.println(header);
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

}
