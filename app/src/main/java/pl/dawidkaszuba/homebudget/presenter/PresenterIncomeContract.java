package pl.dawidkaszuba.homebudget.presenter;

import android.view.View;

import pl.dawidkaszuba.homebudget.pojo.Tag;

public interface PresenterIncomeContract {

    void addIncome(View view, String incomeAmount, String incomeNote, Tag tag);
    void getTags(String kind);
}
