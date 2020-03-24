package kg.koronastaff.staffapp.ui.map

import android.os.Bundle
import android.util.Log
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
import kg.koronastaff.staffapp.database.Cache
import kg.koronastaff.staffapp.models.City
import kg.koronastaff.staffapp.models.TestResults
import kg.koronastaff.staffapp.ui.CoronaViewModel
import kg.koronastaff.staffapp.ui.FragmentWithStat
import kotlinx.android.synthetic.main.fragment_map.*


class MapFragment : FragmentWithStat() {

    private var recyclerView: RecyclerView? = null
    private lateinit var mAdapter: MapsAdapter
    private var viewManager: RecyclerView.LayoutManager? = null
    private lateinit var cities: ArrayList<City>
    private lateinit var viewModel: CoronaViewModel
    lateinit var appCache: Cache
    private var selectId: Int = 0

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        viewModel= super.coronaViewModel
        appCache  = super.cache
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

        if (cities.size < 1){
            viewModel.getCities()?.subscribe{
                updateSpinner(it.results)
                appCache.saveCities(it.results)
                cities = it.results
            }
        }

        viewModel.getStationsByCity(selectId)?.subscribe{
            mAdapter.update(it.results)
            Log.d("tag", "results" + it.results)
        }

        cities_spinner.onItemSelectedListener = object: OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val city = cities[position]
                cache.saveSelectedCity(city)
                viewModel.getStationsByCity(city.pk)?.subscribe{
                    if(it.results.size > 0){
                        not_found_text.visibility = View.GONE
                    }else{
                        not_found_text.visibility = View.VISIBLE
                    }
                    mAdapter.update(it.results)
                }
            }

        }
    }

    fun updateSpinner(list: ArrayList<City>){
        val listNew = arrayListOf<String>()
        val city = cache.getSelectedCity()
        var i = 0
        list.forEach{
            listNew.add(it.name)
            if (it.pk == city.pk) selectId = i
            i++
        }
        cities_spinner.adapter =
                ArrayAdapter<String>(activity!!,
                        android.R.layout.simple_spinner_item,
                        listNew)
        cities_spinner.setSelection(selectId)
    }



}