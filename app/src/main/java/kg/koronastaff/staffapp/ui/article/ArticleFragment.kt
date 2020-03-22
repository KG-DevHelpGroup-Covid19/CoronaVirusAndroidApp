package kg.koronastaff.staffapp.ui.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kg.koronastaff.staffapp.App
import kg.koronastaff.staffapp.R
import java.util.*

class ArticleFragment : Fragment() {
    private val articleId = 0
    var articleTitle: TextView? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.article_fragment, container, false)
        articleTitle = root.findViewById(R.id.article_label)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val app = activity!!.application as App
        articleTitle!!.text = app.getData("article")
        articleTitle!!.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.menu_news))
    }

}