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
import java.util.*

class TipsFragment : Fragment() {

    private lateinit var recyclerViewTips: RecyclerView
    private var mAdapter: RecyclerView.Adapter<*>? = null
    private var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.fragment_tips, container, false)

        recyclerViewTips = rootView.findViewById(R.id.tips_recycler)
        layoutManager = LinearLayoutManager(context)
        recyclerViewTips.setLayoutManager(layoutManager)

        val list = ArrayList<Tips>()
        list.add(Tips("Tip 1", "body", ""))
        list.add(Tips("Tip 2", "body", ""))

        mAdapter = TipsAdapter(list)
        recyclerViewTips.setAdapter(mAdapter)
        return rootView
    }
}