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
import com.bumptech.glide.Glide
import kg.koronastaff.staffapp.R
import kg.koronastaff.staffapp.models.Contacts
import kg.koronastaff.staffapp.models.StationMap
import kotlinx.android.synthetic.main.list_item_map.view.*
import kotlinx.android.synthetic.main.quarantine_steps_item.view.*
import java.util.*

class ContactsAdapter(private var mDataset: ArrayList<Contacts>, var parent: Context) : RecyclerView.Adapter<ContactsAdapter.MyViewHolder>() {

    class MyViewHolder(var frame: CardView) : RecyclerView.ViewHolder(frame)

    override fun onCreateViewHolder(
            parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.contacts_item, parent, false) as CardView
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val s = mDataset[position]
        // implement StationMap to item in recycler
        if (s.image != null){
            holder.frame.image_tip.visibility = View.VISIBLE
            Glide.with(parent).load(s.image).into(holder.frame.image_tip)
        }
        holder.frame.step_num.text = s.region + " область"
        holder.frame.step_body.text = s.text
    }

    override fun getItemCount(): Int {
        return mDataset.size
    }

    fun update(myDataset: ArrayList<Contacts>) {
        mDataset = myDataset
        notifyDataSetChanged()
    }
}