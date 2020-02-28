package pl.dawidkaszuba.homebudget.view;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import pl.dawidkaszuba.homebudget.R;

public class ExpendituresListActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expenditures_list);

        RecyclerView recyclerView = findViewById(R.id.recyclerView_expenditure_list);


    }
}
