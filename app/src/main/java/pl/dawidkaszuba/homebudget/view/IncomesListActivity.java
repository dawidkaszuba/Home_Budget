package pl.dawidkaszuba.homebudget.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pl.dawidkaszuba.homebudget.R;
import pl.dawidkaszuba.homebudget.pojo.Income;

public class IncomesListActivity extends Activity implements ViewIncomesListContract{

    private IncomeAdapter incomeAdapter;
    private List<Income> incomeList;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.incomes_list_activity);
    }

    @Override
    public void setIncomeList(final List<Income> incomes) {
        this.incomeList = incomes;
    }


    private class IncomeAdapter extends RecyclerView.Adapter<IncomeAdapter.IncomeViewHolder>{

        private List<Income> incomes;

        public IncomeAdapter(final List<Income> incomes) {
            this.incomes = incomes;
        }

        public class IncomeViewHolder extends RecyclerView.ViewHolder {

            public TextView expenditureDescription;
            public ImageButton deleteExpenditureButton;


            public IncomeViewHolder(@NonNull final View itemView) {
                super(itemView);
            }
        }



        @NonNull
        @Override
        public IncomeViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
            View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.incomes_list_activity, parent, false);
            IncomeViewHolder viewHolder = new IncomeViewHolder(view);
            viewHolder.expenditureDescription = view.findViewById(R.id.income_item);
            viewHolder.deleteExpenditureButton = view.findViewById(R.id.delete_income_button);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull final IncomeViewHolder holder, final int position) {
            Income income = incomes.get(position);
            holder.expenditureDescription.setText(new StringBuilder()
                    .append(income.getNote())
                    .append(" ")
                    .append(income.getAmount())
                    .append(" ")
                    .append(income.getIncomeDate())
                    .append(" ")
                    .append(income.getTag())
                    .toString());
            holder.deleteExpenditureButton.setOnClickListener(v -> {
                int position1 = holder.getAdapterPosition();
                incomes.remove(position1);
                incomeAdapter.notifyItemRemoved(position1);
            });
        }

        @Override
        public int getItemCount() {
            return incomes.size();
        }
    }

}
