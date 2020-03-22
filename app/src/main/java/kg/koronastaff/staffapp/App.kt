package kg.koronastaff.staffapp

import android.annotation.SuppressLint
import android.app.Application
import android.widget.Toast
import io.reactivex.plugins.RxJavaPlugins
import java.util.*


class App : Application() {
    private val data = HashMap<String, String>()
    @SuppressLint("ShowToast")
    override fun onCreate() {
        super.onCreate()
        RxJavaPlugins.setErrorHandler {
            Toast.makeText(baseContext, getString(R.string.net_problem), Toast.LENGTH_LONG)
        }
    }

    fun getData(key: String?): String? {
        return data[key]
    }

    fun putData(key: String, value: String) {
        data[key] = value
    }
}