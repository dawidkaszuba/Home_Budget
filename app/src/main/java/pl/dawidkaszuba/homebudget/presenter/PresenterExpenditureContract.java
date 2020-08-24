package pl.dawidkaszuba.homebudget.presenter;

import android.content.Intent;
import android.view.View;

import pl.dawidkaszuba.homebudget.pojo.Tag;

public interface PresenterExpenditureContract {

    void getTags(String kind);
    void addExpenditure(View view, String expenditureAmount, String expenditureNote, Tag tag);
    void backToBalanceActivity(Intent intent);
    void getPlannedCashFlows();
}
