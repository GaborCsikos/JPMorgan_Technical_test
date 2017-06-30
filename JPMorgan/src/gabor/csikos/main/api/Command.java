/**
 * 
 */
package gabor.csikos.main.api;

/**
 * @author Gabor Csikos
 * 
 */
public enum Command {
    BUY("B"), SELL("S");

    private String command;

    Command(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

}
