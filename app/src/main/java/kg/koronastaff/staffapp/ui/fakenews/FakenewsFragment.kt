package kg.koronastaff.staffapp.ui.fakenews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kg.koronastaff.staffapp.R
import kg.koronastaff.staffapp.adapters.FakeNewsAdapter
import kg.koronastaff.staffapp.models.FakeNews
import kg.koronastaff.staffapp.ui.FragmentWithStat
import java.util.*

class FakenewsFragment : FragmentWithStat() {
    private var recyclerView: RecyclerView? = null
    private lateinit var mAdapter: FakeNewsAdapter
    private var viewManager: RecyclerView.LayoutManager? = null
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val root = inflater.inflate(R.layout.fragment_fakenews, container, false)
        viewManager = LinearLayoutManager(context)
        mAdapter = FakeNewsAdapter(arrayListOf(), activity!!)
        recyclerView = root.findViewById<RecyclerView>(R.id.fake_news_recycler).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = mAdapter
        }
        mAdapter = FakeNewsAdapter(arrayListOf(), activity!!)

        super.coronaViewModel.getFakeNews()?.subscribe{
            mAdapter.update(it.results!!)
        }

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        super.updateStats(cache.getStat())
    }
}