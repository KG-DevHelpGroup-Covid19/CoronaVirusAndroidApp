package kg.koronastaff.staffapp.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import kg.koronastaff.staffapp.R;
import kg.koronastaff.staffapp.models.QuarantineSteps;

public class QuarantineAdapter extends RecyclerView.Adapter<QuarantineAdapter.MyViewHolder> {
    private ArrayList<QuarantineSteps> mDataset;

    static class MyViewHolder extends RecyclerView.ViewHolder {
        CardView frame;
        MyViewHolder(CardView v) {
            super(v);
            frame = v;
        }
    }

    public QuarantineAdapter(ArrayList<QuarantineSteps> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public QuarantineAdapter.MyViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {

        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.quarantine_steps_item, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        QuarantineSteps f = this.mDataset.get(position);
        TextView title = holder.frame.findViewById(R.id.step_num);
        title.setText(f.getTitle());
        TextView body = holder.frame.findViewById(R.id.step_body);
        body.setText(f.getBody());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void update(ArrayList<QuarantineSteps> myDataset) {
        mDataset = myDataset;
        notifyDataSetChanged();
    }
}