package pl.dawidkaszuba.homebudget.pojo;

public class Balance {

    private double value;

    public Balance(final double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(final double value) {
        this.value = value;
    }
}

