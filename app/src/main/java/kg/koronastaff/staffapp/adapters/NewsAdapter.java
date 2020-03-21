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
import kg.koronastaff.staffapp.models.News;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {
    private ArrayList<News> mDataset;

    static class MyViewHolder extends RecyclerView.ViewHolder {
        CardView frame;
        MyViewHolder(CardView v) {
            super(v);
            frame = v;
        }
    }

    public NewsAdapter(ArrayList<News> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public NewsAdapter.MyViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {

        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fake_news_item, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        News f = this.mDataset.get(position);
        TextView title = holder.frame.findViewById(R.id.fake_text);
        title.setText(f.getTitle());
        TextView body = holder.frame.findViewById(R.id.fake_body);
        TextView time = holder.frame.findViewById(R.id.fake_news_time);
        String b = f.getBody();
        if (b.length() > 199) {
            b = b.substring(0, 200).concat("...");
        }
        body.setText(b);
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

    public void update(ArrayList<News> myDataset) {
        mDataset = myDataset;
        notifyDataSetChanged();
    }
}