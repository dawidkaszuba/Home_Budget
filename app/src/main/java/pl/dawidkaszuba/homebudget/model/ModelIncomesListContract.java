package pl.dawidkaszuba.homebudget.model;

import java.util.List;

import io.reactivex.Single;
import pl.dawidkaszuba.homebudget.pojo.Income;

public interface ModelIncomesListContract {
    Single<List<Income>> getIncomeListFromHttp();
}
