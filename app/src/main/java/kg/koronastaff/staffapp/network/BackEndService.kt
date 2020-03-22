package kg.koronastaff.staffapp.network

import io.reactivex.Observable
import kg.koronastaff.staffapp.models.*
import retrofit2.http.GET
import retrofit2.http.Query


interface BackEndService {
    @GET("api/tips")
    fun getTips(): Observable<ArrayList<Tips>?>?

    @GET("api/news")
    fun getNews(): Observable<ArrayList<News>?>?

    @GET("api/news")
    fun getFakeNews(@Query("fake") boolean: Boolean = true): Observable<ArrayList<FakeNews>?>?

    @GET("api/faq")
    fun getFAQ(): Observable<ArrayList<FAQ>?>?

    @GET("api/steps")
    fun getQuarantineSteps(): Observable<ArrayList<QuarantineSteps>?>?


}