/**
 * 
 */
package gabor.csikos.main.domain;

import gabor.csikos.main.api.Command;
import gabor.csikos.main.api.Currency;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Gabor Csikos
 * 
 */
public class Transaction implements Comparable<Transaction> {

    /**
     * Unique name of entity;
     */
    private String entityName;

    private Command command;
    private BigDecimal agreedFx;
    private Currency currency;
    private Date instructionDate;
    private Date settlementDate;
    private Long units;
    private BigDecimal pricePerUnit;

    public Transaction() {

    }

    public Transaction(Transaction transaction) {
        this.entityName = transaction.entityName;
        this.command = transaction.command;
        this.agreedFx = transaction.agreedFx;
        this.currency = transaction.currency;
        this.instructionDate = transaction.instructionDate;
        this.settlementDate = transaction.settlementDate;
        this.units = transaction.units;
        this.pricePerUnit = transaction.pricePerUnit;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public BigDecimal getAgreedFx() {
        return agreedFx;
    }

    public void setAgreedFx(BigDecimal agreedFx) {
        this.agreedFx = agreedFx;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Date getInstructionDate() {
        return instructionDate;
    }

    public void setInstructionDate(Date instructionDate) {
        this.instructionDate = instructionDate;
    }

    public Date getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(Date settlementDate) {
        this.settlementDate = settlementDate;
    }

    public Long getUnits() {
        return units;
    }

    public void setUnits(Long units) {
        this.units = units;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    @Override
    public int compareTo(Transaction o) {
        return this.getUSDAmount().compareTo(o.getUSDAmount());
    }

    @Override
    public String toString() {
        return "Name:" + entityName + ": " + this.getUSDAmount();
    }

    public BigDecimal getUSDAmount() {
        BigDecimal result = new BigDecimal(units);
        return result.multiply(pricePerUnit).multiply(agreedFx);
    }

}
