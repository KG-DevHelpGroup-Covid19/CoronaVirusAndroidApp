package kg.koronastaff.staffapp.ui.news

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kg.koronastaff.staffapp.R
import kg.koronastaff.staffapp.adapters.NewsAdapter
import kg.koronastaff.staffapp.database.Base
import kg.koronastaff.staffapp.helpers.Const
import kg.koronastaff.staffapp.ui.FragmentWithStat
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.android.synthetic.main.fragment_news.view.*
import java.util.*


class NewsFragment : FragmentWithStat() {
    private var recyclerView: RecyclerView? = null
    private lateinit var mAdapter: NewsAdapter
    private var viewManager: LinearLayoutManager? = null
    private var page: Int = 1
    private var end = false

    @SuppressLint("ShowToast")
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {

        super.onCreateView(inflater, container, savedInstanceState)
        val rootView = inflater.inflate(R.layout.fragment_news, container, false)
        viewManager = object: LinearLayoutManager(context){
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        rootView.nested_scroll.scrollX = 0
        mAdapter = NewsAdapter(ArrayList(), activity!!)
        recyclerView = rootView.findViewById<RecyclerView>(R.id.news_list_recycler).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = mAdapter
        }

        rootView.nested_scroll.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener {
            v, _, scrollY, _, _ ->
            if (scrollY == (v.getChildAt(0).measuredHeight - v.measuredHeight)) {
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

        super.coronaViewModel.getNews(page)?.doOnError{
            Toast.makeText(activity, getString(R.string.net_problem), Toast.LENGTH_LONG)
        }?.subscribe {
            if (it.results!!.size < Const.perPage) {
                end = true
            }
            mAdapter.update(it.results!!)
            rootView.nested_scroll.scrollX = 0
            cache.saveNews(it.results!!)
        }

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        super.updateStats(cache.getStat())
        mAdapter.update(cache.getNews())
        nested_scroll.scrollX = 0
        val b = Base(activity!!)
    }
}

