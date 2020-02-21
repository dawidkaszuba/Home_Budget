package pl.dawidkaszuba.homebudget.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.List;

import pl.dawidkaszuba.homebudget.R;
import pl.dawidkaszuba.homebudget.pojo.Tag;
import pl.dawidkaszuba.homebudget.presenter.PresenterIncomeContract;
import pl.dawidkaszuba.homebudget.presenter.PresenterIncomeContractImpl;

public class IncomeActivity extends Activity implements  ViewIncomeContract{


    Spinner spinner;

    PresenterIncomeContract presenterContract;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        spinner = findViewById(R.id.tag_income_spinner);
        presenterContract = new PresenterIncomeContractImpl(this);
        presenterContract.getTags("POSITIVE");

        Button createExpenditure = findViewById(R.id.income_send);

        EditText incomeNoteField = findViewById(R.id.income_note);
        EditText incomeAmountField = findViewById(R.id.income_amount);
        Editable incomeAmount = incomeAmountField.getText();
        Editable incomeNote = incomeNoteField.getText();

        View view = new View(getApplicationContext());
        Tag tag = (Tag) spinner.getSelectedItem();

        createExpenditure.setOnClickListener(
                v -> presenterContract
                     .addIncome(view,incomeAmount.toString(),
                        incomeNote.toString(),
                        tag));
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
        Toast.makeText(IncomeActivity.this,"error: " + msg, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void navigateToHomeScreen(Intent intent) {
        startActivity(intent);
    }


}
