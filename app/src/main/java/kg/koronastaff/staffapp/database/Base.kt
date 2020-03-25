package kg.koronastaff.staffapp.database

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kg.koronastaff.staffapp.models.City
import kg.koronastaff.staffapp.models.PollChoice
import kg.koronastaff.staffapp.models.TestQuestion
import kg.koronastaff.staffapp.models.TestResults
import java.lang.reflect.Type


class Base(var context: Context) {
    private var gson: Gson = Gson()
    private val tokenNode = "token"
    private val prefsNode = "base"
    private val pollChoiceNode = "pollchoice"

    fun saveToken(token: String){
        val myPrefs = context.getSharedPreferences(prefsNode, MODE_PRIVATE).edit()
        myPrefs.putString(tokenNode, token)
        myPrefs.apply()
    }

    fun getToken(): String? {
        val myPrefs = context.getSharedPreferences(prefsNode, MODE_PRIVATE)
        return myPrefs.getString(tokenNode, "")
    }

    @SuppressLint("CommitPrefEdits")
    fun saveChoices(list: ArrayList<PollChoice>){
        val myPrefs = context.getSharedPreferences(prefsNode, MODE_PRIVATE).edit()
        myPrefs.putString(pollChoiceNode, gson.toJson(list))
        myPrefs.apply()
    }

    fun getChoices(): ArrayList<PollChoice>{
        val myPrefs = context.getSharedPreferences(prefsNode, MODE_PRIVATE)
        val listType: Type = object : TypeToken<ArrayList<PollChoice>>() {}.type
        val listJson = myPrefs.getString(pollChoiceNode, "")
        if (listJson == ""){
            return arrayListOf()
        }
        return gson.fromJson(listJson, listType)
    }

    fun addPollChoice(choice: PollChoice){
        val l  = getChoices()
        l.add(choice)
        saveChoices(l)
    }

    @SuppressLint("CommitPrefEdits")
    fun saveResults(list: TestResults){
        val myPrefs = context.getSharedPreferences(prefsNode, MODE_PRIVATE).edit()
        myPrefs.putString(pollChoiceNode, gson.toJson(list))
        myPrefs.apply()
    }

    fun getResults(): TestResults?{
        val myPrefs = context.getSharedPreferences(prefsNode, MODE_PRIVATE)
        val listType: Type = object : TypeToken<TestResults>() {}.type
        val listJson = myPrefs.getString(pollChoiceNode, "")
        if (listJson == ""){
            return null
        }
        return gson.fromJson(listJson, listType)
    }

}