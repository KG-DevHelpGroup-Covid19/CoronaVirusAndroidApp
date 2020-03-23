package kg.koronastaff.staffapp.ui.tests

import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kg.koronastaff.staffapp.models.Token
import kg.koronastaff.staffapp.network.NetworkService

class TestsViewModel : ViewModel() {
    private val apiServe by lazy {
        NetworkService().create()
    }

    fun getToken(): Observable<Token> {
        return  apiServe.getUniqueToken()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}