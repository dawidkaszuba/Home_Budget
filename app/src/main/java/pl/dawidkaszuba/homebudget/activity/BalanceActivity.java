package pl.dawidkaszuba.homebudget.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;

import pl.dawidkaszuba.homebudget.ApiUtils;
import pl.dawidkaszuba.homebudget.R;
import pl.dawidkaszuba.homebudget.RetrofitClient;
import pl.dawidkaszuba.homebudget.model.Balance;
import pl.dawidkaszuba.homebudget.model.Token;
import pl.dawidkaszuba.homebudget.model.User;
import pl.dawidkaszuba.homebudget.service.BackendServerService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BalanceActivity extends AppCompatActivity {

    Retrofit retrofit = RetrofitClient.getClient(ApiUtils.BASE_URL);


    BackendServerService mBackendServerService = retrofit.create(BackendServerService.class);


    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);
        getBalance();


    }
    private void getBalance() {

        Long userId = 6l;
        LocalDate from = null;
        LocalDate to = null;
        TextView balanceTextView = findViewById(R.id.balance);



        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDate now = LocalDate.now();
            from = now.withDayOfMonth(1);
            to = now.withDayOfMonth(now.lengthOfMonth());
        }


        SharedPreferences prefs = getSharedPreferences("MyPref", MODE_PRIVATE);
        Token token = new Token(prefs.getString("TOKEN"," "));


        Call<Balance> call = mBackendServerService.getBalance(token.getToken(), userId, from, to);
        call.enqueue(new Callback<Balance>() {

            @Override
            public void onResponse(final Call<Balance> call, final Response<Balance> response) {
                if (response.isSuccessful()) {

                    balanceTextView.setText(String.valueOf(response.body().getAmount()));

                } else {

                    balanceTextView.setText("Call Error!");
                }
            }

            @Override
            public void onFailure(final Call<Balance> call, final Throwable t) {
                balanceTextView.setText("Error!");
            }
        });


    }


}
