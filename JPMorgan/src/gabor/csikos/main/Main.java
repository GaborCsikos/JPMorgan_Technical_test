/**
 * 
 */
package gabor.csikos.main;

import gabor.csikos.main.api.TransactionService;

/**
 * The Main class, where the application starts.
 * 
 * @author Gabor Csikos
 * 
 */
public class Main {

    public static void main(String[] args) {
        TransactionService simpleService = new DummyTransactionService();
        ApplicationRunner runner = new ApplicationRunner(simpleService);
        runner.execute();
        runner.generateReport();

    }

}
