package kg.koronastaff.staffapp.network

import io.reactivex.Observable
import kg.koronastaff.staffapp.models.*
import retrofit2.http.*


interface BackEndService {

    @GET("api/tips")
    fun getTips(): Observable<ApiResponse<ArrayList<Tips>?>>?

    @GET("api/news")
    fun getNews(): Observable<ApiResponse<ArrayList<News>?>>?

    @GET("api/news")
    fun getFakeNews(@Query("fake") boolean: Boolean = true):
            Observable<ApiResponse<ArrayList<FakeNews>?>>?

    @GET("api/faq")
    fun getFAQ(): Observable<ApiResponse<ArrayList<FAQ>?>>?

    @GET("api/steps")
    fun getQuarantineSteps(): Observable<ApiResponse<ArrayList<QuarantineSteps>?>>?

    @GET("api/stat")
    fun getStat(): Observable<ApiResponse<ArrayList<Stat>>>

    @GET("api/location/city")
    fun getCities(): Observable<ApiResponse<ArrayList<City>>>?

    @GET("api/location/region")
    fun getRegions(): Observable<ApiResponse<ArrayList<Region>>>?

    @GET("api/test")
    fun getTests(): Observable<ApiResponse<ArrayList<String>>>?

    @POST("api/test")
    fun sendTestResults(@Body results: TestResults): Observable<ApiResponse<ApiStatus>>

    @GET("api/city/{city}/stations")
    fun getStationsByCityId(@Path("city") cityId: Int):
            Observable<ApiResponse<ArrayList<StationMap>>>?

}