package kg.koronastaff.staffapp.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kg.koronastaff.staffapp.R
import kg.koronastaff.staffapp.adapters.MapsAdapter
import kg.koronastaff.staffapp.models.City
import kg.koronastaff.staffapp.ui.CoronaViewModel
import kg.koronastaff.staffapp.ui.FragmentWithStat
import kotlinx.android.synthetic.main.fragment_map.*


class MapFragment : FragmentWithStat() {

    private var recyclerView: RecyclerView? = null
    private lateinit var mAdapter: MapsAdapter
    private var viewManager: RecyclerView.LayoutManager? = null
    private lateinit var cities: ArrayList<City>
    private var viewModel: CoronaViewModel = super.coronaViewModel

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val rootView = inflater.inflate(R.layout.fragment_map, container, false)
        viewManager = LinearLayoutManager(context)
        mAdapter = MapsAdapter(arrayListOf(), activity!!)
        recyclerView = rootView.findViewById<RecyclerView>(R.id.map_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = mAdapter
        }
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        super.updateStats(cache.getStat())

        cities = cache.getCities()

        updateSpinner(cities)

        viewModel.getCities()?.subscribe{
            updateSpinner(it.results)
            cache.saveCities(it.results)
            cities = it.results
        }

        cities_spinner.onItemSelectedListener = object: OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val city = cities[position]
                viewModel.getStationsByCity(city.pk)?.subscribe{
                    mAdapter.update(it.results)
                }
            }
        }
    }

    fun updateSpinner(list: ArrayList<City>){
        val listNew = arrayListOf<String>()
        list.forEach{
            listNew.add(it.name)
        }
        cities_spinner.adapter =
                ArrayAdapter<String>(activity!!,
                        android.R.layout.simple_spinner_item,
                        listNew)
    }
}