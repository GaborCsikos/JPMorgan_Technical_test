package gabor.csikos.main.transaction;

import gabor.csikos.main.ApplicationRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RiportGeneratorEnd2EndTest {

    private ApplicationRunner application;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        // Usually would use a Mock object here
        application = new ApplicationRunner(new TestTransactionService());
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    public void testHappyPath() {

    }

}
