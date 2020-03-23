package kg.koronastaff.staffapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import kg.koronastaff.staffapp.App
import kg.koronastaff.staffapp.R
import kg.koronastaff.staffapp.models.News
import kotlinx.android.synthetic.main.fake_news_item.view.*
import java.util.*

class NewsAdapter(private var mDataset: ArrayList<News>, var parent: FragmentActivity) :
        RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {

    var app: App = parent.application as App

    class MyViewHolder(var frame: CardView) : RecyclerView.ViewHolder(frame)

    override fun onCreateViewHolder(
            parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.fake_news_item, parent, false) as CardView
        return MyViewHolder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val news = mDataset[position]
        holder.frame.fake_text.text = news.title
        var b = news.body
        if (b.length > 199) {
            b = b.substring(0, 200) + "..."
        }
        holder.frame.fake_body.text = b
        holder.frame.fake_news_time.text = news.created_at
        holder.frame.setOnClickListener { v: View? ->
            app.putData("article", news)
            Navigation.findNavController(v!!).navigate(R.id.article)
        }
    }

    override fun getItemCount(): Int {
        return mDataset.size
    }

    fun update(myDataset: ArrayList<News>) {
        mDataset = myDataset
        notifyDataSetChanged()
    }

}