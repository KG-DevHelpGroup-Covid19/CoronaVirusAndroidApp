package kg.koronastaff.staffapp.ui.article

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kg.koronastaff.staffapp.App
import kg.koronastaff.staffapp.R
import kg.koronastaff.staffapp.models.News
import kg.koronastaff.staffapp.ui.FragmentWithStat
import kotlinx.android.synthetic.main.article_fragment.*
import java.util.*

class ArticleFragment : FragmentWithStat() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val root = inflater.inflate(R.layout.article_fragment, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        super.updateStats(cache.getStat())
        val app = activity!!.application as App
        val news = app.getData("article") as News

        article_label.text = news.title
        news_time.text = news.created_at
        news_body.text = news.body
        article_link.text = news.source_news
        imageView.setOnClickListener { activity!!.onBackPressed() }
        article_label.setOnClickListener{activity!!.onBackPressed()}
        back_button.setOnClickListener{activity!!.onBackPressed()}
    }
}