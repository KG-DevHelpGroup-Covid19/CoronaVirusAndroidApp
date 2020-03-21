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
import kg.koronastaff.staffapp.models.FakeNews;
import kg.koronastaff.staffapp.models.QuarantineSteps;

public class FakeNewsAdapter extends RecyclerView.Adapter<FakeNewsAdapter.MyViewHolder> {
    private ArrayList<FakeNews> mDataset;

    static class MyViewHolder extends RecyclerView.ViewHolder {
        CardView frame;
        MyViewHolder(CardView v) {
            super(v);
            frame = v;
        }
    }

    public FakeNewsAdapter(ArrayList<FakeNews> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public FakeNewsAdapter.MyViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {

        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fake_news_item, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        FakeNews f = this.mDataset.get(position);
        TextView title = holder.frame.findViewById(R.id.fake_text);
        title.setText(f.getTitle());
        TextView body = holder.frame.findViewById(R.id.fake_body);
        TextView time = holder.frame.findViewById(R.id.fake_news_time);
        body.setText(f.getBody().substring(0, 200).concat("..."));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(f.getTimestamp()));
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        time.setText(hours + ":" + minutes);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void update(ArrayList<FakeNews> myDataset) {
        mDataset = myDataset;
        notifyDataSetChanged();
    }
}