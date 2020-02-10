package pl.dawidkaszuba.homebudget.pojo;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Expenditure {


    private Long id;
    private BigDecimal amount;
    private LocalDate expenditureDate;
    private Tag tag;
    private PlannedCashFlow plannedCashFlow;
    private String note;
    private User user;

    public Expenditure(Long id, BigDecimal amount, LocalDate expenditureDate, Tag tag, PlannedCashFlow plannedCashFlow, String note, User user) {
        this.id = id;
        this.amount = amount;
        this.expenditureDate = expenditureDate;
        this.tag = tag;
        this.plannedCashFlow = plannedCashFlow;
        this.note = note;
        this.user = user;
    }


    public Expenditure(BigDecimal amount, Tag tag, String note, User user) {
        this.amount = amount;
        this.tag = tag;
        this.note = note;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getExpenditureDate() {
        return expenditureDate;
    }

    public void setExpenditureDate(LocalDate expenditureDate) {
        this.expenditureDate = expenditureDate;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public PlannedCashFlow getPlannedCashFlow() {
        return plannedCashFlow;
    }

    public void setPlannedCashFlow(PlannedCashFlow plannedCashFlow) {
        this.plannedCashFlow = plannedCashFlow;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
