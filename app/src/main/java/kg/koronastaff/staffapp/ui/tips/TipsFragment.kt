package kg.koronastaff.staffapp.ui.tips

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kg.koronastaff.staffapp.R
import kg.koronastaff.staffapp.adapters.TipsAdapter
import kg.koronastaff.staffapp.models.Tips
import kg.koronastaff.staffapp.ui.FragmentWithStat
import java.util.*

class TipsFragment : FragmentWithStat() {

    private lateinit var recyclerViewTips: RecyclerView
    private lateinit var mAdapter: TipsAdapter
    private var viewManager: RecyclerView.LayoutManager? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {

        super.onCreateView(inflater, container, savedInstanceState)
        val rootView = inflater.inflate(R.layout.fragment_tips, container, false)

        viewManager = LinearLayoutManager(context) as RecyclerView.LayoutManager?

        mAdapter = TipsAdapter(arrayListOf(), activity!!)

        recyclerViewTips = rootView.findViewById<RecyclerView>(R.id.tips_recycler).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = mAdapter
        }

        super.coronaViewModel.getTips()?.subscribe{
            mAdapter.update(it.results!!)
        }

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        super.updateStats(cache.getStat())
    }


}