package pl.dawidkaszuba.homebudget.view;

import android.os.Bundle;
import android.text.Editable;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.math.BigDecimal;
import java.util.List;

import pl.dawidkaszuba.homebudget.ApiUtils;
import pl.dawidkaszuba.homebudget.R;
import pl.dawidkaszuba.homebudget.RetrofitClient;
import pl.dawidkaszuba.homebudget.pojo.Expenditure;
import pl.dawidkaszuba.homebudget.pojo.Tag;
import pl.dawidkaszuba.homebudget.pojo.Token;
import pl.dawidkaszuba.homebudget.pojo.User;
import pl.dawidkaszuba.homebudget.service.BackendServerService;
import pl.dawidkaszuba.homebudget.shearedPreferences.MyPreferences;
import pl.dawidkaszuba.homebudget.presenter.PresenterContract;
import pl.dawidkaszuba.homebudget.presenter.TagsPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ExpenditureActivity extends AppCompatActivity implements ViewContract {

    Retrofit retrofit = RetrofitClient.getClient(ApiUtils.BASE_URL);


    BackendServerService mBackendServerService = retrofit.create(BackendServerService.class);

    Spinner spinner;

    PresenterContract presenterContract;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenditure);


        EditText expenditureNoteField= findViewById(R.id.expenditure_note);
        EditText expenditureAmountField= findViewById(R.id.expenditure_amount);


        Editable expenditureAmount = expenditureAmountField.getText();
        Editable expenditureNote = expenditureNoteField.getText();


        spinner = findViewById(R.id.tag_spinner);

        presenterContract = new TagsPresenter(this);
        presenterContract.getTags();


    }



    private void addExpenditure( BigDecimal expenditureAmount, String note, Tag tag){

        MyPreferences myPreferences = MyPreferences.getInstance(getApplicationContext());
        Token token = new Token(myPreferences.getPreference("TOKEN"));
        Long userId = Long.parseLong(myPreferences.getPreference("USER_ID"));

        User user = new User(userId);
        tag = new Tag();


        Expenditure expenditure = new Expenditure(expenditureAmount,tag,note,user);

        Call<Expenditure> call = mBackendServerService.addExpenditure(token.getToken(),expenditure,userId);
        call.enqueue(new Callback<Expenditure>() {

            @Override
            public void onResponse(final Call<Expenditure> call, final Response<Expenditure> response) {
                if (response.isSuccessful()) {

                    Toast.makeText(getApplicationContext(),"expenditure created",Toast.LENGTH_SHORT).show();

                }else{

                    Toast.makeText(ExpenditureActivity.this,response.errorBody().toString(),Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(final Call<Expenditure> call, final Throwable t) {
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
