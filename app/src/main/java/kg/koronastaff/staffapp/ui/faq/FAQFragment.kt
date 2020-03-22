package kg.koronastaff.staffapp.ui.faq

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kg.koronastaff.staffapp.R
import kg.koronastaff.staffapp.adapters.FAQAdapter
import kg.koronastaff.staffapp.models.FAQ
import kg.koronastaff.staffapp.ui.FragmentWithStat
import kotlinx.android.synthetic.main.fragment_faq.*
import kotlinx.android.synthetic.main.fragment_faq.view.*
import java.util.*

class FAQFragment : FragmentWithStat() {
    private var recyclerView: RecyclerView? = null
    private lateinit var mAdapter: FAQAdapter
    private var viewManager: RecyclerView.LayoutManager? = null
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val root = inflater.inflate(R.layout.fragment_faq, container, false)
        viewManager = LinearLayoutManager(context)
        mAdapter = FAQAdapter(arrayListOf())

        recyclerView = root.findViewById<RecyclerView>(R.id.faq_news_recycler).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = mAdapter
        }

        super.coronaViewModel.getFAQ()?.subscribe{
            mAdapter.update(it.results!!)
        }

        root.go_to_test_action.setOnClickListener {
            view -> Navigation.findNavController(view!!).navigate(R.id.tests_fragment) }

        return root
    }
}