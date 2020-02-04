package pl.dawidkaszuba.homebudget.activity;

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
import pl.dawidkaszuba.homebudget.model.Token;
import pl.dawidkaszuba.homebudget.model.User;
import pl.dawidkaszuba.homebudget.service.BackendServerService;
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

        Call<Token> call = mBackendServerService.authenticate(user);
        call.enqueue(new Callback<Token>() {

            @Override
            public void onResponse(final Call<Token> call, final Response<Token> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    Toast.makeText(LoginActivity.this,response.body().getToken(),Toast.LENGTH_SHORT).show();
                    System.out.println(response.body().getToken());

                }else{
                    Toast.makeText(LoginActivity.this,response.errorBody().toString(),Toast.LENGTH_SHORT).show();
                    System.out.println(response.code());
                }
            }


            @Override
            public void onFailure(final Call<Token> call, final Throwable t) {
                Toast.makeText(LoginActivity.this,"Error!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
