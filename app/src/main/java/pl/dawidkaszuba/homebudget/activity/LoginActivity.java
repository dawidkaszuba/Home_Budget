package pl.dawidkaszuba.homebudget.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import pl.dawidkaszuba.homebudget.ApiUtils;
import pl.dawidkaszuba.homebudget.R;
import pl.dawidkaszuba.homebudget.RetrofitClient;
import pl.dawidkaszuba.homebudget.model.LoggedUser;
import pl.dawidkaszuba.homebudget.model.Token;
import pl.dawidkaszuba.homebudget.model.User;
import pl.dawidkaszuba.homebudget.service.BackendServerService;
import pl.dawidkaszuba.homebudget.shearedPreferences.MyPreferences;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {


    Retrofit retrofit = RetrofitClient.getClient(ApiUtils.BASE_URL);


    BackendServerService mBackendServerService = retrofit.create(BackendServerService.class);


    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button registerButton = findViewById(R.id.register_send);

        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                 EditText userNameField = findViewById(R.id.username);
                 EditText passwordField = findViewById(R.id.password);
                 login(userNameField.getText().toString(),passwordField.getText().toString());
            }
        });

    }


    private void login(String userName, String password){

        User user = new User(userName, password);

        Call<LoggedUser> call = mBackendServerService.authenticate(user);
        call.enqueue(new Callback<LoggedUser>() {

            @Override
            public void onResponse(final Call<LoggedUser> call, final Response<LoggedUser> response) {
                if (response.isSuccessful()) {

                    Intent intent = new Intent(getApplicationContext(), BalanceActivity.class);

                    MyPreferences myPreferences = MyPreferences.getInstance(getApplicationContext());
                    myPreferences.savePreference("TOKEN","Bearer " + response.body().getToken());
                    myPreferences.savePreference("USER_ID",response.body().getId().toString());

                    startActivity(intent);

                }else{

                    Toast.makeText(LoginActivity.this,response.errorBody().toString(),Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(final Call<LoggedUser> call, final Throwable t) {
                Toast.makeText(LoginActivity.this,"Error!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
