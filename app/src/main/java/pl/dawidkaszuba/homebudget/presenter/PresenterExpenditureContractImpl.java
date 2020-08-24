package pl.dawidkaszuba.homebudget.presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import pl.dawidkaszuba.homebudget.ApiUtils;
import pl.dawidkaszuba.homebudget.RetrofitClient;
import pl.dawidkaszuba.homebudget.model.ModelPLannedCashFlowContractImpl;
import pl.dawidkaszuba.homebudget.model.ModelPlannedCashFlowContact;
import pl.dawidkaszuba.homebudget.model.ModelTagContract;
import pl.dawidkaszuba.homebudget.pojo.Expenditure;
import pl.dawidkaszuba.homebudget.pojo.PlannedCashFlow;
import pl.dawidkaszuba.homebudget.pojo.Tag;
import pl.dawidkaszuba.homebudget.model.ModelTagContractImpl;
import pl.dawidkaszuba.homebudget.pojo.Token;
import pl.dawidkaszuba.homebudget.pojo.User;
import pl.dawidkaszuba.homebudget.service.BackendServerService;
import pl.dawidkaszuba.homebudget.shearedPreferences.MyPreferences;
import pl.dawidkaszuba.homebudget.view.BalanceActivity;
import pl.dawidkaszuba.homebudget.view.ViewExpenditureContract;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PresenterExpenditureContractImpl implements PresenterExpenditureContract {


    public static final String TAG = "Tags_tag";


    ViewExpenditureContract view;
    ModelTagContract model;
    ModelPlannedCashFlowContact pcf_model;



    public PresenterExpenditureContractImpl(Context context) {
        view = (ViewExpenditureContract) context;
        model = new ModelTagContractImpl(context);
        pcf_model = new ModelPLannedCashFlowContractImpl(context);
    }

    @Override
    public void getTags(String kind) {

        model.getTagsFromHttp(kind)
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

    @Override
    public void getPlannedCashFlows() {

        pcf_model.getPlannedCashFlowFromHttp()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.computation())
                .subscribe(new SingleObserver<List<PlannedCashFlow>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG,"on subscribe: ");

                    }

                    @Override
                    public void onSuccess(List<PlannedCashFlow> plannedCashFlows) {
                        Log.d(TAG,"on Success: " + plannedCashFlows.size());

                        view.fillPlannedCashFlowSpinner(plannedCashFlows);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG,"on Error: " + e.getMessage());
                        view.errorMessage("error: " + e.getMessage());
                    }
                });

    }

    public void addExpenditure(View view, String expenditureAmount, String expenditureNote, Tag tag,
                               PlannedCashFlow pcf){


        Retrofit retrofit = RetrofitClient.getClient(ApiUtils.BASE_URL);

        BackendServerService mBackendServerService = retrofit.create(BackendServerService.class);

        Intent intent = new Intent(view.getContext(), BalanceActivity.class);


        MyPreferences myPreferences = MyPreferences.getInstance(view.getContext());
        Long userId = Long.parseLong(myPreferences.getPreference("USER_ID"));
        String userName = myPreferences.getPreference("USER_NAME");
        Token token = new Token(myPreferences.getPreference("TOKEN"));

        User user = new User(userId,userName);
        Expenditure expenditure = new Expenditure(new BigDecimal(expenditureAmount),tag,expenditureNote,user,pcf);


        Call<ResponseBody> call = mBackendServerService.addExpenditure(token.getToken(),expenditure,userId);
        call.enqueue(new Callback<ResponseBody>() {


            @Override
            public void onResponse(final Call<ResponseBody> call, final Response<ResponseBody> response) {
                if (response.isSuccessful()) {

                    backToBalanceActivity(intent);

                }else{

                    Toast.makeText(view.getContext(),response.errorBody().toString(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(final Call<ResponseBody> call, final Throwable t) {
                Toast.makeText(view.getContext(),"Error!",Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void backToBalanceActivity(Intent intent) {
        view.navigateToHomeScreen(intent);
    }

}
