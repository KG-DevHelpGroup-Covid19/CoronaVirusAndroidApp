package kg.koronastaff.staffapp

import android.annotation.SuppressLint
import android.app.Application
import android.widget.Toast
import io.reactivex.plugins.RxJavaPlugins
import java.util.*


class App : Application() {
    private val data = HashMap<String, Any>()
    @SuppressLint("ShowToast")
    override fun onCreate() {
        super.onCreate()
        RxJavaPlugins.setErrorHandler {}
    }

    fun getData(key: String?): Any? {
        return data[key]
    }

    fun putData(key: String, value: Any) {
        data[key] = value
    }
}