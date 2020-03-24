package kg.koronastaff.staffapp.network

import io.reactivex.Observable
import kg.koronastaff.staffapp.models.*
import retrofit2.http.*


interface BackEndService {

    @GET("api/tips")
    fun getTips(): Observable<ApiResponse<ArrayList<Tips>?>>?

    @GET("api/news")
    fun getNews(@Query("offset") page: Int, @Query("limit") limit: Int = 20, @Query("fake") fake: Boolean = false): Observable<ApiResponse<ArrayList<News>?>>?

    @GET("api/faq")
    fun getFAQ(): Observable<ApiResponse<ArrayList<FAQ>?>>?

    @GET("api/steps")
    fun getQuarantineSteps(): Observable<ApiResponse<ArrayList<QuarantineSteps>?>>?

    @GET("api/stat")
    fun getStat(): Observable<ApiResponse<ArrayList<Stat>>>

    @GET("api/location/city")
    fun getCities(@Query("limit") limit: Int = 500): Observable<ApiResponse<ArrayList<City>>>?

    @GET("api/location/region")
    fun getRegions(): Observable<ApiResponse<ArrayList<Region>>>?

    @GET("api/polls/")
    fun getTests(): Observable<ApiResponse<ArrayList<TestQuestion>>>

    @POST("api/test")
    fun sendTestResults(@Body results: TestResults): Observable<ApiResponse<ApiStatus>>

    @GET("api/location/city/{city}/stations")
    fun getStationsByCityId(@Path("city") cityId: Int):
            Observable<ApiResponse<ArrayList<StationMap>>>?

    @GET("api/polls/current_user/")
    fun getUniqueToken(): Observable<Token>

}