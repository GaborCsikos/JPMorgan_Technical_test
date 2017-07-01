package gabor.csikos.main.transaction;

import gabor.csikos.main.api.TransactionService;
import gabor.csikos.main.domain.Transaction;

import java.util.List;

public class TestTransactionService implements TransactionService {

    private TransactionFactory factory = new TransactionFactory();

    @Override
    public List<Transaction> getTransactions() {
        return factory.generateDataForTest();
    }
}
