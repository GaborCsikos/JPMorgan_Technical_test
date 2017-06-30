/**
 * 
 */
package gabor.csikos.main;

import gabor.csikos.main.api.TransactionDTO;
import gabor.csikos.main.api.TransactionService;
import gabor.csikos.main.domain.Transaction;
import gabor.csikos.main.transaction.RiportGenerator;
import gabor.csikos.main.transaction.TransactionManager;

import java.util.List;

/**
 * @author Gabor Csikos
 * 
 */
public class ApplicationRunner {

    private TransactionDTO dto = new TransactionDTO();

    private RiportGenerator riportGenerator = new RiportGenerator();
    private TransactionService transactionService;
    private TransactionManager manager = new TransactionManager();

    public ApplicationRunner(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public void execute() {
        List<Transaction> allTransactions = transactionService
                .getTransactions();
        dto.setManagedTransactions(manager
                .adjustTransactionsForWorkingDay(allTransactions));
        manager.calculateAmount(dto);
    }

    public void generateReport() {
        riportGenerator
                .printAmuntSettled("Incoming", dto.getIncomingEveryDay());
        riportGenerator
                .printAmuntSettled("OutGoing", dto.getOutgoingEveryDay());
        riportGenerator.printRankIncoming(manager
                .rankEntityByIncomingAmount(dto.getManagedTransactions()));
        riportGenerator.printRankOutGoing(manager
                .rankEntityByOutGoingAmount(dto.getManagedTransactions()));
    }

}
