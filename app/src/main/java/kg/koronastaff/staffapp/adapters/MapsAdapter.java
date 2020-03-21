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
import kg.koronastaff.staffapp.models.StationMap;

public class MapsAdapter extends RecyclerView.Adapter<MapsAdapter.MyViewHolder> {
    private ArrayList<StationMap> mDataset;

    static class MyViewHolder extends RecyclerView.ViewHolder {
        CardView frame;
        MyViewHolder(CardView v) {
            super(v);
            frame = v;
        }
    }

    public MapsAdapter(ArrayList<StationMap> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public MapsAdapter.MyViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {

        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_map, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        StationMap s = this.mDataset.get(position);
        // implement StationMap to item in recycler

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void update(ArrayList<StationMap> myDataset) {
        mDataset = myDataset;
        notifyDataSetChanged();
    }
}