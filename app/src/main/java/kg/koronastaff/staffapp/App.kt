package kg.koronastaff.staffapp

import android.app.Application
import java.util.*

class App : Application() {
    private val data = HashMap<String, String>()
    override fun onCreate() {
        super.onCreate()
    }

    fun getData(key: String?): String? {
        return data[key]
    }

    fun putData(key: String, value: String) {
        data[key] = value
    }
}