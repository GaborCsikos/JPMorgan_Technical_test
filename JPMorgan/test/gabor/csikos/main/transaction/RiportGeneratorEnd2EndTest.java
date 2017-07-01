package gabor.csikos.main.transaction;

import static org.junit.Assert.assertEquals;
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
    public void sammpleTransaction() {
        application.execute();
        application.generateReport();
        assertEquals(expectedOutput(), outContent.toString());
    }

    private String expectedOutput() {
        StringBuilder result = new StringBuilder();
        result.append("Incoming").append(System.lineSeparator());
        result.append("2017-06-30 : 160.00").append(System.lineSeparator());
        result.append("2017-07-03 : 200.00").append(System.lineSeparator())
                .append(System.lineSeparator());

        result.append("Outgoing").append(System.lineSeparator());
        result.append("2017-06-30 : 80.00").append(System.lineSeparator());
        result.append("2017-07-03 : 120.00").append(System.lineSeparator())
                .append(System.lineSeparator());

        result.append("Highest Incoming to Lowest").append(
                System.lineSeparator());
        result.append("Name: foo | 200.00").append(System.lineSeparator());
        result.append("Name: bar | 80.00").append(System.lineSeparator())
                .append(System.lineSeparator());

        result.append("Highest Outgoing to Lowest").append(
                System.lineSeparator());
        result.append("Name: foo | 120.00").append(System.lineSeparator());
        result.append("Name: bar | 40.00").append(System.lineSeparator())
                .append(System.lineSeparator());
        return result.toString();
    }

}
