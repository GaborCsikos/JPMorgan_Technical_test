/**
 * 
 */
package gabor.csikos.main.api;

import gabor.csikos.main.domain.Transaction;

import java.util.List;

/**
 * The interface where the transactions come in
 * 
 * @author Gabor Csikos
 * 
 */
public interface TransactionService {

    List<Transaction> getTransactions();

}
