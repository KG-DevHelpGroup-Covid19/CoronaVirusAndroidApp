package kg.koronastaff.staffapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kg.koronastaff.staffapp.R
import kg.koronastaff.staffapp.models.City
import kg.koronastaff.staffapp.ui.map.ImplCityView
import kg.koronastaff.staffapp.ui.tests.ImplTestFragmentView
import kotlinx.android.synthetic.main.item_city_name.view.*
import java.util.*

class CityListAdapter(private var list: ArrayList<*>, private var implCityView: ImplCityView?, private var implTestFragmentView: ImplTestFragmentView?, private var LAYOUT: Int) : RecyclerView.Adapter<CityListAdapter.MyCityHolder>() {

    private val LAYOUT_REGIONS = 10001

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
        if (LAYOUT == LAYOUT_REGIONS) {
            val s = list[position] as String
            holder.frame.i_c_n_title.text = s

            holder.itemView.setOnClickListener {
                if (implTestFragmentView != null)
                    implTestFragmentView?.selectedRegion(s, position)
            }
        } else {
            val s = list[position] as City
            holder.frame.i_c_n_title.text = s.name
            holder.itemView.setOnClickListener {
                if (implCityView != null)
                    implCityView?.selectedCity(s.name, position)
                else if (implTestFragmentView != null)
                    implTestFragmentView?.selectedCity(s.name, position)
            }
        }
    }

    fun setList(list: ArrayList<*>) {
        this.list = list
    }

}