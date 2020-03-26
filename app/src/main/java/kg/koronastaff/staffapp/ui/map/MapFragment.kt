package kg.koronastaff.staffapp.ui.map

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import kg.koronastaff.staffapp.R
import kg.koronastaff.staffapp.adapters.CityListAdapter
import kg.koronastaff.staffapp.adapters.MapsAdapter
import kg.koronastaff.staffapp.database.Cache
import kg.koronastaff.staffapp.models.City
import kg.koronastaff.staffapp.ui.CoronaViewModel
import kg.koronastaff.staffapp.ui.FragmentWithStat
import kotlinx.android.synthetic.main.fragment_map.*
import kotlinx.android.synthetic.main.fragment_map.view.*
import kotlinx.android.synthetic.main.item_city_list.view.*
import java.util.*
import kotlin.collections.ArrayList


class MapFragment : FragmentWithStat(), ImplCityView {

    private var recyclerView: RecyclerView? = null
    private lateinit var mAdapter: MapsAdapter
    private lateinit var mCityAdapter: CityListAdapter
    private var viewManager: RecyclerView.LayoutManager? = null
    private lateinit var cities: ArrayList<City>
    private lateinit var result: ArrayList<City>
    private lateinit var viewModel: CoronaViewModel
    lateinit var appCache: Cache
    private var selectId: Int = 0
    private lateinit var rootView: View
    private lateinit var alertDialog: AlertDialog

    var d: Disposable? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        viewModel= super.coronaViewModel
        appCache  = super.cache
        rootView = inflater.inflate(R.layout.fragment_map, container, false)
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
        val c: City = cache.getSelectedCity()
        f_m_selected_city.text = c.name
        d = viewModel.getStationsByCity(c.pk)?.doOnError{
            Toast.makeText(activity, getString(R.string.net_problem), Toast.LENGTH_LONG).show()
            d?.dispose()
        }?.subscribe{
            if(it.results.size > 0){
                not_found_text.visibility = View.GONE
            }else{
                not_found_text.visibility = View.VISIBLE
            }
            mAdapter.update(it.results)
            d?.dispose()
        }

        updateSpinner(cities)

        if (cities.size < 1){
            viewModel.getCities()?.subscribe{
                updateSpinner(it.results)
                appCache.saveCities(it.results)
                cities = it.results
            }
        }


        cities_spinner.onItemSelectedListener = object: OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val city = cities[position]
                cache.saveSelectedCity(city)
                d?.dispose()
                d = viewModel.getStationsByCity(city.pk)?.doOnError{
                    Toast.makeText(activity, getString(R.string.net_problem), Toast.LENGTH_LONG).show()
                    d?.dispose()
                }?.subscribe{
                    if(it.results.size > 0){
                        not_found_text.visibility = View.GONE
                    }else{
                        not_found_text.visibility = View.VISIBLE
                    }
                    mAdapter.update(it.results)
                    d?.dispose()
                }
            }
        }

        rootView.f_m_selected_city.setOnClickListener {
            dialogListOfCountries(cities)
        }
    }

    private fun updateSpinner(list: ArrayList<City>){
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

    private fun dialogListOfCountries(dataList: ArrayList<City>) {
        val dialogView = View.inflate(context, R.layout.item_city_list, null)
        val builder = AlertDialog.Builder(context)
        builder.setCancelable(true)
        builder.setView(dialogView)
        alertDialog = builder.create()
        alertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        alertDialog.show()
//        dialogView.dialog_spinner_view_clear.setOnClickListener {
//            dialogView.dialog_spinner_view_serch.setText("")
//        }
        dialogView.i_c_l_search_et.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                result = Observable.fromIterable(dataList).filter { t ->
                    val name =  dialogView.i_c_l_search_et.text.toString()
                    t.name.contains(name.capitalize())
                }.toList().blockingGet() as ArrayList<City>
                mCityAdapter.setList(result)
                mCityAdapter.notifyDataSetChanged()
            }
        })
        val recyclerView = dialogView.findViewById<RecyclerView>(R.id.i_c_l_cities)
        recyclerView.setHasFixedSize(true)
        val mLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = mLayoutManager
        mCityAdapter = CityListAdapter(dataList, this, null, R.layout.fragment_map)
        recyclerView.adapter = mCityAdapter
        recyclerView.isNestedScrollingEnabled = false
    }

    override fun selectedCity(city: City) {
        alertDialog.dismiss()
        rootView.f_m_selected_city.text = city.name
        cache.saveSelectedCity(city)
        viewModel.getStationsByCity(city.pk)?.doOnError{
            Toast.makeText(activity, getString(R.string.net_problem), Toast.LENGTH_LONG).show()
        }?.subscribe{
            if(it.results.size > 0){
                not_found_text.visibility = View.GONE
            }else{
                not_found_text.visibility = View.VISIBLE
            }
            mAdapter.update(it.results)
        }
    }
}