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
import pl.dawidkaszuba.homebudget.model.ModelTagContract;
import pl.dawidkaszuba.homebudget.model.ModelTagContractImpl;
import pl.dawidkaszuba.homebudget.pojo.Income;
import pl.dawidkaszuba.homebudget.pojo.Tag;
import pl.dawidkaszuba.homebudget.pojo.Token;
import pl.dawidkaszuba.homebudget.pojo.User;
import pl.dawidkaszuba.homebudget.service.BackendServerService;
import pl.dawidkaszuba.homebudget.shearedPreferences.MyPreferences;
import pl.dawidkaszuba.homebudget.view.BalanceActivity;
import pl.dawidkaszuba.homebudget.view.ViewIncomeContract;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PresenterIncomeContractImpl implements PresenterIncomeContract {

    public static final String TAG = "Tags_tag";

    ViewIncomeContract view;
    ModelTagContract model;

    public PresenterIncomeContractImpl(Context context) {
        view = (ViewIncomeContract) context;
        model = new ModelTagContractImpl(context);
    }

    @Override
    public void addIncome(View view, String incomeAmount, String incomeNote, Tag tag) {

        Retrofit retrofit = RetrofitClient.getClient(ApiUtils.BASE_URL);

        BackendServerService mBackendServerService = retrofit.create(BackendServerService.class);

        Intent intent = new Intent(view.getContext(), BalanceActivity.class);


        MyPreferences myPreferences = MyPreferences.getInstance(view.getContext());
        Long userId = Long.parseLong(myPreferences.getPreference("USER_ID"));
        String userName = myPreferences.getPreference("USER_NAME");
        Token token = new Token(myPreferences.getPreference("TOKEN"));

        User user = new User(userId,userName);
        Income income = new Income(new BigDecimal(incomeAmount),tag,incomeNote,user);


        Call<ResponseBody> call = mBackendServerService.addIncome(token.getToken(),income,userId);
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

}
