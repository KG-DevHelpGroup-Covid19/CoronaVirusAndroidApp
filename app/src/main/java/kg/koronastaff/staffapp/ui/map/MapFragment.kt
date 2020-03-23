package kg.koronastaff.staffapp.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kg.koronastaff.staffapp.R
import kg.koronastaff.staffapp.adapters.MapsAdapter
import kg.koronastaff.staffapp.ui.CoronaViewModel
import kg.koronastaff.staffapp.ui.FragmentWithStat

class MapFragment : FragmentWithStat() {
    private var recyclerView: RecyclerView? = null
    private var viewModel: CoronaViewModel? = null
    private lateinit var mAdapter: MapsAdapter
    private var viewManager: RecyclerView.LayoutManager? = null
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {

        super.onCreateView(inflater, container, savedInstanceState)
        viewModel = ViewModelProvider(this).get(CoronaViewModel::class.java)
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
    }
}