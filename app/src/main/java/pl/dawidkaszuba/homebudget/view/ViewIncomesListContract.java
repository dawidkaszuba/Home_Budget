package pl.dawidkaszuba.homebudget.view;

import java.util.List;

import pl.dawidkaszuba.homebudget.pojo.Income;

public interface ViewIncomesListContract {

    void setIncomeList(List<Income> incomes);
}
