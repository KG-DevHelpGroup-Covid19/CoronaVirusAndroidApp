package kg.koronastaff.staffapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kg.koronastaff.staffapp.R
import kg.koronastaff.staffapp.models.Tips
import kotlinx.android.synthetic.main.list_item_map.view.*
import kotlinx.android.synthetic.main.quarantine_steps_item.view.*
import java.util.*

class TipsAdapter(private var mDataset: ArrayList<Tips>, var context: Context) : RecyclerView.Adapter<TipsAdapter.MyViewHolder>() {

    class MyViewHolder(var frame: CardView) : RecyclerView.ViewHolder(frame)

    override fun onCreateViewHolder(
            parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.quarantine_steps_item, parent, false) as CardView
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val f = mDataset[position]
        holder.frame.step_num.text = f.title
        holder.frame.step_body.text = f.body
        if (f.image != null){
            holder.frame.image_tip.visibility = View.VISIBLE
            Glide.with(context).load(f.image).into(holder.frame.image_tip)
        }
    }

    override fun getItemCount(): Int {
        return mDataset.size
    }

    fun update(myDataset: ArrayList<Tips>) {
        mDataset = myDataset
        notifyDataSetChanged()
    }

}