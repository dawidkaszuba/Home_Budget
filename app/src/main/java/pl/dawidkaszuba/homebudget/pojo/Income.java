package pl.dawidkaszuba.homebudget.pojo;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Income {

    private Long id;
    private BigDecimal amount;
    private LocalDate incomeDate;
    private Tag tag;
    private String note;
    private User user;

    public Income(BigDecimal amount, Tag tag, String note, User user) {
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

    public LocalDate getIncomeDate() {
        return incomeDate;
    }

    public void setIncomeDate(LocalDate incomeDate) {
        this.incomeDate = incomeDate;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
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
