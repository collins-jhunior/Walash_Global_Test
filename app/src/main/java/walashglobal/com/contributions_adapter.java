package walashglobal.com;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class contributions_adapter extends RecyclerView.Adapter<contributions_adapter.ViewHolder> {
    private Context context;
    private ArrayList<String> ids, amounts, dates;

    contributions_adapter(Context context, ArrayList<String> ids, ArrayList<String> amounts, ArrayList<String> dates) {
        this.context = context;
        this.ids = ids;
        this.amounts = amounts;
        this.dates = dates;
    }

    @NonNull
    @Override
    public contributions_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.contributions_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull contributions_adapter.ViewHolder holder, int position) {
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        String amount = "₦" + formatter.format(Double.parseDouble(amounts.get(position))) + " on " + dates.get(position);
        holder.user_id.setText(ids.get(position));
        holder.amount.setText(amount);
    }

    @Override
    public int getItemCount() {
        return ids.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView user_id, amount;

        ViewHolder(@NonNull View v) {
            super(v);
            user_id = v.findViewById(R.id.user_id);
            amount = v.findViewById(R.id.amount);
        }
    }
}
