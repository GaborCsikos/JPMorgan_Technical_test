/**
 * 
 */
package gabor.csikos.main;

import gabor.csikos.main.api.TransactionService;
import gabor.csikos.main.domain.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy class for transactions
 * 
 * @author Gabor Csikos
 * 
 */
public class DummyTransactionService implements TransactionService {

    /*
     * (non-Javadoc)
     * 
     * @see gabor.csikos.main.api.TransactionService#getTransactions()
     */
    @Override
    public List<Transaction> getTransactions() {
        return new ArrayList<Transaction>();
    }

}
