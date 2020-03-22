package kg.koronastaff.staffapp.ui.news

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kg.koronastaff.staffapp.R
import kg.koronastaff.staffapp.adapters.NewsAdapter
import kg.koronastaff.staffapp.models.News
import kg.koronastaff.staffapp.models.Stat
import kg.koronastaff.staffapp.network.NetworkService
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.android.synthetic.main.stat_item.*
import java.util.*

class NewsFragment : Fragment() {
    lateinit var newsViewModel: NewsViewModel
    private var recyclerView: RecyclerView? = null
    private var mAdapter: RecyclerView.Adapter<*>? = null
    private var viewManager: RecyclerView.LayoutManager? = null

    val apiServe by lazy {
        NetworkService().create()
    }

    var disposable: Disposable? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)

        val rootView = inflater.inflate(R.layout.fragment_news, container, false)
        viewManager = LinearLayoutManager(context)

        val list = ArrayList<News>()
        list.add(News(1, "title", "", 1))
        mAdapter = NewsAdapter(list, activity)

        recyclerView = rootView.findViewById<RecyclerView>(R.id.news_list_recycler).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = mAdapter
        }

        updateStat()
        return rootView
    }

    private fun updateStat(){
        disposable =
                apiServe.getStat()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { result -> showResult(result.results) },
                                { error -> showError(error.message) }
                        )
    }
    private fun showResult(s: Stat){
        dead_num.text = s.dead
        infected_num.text = s.infected
        recovered_num.text = s.recovered
    }

    private fun showError(s: String?){

    }
}