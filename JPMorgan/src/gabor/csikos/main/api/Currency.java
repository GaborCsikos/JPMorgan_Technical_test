/**
 * 
 */
package gabor.csikos.main.api;

/**
 * @author Gabor Csikos
 * 
 */
public enum Currency {
    SGP(true), AED(false), SAR(false);

    private boolean startsOnMondayEndOnFriday;

    Currency(boolean startsOnMondayEndOnFriday) {
        this.startsOnMondayEndOnFriday = startsOnMondayEndOnFriday;
    }

    public boolean startsOnMondayEndOnFriday() {
        return startsOnMondayEndOnFriday;
    }

}
