package kg.koronastaff.staffapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kg.koronastaff.staffapp.R
import kg.koronastaff.staffapp.models.QuarantineSteps
import java.util.*

class QuarantineAdapter(private var mDataset: ArrayList<QuarantineSteps>) :
        RecyclerView.Adapter<QuarantineAdapter.MyViewHolder>() {

    class MyViewHolder(var frame: CardView) : RecyclerView.ViewHolder(frame)

    override fun onCreateViewHolder(
            parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.quarantine_steps_item, parent, false) as CardView
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val f = mDataset[position]
        val title = holder.frame.findViewById<TextView>(R.id.step_num)
        title.text = f.title
        val body = holder.frame.findViewById<TextView>(R.id.step_body)
        body.text = f.body
    }

    override fun getItemCount(): Int {
        return mDataset.size
    }

    fun update(myDataset: ArrayList<QuarantineSteps>) {
        mDataset = myDataset
        notifyDataSetChanged()
    }

}