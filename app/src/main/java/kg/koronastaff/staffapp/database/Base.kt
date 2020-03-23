package kg.koronastaff.staffapp.database

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.google.gson.Gson
import kg.koronastaff.staffapp.models.City


class Base(var context: Context) {
    private var gson: Gson = Gson()
    private val tokenNode = "token"
    private val prefsNode = "base"

    fun saveToken(token: String){
        val myPrefs = context.getSharedPreferences(prefsNode, MODE_PRIVATE).edit()
        myPrefs.putString(tokenNode, token)
        myPrefs.apply()
    }

    fun getToken(): String? {
        val myPrefs = context.getSharedPreferences(prefsNode, MODE_PRIVATE)
        return myPrefs.getString(tokenNode, "")
    }

}