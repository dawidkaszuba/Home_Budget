package pl.dawidkaszuba.homebudget.model;

public class Balance {

    private double amount;

    public Balance(final double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(final double amount) {
        this.amount = amount;
    }
}
