package kg.koronastaff.staffapp.ui.quarantine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kg.koronastaff.staffapp.R
import kg.koronastaff.staffapp.adapters.QuarantineAdapter
import kg.koronastaff.staffapp.models.QuarantineSteps
import kg.koronastaff.staffapp.ui.FragmentWithStat
import java.util.*

class QuarantineFragment : FragmentWithStat() {
    private var recyclerView: RecyclerView? = null
    private var mAdapter: RecyclerView.Adapter<*>? = null
    private var viewManager: RecyclerView.LayoutManager? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val root = inflater.inflate(R.layout.fragment_quarantine, container, false)
        recyclerView = root.findViewById(R.id.fake_news_recycler)
        viewManager = LinearLayoutManager(context)

        mAdapter = QuarantineAdapter(arrayListOf())

        recyclerView = root.findViewById<RecyclerView>(R.id.fake_news_recycler).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = mAdapter
        }
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        super.updateStats(cache.getStat())
    }
}