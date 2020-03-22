package kg.koronastaff.staffapp.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkService {

    val apiServe by lazy {
        create()
    }

    private fun create(): BackEndService {
        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                        RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                        GsonConverterFactory.create())
                .baseUrl("https://www.googleapis.com")
                .build()

        return retrofit.create(BackEndService::class.java)
    }
}