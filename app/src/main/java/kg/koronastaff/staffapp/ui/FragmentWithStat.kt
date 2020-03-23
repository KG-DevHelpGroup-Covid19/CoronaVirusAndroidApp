package kg.koronastaff.staffapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kg.koronastaff.staffapp.R
import kg.koronastaff.staffapp.database.Cache
import kg.koronastaff.staffapp.models.Stat
import kotlinx.android.synthetic.main.stat_item.*


open class FragmentWithStat : Fragment(){
    lateinit var coronaViewModel: CoronaViewModel
    open lateinit var cache: Cache
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        coronaViewModel = ViewModelProvider(this).get(CoronaViewModel::class.java)
        cache = Cache(activity!!)
        updateStat()
        return null
    }

    private fun updateStat(){
        coronaViewModel.getStat()
                .doOnError{
                    showError("");
                }
                .subscribe(
                { result -> updateStats(result.results[0]); cache.saveStat(result.results[0]) },
                { error -> showError(error.message) }
        )
    }

    @SuppressLint("ShowToast")
    private fun showError(s: String?){
        Toast.makeText(activity, ""+getString(R.string.net_problem), Toast.LENGTH_SHORT)
    }

    fun updateStats(s: Stat){
        dead_num.text = s.died
        infected_num.text = s.infected
        recovered_num.text = s.recovered
    }
}