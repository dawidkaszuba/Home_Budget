package pl.dawidkaszuba.homebudget.presenter;

import android.content.Context;
import android.util.Log;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import pl.dawidkaszuba.homebudget.model.ModelTagContract;
import pl.dawidkaszuba.homebudget.pojo.Tag;
import pl.dawidkaszuba.homebudget.model.ModelTagContractImpl;
import pl.dawidkaszuba.homebudget.view.ViewTagContract;

public class PresenterTagContractImpl implements PresenterTagContract {


    public static final String TAG = "Tags_tag";


    ViewTagContract view;

    ModelTagContract model;


    public PresenterTagContractImpl(Context context) {
        view = (ViewTagContract) context;
        model = new ModelTagContractImpl(context);
    }

    @Override
    public void getTags() {

        model.getTagsFromHttp()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.computation())
                .subscribe(new SingleObserver<List<Tag>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG,"on subscribe: ");

                    }

                    @Override
                    public void onSuccess(List<Tag> tags) {
                        Log.d(TAG,"on Success: " + tags.size());

                        view.fillTagsSpinner(tags);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG,"on Error: " + e.getMessage());
                        view.errorMessage("error: " + e.getMessage());
                    }
                });

    }
}
