package kg.koronastaff.staffapp.ui.fakenews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kg.koronastaff.staffapp.R
import kg.koronastaff.staffapp.adapters.NewsAdapter
import kg.koronastaff.staffapp.helpers.Const
import kg.koronastaff.staffapp.ui.FragmentWithStat
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.android.synthetic.main.fragment_news.view.nested_scroll

class FakenewsFragment : FragmentWithStat() {

    private var recyclerView: RecyclerView? = null
    private lateinit var mAdapter: NewsAdapter
    private var viewManager: RecyclerView.LayoutManager? = null
    private var page = 1
    private var end = false

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val root = inflater.inflate(R.layout.fragment_fakenews, container, false)

        viewManager = LinearLayoutManager(context)

        mAdapter = NewsAdapter(arrayListOf(), activity!!)

        recyclerView = root.findViewById<RecyclerView>(R.id.fake_news_recycler).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = mAdapter
        }

        root.nested_scroll.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener {
            v, _, scrollY, _, _ ->
            if (scrollY == (v.getChildAt(0).measuredHeight - v.measuredHeight) - 10) {
                if (!end) {
                    progressBar.visibility = View.VISIBLE
                    coronaViewModel.getNews(page++)?.subscribe {
                        mAdapter.add(it.results!!)
                        if (it.results!!.size < Const.perPage) {
                            end = true
                        }
                        progressBar.visibility = View.GONE
                        Thread {
                            cache.addNews(it.results!!)
                        }
                    }
                }
            }
        })

        super.coronaViewModel.getFakeNews(page)?.subscribe{
            if (it.results!!.size < 20) {
                end = true
            }
            mAdapter.update(it.results!!)
        }

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        super.updateStats(cache.getStat())
    }
}