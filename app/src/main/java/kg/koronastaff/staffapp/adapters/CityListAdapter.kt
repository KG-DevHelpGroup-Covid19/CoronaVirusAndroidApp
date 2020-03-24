package kg.koronastaff.staffapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kg.koronastaff.staffapp.R
import kg.koronastaff.staffapp.models.City
import kg.koronastaff.staffapp.ui.map.ImplCityView
import kotlinx.android.synthetic.main.item_city_name.view.*
import java.util.*

class CityListAdapter(private var list: ArrayList<City>, private var implCityView: ImplCityView) : RecyclerView.Adapter<CityListAdapter.MyCityHolder>() {

    class MyCityHolder(var frame: CardView) : RecyclerView.ViewHolder(frame)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCityHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_city_name, parent, false) as CardView
        return MyCityHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyCityHolder, position: Int) {
        val s = list[position]
        holder.frame.i_c_n_title.text = s.name

        holder.itemView.setOnClickListener {
            implCityView.selectedCity(s.name, position)
        }
    }

    fun setList(list: ArrayList<City>) {
        this.list = list
    }

}