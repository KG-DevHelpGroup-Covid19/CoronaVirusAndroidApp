package kg.koronastaff.staffapp.ui.tests

import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kg.koronastaff.staffapp.models.*
import kg.koronastaff.staffapp.network.NetworkService

class TestsViewModel : ViewModel() {
    var parsedCities: HashMap<String, ArrayList<City>> = HashMap()
    var parsedRegions: ArrayList<String> = arrayListOf()

    private val apiServe by lazy {
        NetworkService().create()
    }

    fun getToken(): Observable<Token> {
        return apiServe.getUniqueToken()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun postPollChoice(c: PollChoice): Observable<PollChoice>? {
        return apiServe.postPollChoice(c).subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())
    }

    fun postTestResult(c: TestResults, token: String): Observable<TestResults>? {
        return apiServe.putUserData(c, token).subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())
    }

    fun getRegions(): Observable<ApiResponse<ArrayList<Region>>>? {
        return apiServe.getRegions()?.subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())
    }

    fun getCities(): Observable<ApiResponse<ArrayList<City>>>? {
        return apiServe.getCities()?.subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())
    }

    fun parseCities(cities: ArrayList<City>) {
        cities.forEach {
            var list = parsedCities[it.region.name]
            if (list == null){
                list = arrayListOf(it)
                parsedCities[it.region.name] = list
            }else{
                parsedCities[it.region.name]!!.add(it)
            }
        }
        val keys = parsedCities.filterValues { true }.keys
        parsedRegions.addAll(keys)
    }

    fun getQuestions(): Observable<ApiResponse<ArrayList<TestQuestion>>>? {
        return apiServe.getTests().subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())
    }
}