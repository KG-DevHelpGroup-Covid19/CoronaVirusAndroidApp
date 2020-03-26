package kg.koronastaff.staffapp.ui.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.disposables.Disposable
import kg.koronastaff.staffapp.R
import kg.koronastaff.staffapp.adapters.ContactsAdapter
import kg.koronastaff.staffapp.ui.FragmentWithStat
import java.util.*

class ContactsFragment : FragmentWithStat() {
    private var recyclerView: RecyclerView? = null
    private lateinit var mAdapter: ContactsAdapter
    private var viewManager: LinearLayoutManager? = null
    var d : Disposable? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val rootView = inflater.inflate(R.layout.fragment_cotacts, container, false)

        viewManager = object : LinearLayoutManager(context) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        mAdapter = ContactsAdapter(ArrayList(), activity!!)
        recyclerView = rootView.findViewById<RecyclerView>(R.id.contacts_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = mAdapter
        }

        d = coronaViewModel.getContacts()?.subscribe {
            mAdapter.update(it.results)
            cache.saveContacts(it.results)
            d?.dispose()
        }

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        super.updateStats(cache.getStat())
        mAdapter.update(cache.getContacts())
    }
}