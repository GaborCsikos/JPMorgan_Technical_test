/**
 * 
 */
package gabor.csikos.main.domain;

import gabor.csikos.main.api.Command;
import gabor.csikos.main.api.Currency;

import java.math.BigDecimal;
import java.time.LocalDate;

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
    private LocalDate instructionDate;
    private LocalDate settlementDate;
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

    public LocalDate getInstructionDate() {
        return instructionDate;
    }

    public void setInstructionDate(LocalDate instructionDate) {
        this.instructionDate = instructionDate;
    }

    public LocalDate getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(LocalDate settlementDate) {
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
        return o.getUSDAmount().compareTo(this.getUSDAmount());
    }

    @Override
    public String toString() {
        return "Name: " + entityName + " | " + this.getUSDAmount();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((entityName == null) ? 0 : entityName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Transaction other = (Transaction) obj;
        if (entityName == null) {
            if (other.entityName != null)
                return false;
        } else if (!entityName.equals(other.entityName))
            return false;
        return true;
    }

    public BigDecimal getUSDAmount() {
        BigDecimal result = new BigDecimal(units);
        return result.multiply(pricePerUnit).multiply(agreedFx);
    }

}
