package kg.koronastaff.staffapp.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import kg.koronastaff.staffapp.R;
import kg.koronastaff.staffapp.models.QuarantineSteps;
import kg.koronastaff.staffapp.models.Tips;

public class TipsAdapter extends RecyclerView.Adapter<TipsAdapter.MyViewHolder> {
    private ArrayList<Tips> mDataset;

    static class MyViewHolder extends RecyclerView.ViewHolder {
        CardView frame;
        MyViewHolder(CardView v) {
            super(v);
            frame = v;
        }
    }

    public TipsAdapter(ArrayList<Tips> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public TipsAdapter.MyViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {

        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.quarantine_steps_item, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Tips f = this.mDataset.get(position);
        TextView title = holder.frame.findViewById(R.id.step_num);
        title.setText(f.getTitle());
        TextView body = holder.frame.findViewById(R.id.step_body);
        body.setText(f.getBody());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void update(ArrayList<Tips> myDataset) {
        mDataset = myDataset;
        notifyDataSetChanged();
    }
}