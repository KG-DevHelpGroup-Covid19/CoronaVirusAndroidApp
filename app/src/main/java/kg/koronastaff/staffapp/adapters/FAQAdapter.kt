package kg.koronastaff.staffapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kg.koronastaff.staffapp.R
import kg.koronastaff.staffapp.models.FAQ
import kotlinx.android.synthetic.main.quarantine_steps_item.view.*
import java.util.*

class FAQAdapter(private var mDataset: ArrayList<FAQ>) : RecyclerView.Adapter<FAQAdapter.MyViewHolder>() {

    class MyViewHolder(var frame: CardView) : RecyclerView.ViewHolder(frame)

    override fun onCreateViewHolder(
            parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.quarantine_steps_item, parent, false) as CardView
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val s = mDataset[position]
        holder.frame.step_body.text = s.answer
        holder.frame.step_num.text = s.question
    }

    override fun getItemCount(): Int {
        return mDataset.size
    }

    fun update(myDataset: ArrayList<FAQ>) {
        mDataset = myDataset
        notifyDataSetChanged()
    }

}