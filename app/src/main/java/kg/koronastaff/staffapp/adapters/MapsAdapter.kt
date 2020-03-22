package kg.koronastaff.staffapp.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kg.koronastaff.staffapp.R
import kg.koronastaff.staffapp.models.StationMap
import kotlinx.android.synthetic.main.list_item_map.view.*
import java.util.*

class MapsAdapter(private var mDataset: ArrayList<StationMap>, var parent: Context) : RecyclerView.Adapter<MapsAdapter.MyViewHolder>() {

    class MyViewHolder(var frame: CardView) : RecyclerView.ViewHolder(frame)

    override fun onCreateViewHolder(
            parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_map, parent, false) as CardView
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val s = mDataset[position]
        // implement StationMap to item in recycler
        holder.frame.text_map_1.text = s.address
        holder.frame.text_map_2.text = s.telephone
        holder.frame.text_map_3.text = s.website
        holder.frame.image_map_4.setOnClickListener { v: View? ->
            val gmmIntentUri = Uri.parse(
                    "geo:0,0?q=" + s.lat + "," + s.lon)
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            parent.startActivity(mapIntent)
        }
    }

    override fun getItemCount(): Int {
        return mDataset.size
    }

    fun update(myDataset: ArrayList<StationMap>) {
        mDataset = myDataset
        notifyDataSetChanged()
    }

}