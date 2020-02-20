package pl.dawidkaszuba.homebudget.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.math.BigDecimal;
import java.util.List;

import okhttp3.ResponseBody;
import pl.dawidkaszuba.homebudget.ApiUtils;
import pl.dawidkaszuba.homebudget.R;
import pl.dawidkaszuba.homebudget.RetrofitClient;
import pl.dawidkaszuba.homebudget.pojo.Expenditure;
import pl.dawidkaszuba.homebudget.pojo.Tag;
import pl.dawidkaszuba.homebudget.pojo.Token;
import pl.dawidkaszuba.homebudget.pojo.User;
import pl.dawidkaszuba.homebudget.presenter.PresenterTagContract;
import pl.dawidkaszuba.homebudget.presenter.PresenterTagContractImpl;
import pl.dawidkaszuba.homebudget.service.BackendServerService;
import pl.dawidkaszuba.homebudget.shearedPreferences.MyPreferences;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ExpenditureActivity extends AppCompatActivity implements ViewTagContract {

    Retrofit retrofit = RetrofitClient.getClient(ApiUtils.BASE_URL);


    BackendServerService mBackendServerService = retrofit.create(BackendServerService.class);

    Spinner spinner;

    PresenterTagContract presenterContract;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenditure);


        spinner = findViewById(R.id.tag_spinner);
        presenterContract = new PresenterTagContractImpl(this);
        presenterContract.getTags();



        Button createExpenditure = findViewById(R.id.expenditure_send);

        createExpenditure.setOnClickListener( v -> addExpenditure());


    }



    private void addExpenditure(){
        Intent intent = new Intent(getApplicationContext(), BalanceActivity.class);

        EditText expenditureNoteField= findViewById(R.id.expenditure_note);
        EditText expenditureAmountField= findViewById(R.id.expenditure_amount);


        Editable expenditureAmount = expenditureAmountField.getText();
        Editable expenditureNote = expenditureNoteField.getText();



        Tag tag = (Tag) spinner.getSelectedItem();

        MyPreferences myPreferences = MyPreferences.getInstance(getApplicationContext());
        Long userId = Long.parseLong(myPreferences.getPreference("USER_ID"));
        String userName = myPreferences.getPreference("USER_NAME");
        Token token = new Token(myPreferences.getPreference("TOKEN"));

        User user = new User(userId,userName);
        Expenditure expenditure = new Expenditure(new BigDecimal(expenditureAmount.toString()),tag,expenditureNote.toString(),user);


        Call<ResponseBody> call = mBackendServerService.addExpenditure(token.getToken(),expenditure,userId);
        call.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(final Call<ResponseBody> call, final Response<ResponseBody> response) {
                if (response.isSuccessful()) {

                    startActivity(intent);

                }else{

                    Toast.makeText(ExpenditureActivity.this,response.errorBody().toString(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(final Call<ResponseBody> call, final Throwable t) {
                Toast.makeText(ExpenditureActivity.this,"Error!",Toast.LENGTH_SHORT).show();
            }
        });


    }


    @Override
    public void fillTagsSpinner(List<Tag> tags) {

        ArrayAdapter<Tag> adapter = new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item,tags);

        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

    }

    @Override
    public void errorMessage(String msg){
        Toast.makeText(ExpenditureActivity.this,"error: " + msg, Toast.LENGTH_SHORT).show();
    }


}
