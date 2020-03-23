package kg.koronastaff.staffapp.ui

import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kg.koronastaff.staffapp.models.*
import kg.koronastaff.staffapp.network.NetworkService

class CoronaViewModel : ViewModel(){
    private val apiServe by lazy {
        NetworkService().create()
    }

    

    fun getStat(): Observable<ApiResponse<ArrayList<Stat>>> {
        return apiServe.getStat()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun getNews(): Observable<ApiResponse<ArrayList<News>?>>? {
        return apiServe.getNews()?.subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())
    }

    fun getFakeNews(): Observable<ApiResponse<ArrayList<FakeNews>?>>? {
        return apiServe.getFakeNews()?.subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())
    }

    fun getTips(): Observable<ApiResponse<ArrayList<Tips>?>>? {
        return apiServe.getTips()?.subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())
    }

    fun getQuarantineSteps(): Observable<ApiResponse<ArrayList<QuarantineSteps>?>>? {
        return apiServe.getQuarantineSteps()?.subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())
    }

    fun getFAQ(): Observable<ApiResponse<ArrayList<FAQ>?>>? {
        return apiServe.getFAQ()?.subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())
    }

    fun getCities(): Observable<ApiResponse<ArrayList<City>>>? {
        return apiServe.getCities()?.subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())
    }

    fun getTests(): Observable<ApiResponse<ArrayList<String>>>? {
        return apiServe.getTests()?.subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())
    }

    fun getStationsByCity(id: Int): Observable<ApiResponse<ArrayList<StationMap>>>? {
        return apiServe.getStationsByCityId(id)?.subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())
    }
}