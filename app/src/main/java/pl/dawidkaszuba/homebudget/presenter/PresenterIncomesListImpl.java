package pl.dawidkaszuba.homebudget.presenter;

import android.util.Log;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import pl.dawidkaszuba.homebudget.model.ModelIncomesListContract;
import pl.dawidkaszuba.homebudget.pojo.Income;
import pl.dawidkaszuba.homebudget.view.ViewIncomesListContract;

public class PresenterIncomesListImpl implements PresenterIncomesListContract {

    ViewIncomesListContract view;
    ModelIncomesListContract model;
    public static final String INCOMES_LIST = "Income_list_income_list";


    public PresenterIncomesListImpl(final ViewIncomesListContract view, final ModelIncomesListContract model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void getIncomeList() {

        model.getIncomeListFromHttp()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.computation())
                .subscribe(new SingleObserver<List<Income>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(INCOMES_LIST,"on subscribe: ");

                    }

                    @Override
                    public void onSuccess(List<Income> incomes) {
                        Log.d(INCOMES_LIST,"on Success: " + incomes.size());

                        view.setIncomeList(incomes);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(INCOMES_LIST,"on Error: " + e.getMessage());
                        view.errorMessage("error: " + e.getMessage());
                    }
                });

    }
}
