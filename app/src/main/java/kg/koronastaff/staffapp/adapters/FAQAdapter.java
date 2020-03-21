package kg.koronastaff.staffapp.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import kg.koronastaff.staffapp.R;
import kg.koronastaff.staffapp.models.FAQ;
import kg.koronastaff.staffapp.models.StationMap;

public class FAQAdapter extends RecyclerView.Adapter<FAQAdapter.MyViewHolder> {
    private ArrayList<FAQ> mDataset;

    static class MyViewHolder extends RecyclerView.ViewHolder {
        CardView frame;
        MyViewHolder(CardView v) {
            super(v);
            frame = v;
        }
    }

    public FAQAdapter(ArrayList<FAQ> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public FAQAdapter.MyViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {

        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.quarantine_steps_item, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        FAQ s = this.mDataset.get(position);
        TextView question = holder.frame.findViewById(R.id.step_num);
        TextView answer = holder.frame.findViewById(R.id.step_body);
        String b = s.getAnswer();
        if (b.length() > 199) {
            b = b.substring(0, 200).concat("...");
        }
        answer.setText(b);
        question.setText(s.getQuestion());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void update(ArrayList<FAQ> myDataset) {
        mDataset = myDataset;
        notifyDataSetChanged();
    }
}