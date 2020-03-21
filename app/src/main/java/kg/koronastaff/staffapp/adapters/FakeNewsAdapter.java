package kg.koronastaff.staffapp.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import kg.koronastaff.staffapp.R;
import kg.koronastaff.staffapp.models.FakeNews;

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
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}