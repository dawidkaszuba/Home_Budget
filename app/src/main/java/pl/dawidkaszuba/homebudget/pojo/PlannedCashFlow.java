package pl.dawidkaszuba.homebudget.pojo;

import java.math.BigDecimal;

public class PlannedCashFlow {


    private Long id;
    private String name;
    private String note;
    private BigDecimal currentSumAmount;
    private BigDecimal plannedAmount;
    private boolean isSumAmountExceeded;

    @Override
    public String toString() {
        return name;
    }

}
