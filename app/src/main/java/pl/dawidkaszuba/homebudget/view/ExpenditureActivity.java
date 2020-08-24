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
import pl.dawidkaszuba.homebudget.pojo.PlannedCashFlow;
import pl.dawidkaszuba.homebudget.pojo.Tag;
import pl.dawidkaszuba.homebudget.presenter.PresenterExpenditureContract;
import pl.dawidkaszuba.homebudget.presenter.PresenterExpenditureContractImpl;

public class ExpenditureActivity extends Activity implements ViewExpenditureContract {

    Spinner tag_spinner;
    Spinner pcf_spinner;

    PresenterExpenditureContract presenterContract;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenditure);


        tag_spinner = findViewById(R.id.tag_spinner);
        presenterContract = new PresenterExpenditureContractImpl(this);
        presenterContract.getTags("NEGATIVE");

        pcf_spinner = findViewById(R.id.pcf_spinner);
        presenterContract.getPlannedCashFlows();

        Button createExpenditure = findViewById(R.id.expenditure_send);

        EditText expenditureNoteField = findViewById(R.id.expenditure_note);
        EditText expenditureAmountField = findViewById(R.id.expenditure_amount);
        Editable expenditureAmount = expenditureAmountField.getText();
        Editable expenditureNote = expenditureNoteField.getText();

        View view = new View(getApplicationContext());

        createExpenditure.setOnClickListener( v -> {
            Tag tag = (Tag) tag_spinner.getSelectedItem();
            PlannedCashFlow pcf = (PlannedCashFlow) pcf_spinner.getSelectedItem();
            presenterContract
                   .addExpenditure(view,expenditureAmount.toString(),
                           expenditureNote.toString(),
                           tag,pcf);
        });
    }


    @Override
    public void fillTagsSpinner(List<Tag> tags) {

        ArrayAdapter<Tag> adapter = new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item,tags);

        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        tag_spinner.setAdapter(adapter);

    }
    @Override
    public void fillPlannedCashFlowSpinner(final List<PlannedCashFlow> plannedCashFlows) {

        ArrayAdapter<PlannedCashFlow> adapter = new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item,plannedCashFlows);

        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        pcf_spinner.setAdapter(adapter);
    }




    @Override
    public void errorMessage(String msg){
        Toast.makeText(ExpenditureActivity.this,"error: " + msg, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void navigateToHomeScreen(Intent intent) {
        startActivity(intent);
    }



}
