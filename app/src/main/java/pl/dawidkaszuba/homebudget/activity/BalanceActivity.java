package pl.dawidkaszuba.homebudget.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import pl.dawidkaszuba.homebudget.R;

public class BalanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);

        TextView balanceTextView = findViewById(R.id.balance);

        balanceTextView.setText();
    }
}
