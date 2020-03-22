package kg.koronastaff.staffapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

import kg.koronastaff.staffapp.R;
import kg.koronastaff.staffapp.models.StationMap;

public class MapsAdapter extends RecyclerView.Adapter<MapsAdapter.MyViewHolder> {
    private ArrayList<StationMap> mDataset;
    Context parent;

    static class MyViewHolder extends RecyclerView.ViewHolder {
        CardView frame;
        MyViewHolder(CardView v) {
            super(v);
            frame = v;
        }
    }

    public MapsAdapter(ArrayList<StationMap> myDataset, Context parent) {
        mDataset = myDataset;
        this.parent = parent;
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
        ImageView mapImageView = holder.frame.findViewById(R.id.image_map_4);
        TextView address = holder.frame.findViewById(R.id.text_map_1);
        TextView number = holder.frame.findViewById(R.id.text_map_2);
        TextView webSite = holder.frame.findViewById(R.id.text_map_3);

        address.setText(s.getAddress());
        number.setText(s.getTelephone());
        webSite.setText(s.getWebsite());

        mapImageView.setOnClickListener(v->{
            Uri gmmIntentUri = Uri.parse(
                    "geo:0,0?q=" + s.getLat() + "," + s.getLon());
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            parent.startActivity(mapIntent);
        });
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