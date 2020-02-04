package pl.dawidkaszuba.homebudget.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Expenditure {


    private Long id;
    private BigDecimal amount;
    private LocalDate expenditureDate;
    private List<Tag> tags;
    private PlannedCashFlow plannedCashFlow;
    private String note;
    private User user;

    public Expenditure(final Long id,
                       final BigDecimal amount,
                       final LocalDate expenditureDate,
                       final List<Tag> tags,
                       final PlannedCashFlow plannedCashFlow,
                       final String note,
                       final User user) {
        this.id = id;
        this.amount = amount;
        this.expenditureDate = expenditureDate;
        this.tags = tags;
        this.plannedCashFlow = plannedCashFlow;
        this.note = note;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getExpenditureDate() {
        return expenditureDate;
    }

    public void setExpenditureDate(final LocalDate expenditureDate) {
        this.expenditureDate = expenditureDate;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(final List<Tag> tags) {
        this.tags = tags;
    }

    public PlannedCashFlow getPlannedCashFlow() {
        return plannedCashFlow;
    }

    public void setPlannedCashFlow(final PlannedCashFlow plannedCashFlow) {
        this.plannedCashFlow = plannedCashFlow;
    }

    public String getNote() {
        return note;
    }

    public void setNote(final String note) {
        this.note = note;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }
}
