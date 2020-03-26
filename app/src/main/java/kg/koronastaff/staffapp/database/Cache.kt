package kg.koronastaff.staffapp.database

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kg.koronastaff.staffapp.models.*
import java.lang.reflect.Type


class Cache(var context: Context) {
    private var gson: Gson = Gson()
    private val citiesNode = "cities"
    private val prefsNode = "myPrefs"
    private val faqNode = "faq"
    private val statNode = "stat"
    private val newsNoe = "news"
    private val selectedCityNode = "city"
    private val questionsNode = "questions"
    private val contactsNode = "contacts"
    private val tipsNode = "tips"
    private val stepsNode = "steps"

    @SuppressLint("CommitPrefEdits")
    fun saveQuestions(list: ArrayList<TestQuestion>){
        val myPrefs = context.getSharedPreferences(prefsNode, MODE_PRIVATE).edit()
        myPrefs.putString(questionsNode, gson.toJson(list))
        myPrefs.apply()
    }
    fun getQuestions(): ArrayList<TestQuestion>{
        val myPrefs = context.getSharedPreferences(prefsNode, MODE_PRIVATE)
        val listType: Type = object : TypeToken<ArrayList<TestQuestion>>() {}.type
        val listJson = myPrefs.getString(questionsNode, "")
        if (listJson == ""){
            return arrayListOf()
        }
        return gson.fromJson(listJson, listType)
    }

    fun getTips(): ArrayList<Tips>{
        val myPrefs = context.getSharedPreferences(prefsNode, MODE_PRIVATE)
        val listType: Type = object : TypeToken<ArrayList<Tips>>() {}.type
        val listJson = myPrefs.getString(tipsNode, "")
        if (listJson == ""){
            return arrayListOf()
        }
        return gson.fromJson(listJson, listType)
    }

    @SuppressLint("CommitPrefEdits")
    fun saveTips(list: ArrayList<Tips>){
        val myPrefs = context.getSharedPreferences(prefsNode, MODE_PRIVATE).edit()
        myPrefs.putString(tipsNode, gson.toJson(list))
        myPrefs.apply()
    }

    fun getSteps(): ArrayList<QuarantineSteps>{
        val myPrefs = context.getSharedPreferences(prefsNode, MODE_PRIVATE)
        val listType: Type = object : TypeToken<ArrayList<QuarantineSteps>>() {}.type
        val listJson = myPrefs.getString(stepsNode, "")
        if (listJson == ""){
            return arrayListOf()
        }
        return gson.fromJson(listJson, listType)
    }

    @SuppressLint("CommitPrefEdits")
    fun saveSteps(list: ArrayList<QuarantineSteps>){
        val myPrefs = context.getSharedPreferences(prefsNode, MODE_PRIVATE).edit()
        myPrefs.putString(stepsNode, gson.toJson(list))
        myPrefs.apply()
    }


    fun saveSelectedCity(city: City){
        val myPrefs = context.getSharedPreferences(prefsNode, MODE_PRIVATE).edit()
        myPrefs.putString(selectedCityNode, gson.toJson(city))
        myPrefs.apply()
    }

    fun getSelectedCity(): City{
        val myPrefs = context.getSharedPreferences(prefsNode, MODE_PRIVATE)
        val listJson = myPrefs.getString(selectedCityNode, "")
        if (listJson == ""){
            return City("0", 0, City.CityRegion("",1))
        }
        return gson.fromJson(listJson, City::class.java)
    }

    @SuppressLint("CommitPrefEdits")
    fun saveCities(list: ArrayList<City>){
        val myPrefs = context.getSharedPreferences(prefsNode, MODE_PRIVATE).edit()
        myPrefs.putString(citiesNode, gson.toJson(list))
        myPrefs.apply()
    }

    fun getCities(): ArrayList<City>{
        val myPrefs = context.getSharedPreferences(prefsNode, MODE_PRIVATE)
        val listType: Type = object : TypeToken<ArrayList<City>>() {}.type
        val listJson = myPrefs.getString(citiesNode, "")
        if (listJson == ""){
            return arrayListOf()
        }
        return gson.fromJson(listJson, listType)

    }

    @SuppressLint("CommitPrefEdits")
    fun saveFaq(list: ArrayList<FAQ>){
        val myPrefs = context.getSharedPreferences(prefsNode, MODE_PRIVATE).edit()
        myPrefs.putString(faqNode, gson.toJson(list))
        myPrefs.apply()
    }

    fun getFaq(): ArrayList<FAQ>{
        val myPrefs = context.getSharedPreferences(prefsNode, MODE_PRIVATE)
        val listType: Type = object : TypeToken<ArrayList<FAQ>>() {}.type
        val listJson = myPrefs.getString(faqNode, "")
        if (listJson == ""){
            return arrayListOf()
        }
        return gson.fromJson(listJson, listType)
    }

    @SuppressLint("CommitPrefEdits")
    fun saveNews(list: ArrayList<News>){
        val myPrefs = context.getSharedPreferences(prefsNode, MODE_PRIVATE).edit()
        myPrefs.putString(newsNoe, gson.toJson(list))
        myPrefs.apply()
    }

    fun getNews(): ArrayList<News>{
        val myPrefs = context.getSharedPreferences(prefsNode, MODE_PRIVATE)
        val listType: Type = object : TypeToken<ArrayList<News>>() {}.type
        val listJson = myPrefs.getString(newsNoe, "")
        if (listJson == ""){
            return arrayListOf()
        }
        return gson.fromJson(listJson, listType)
    }

    @SuppressLint("CommitPrefEdits")
    fun saveContacts(list: ArrayList<Contacts>){
        val myPrefs = context.getSharedPreferences(prefsNode, MODE_PRIVATE).edit()
        myPrefs.putString(contactsNode, gson.toJson(list))
        myPrefs.apply()
    }

    fun getContacts(): ArrayList<Contacts>{
        val myPrefs = context.getSharedPreferences(prefsNode, MODE_PRIVATE)
        val listType: Type = object : TypeToken<ArrayList<Contacts>>() {}.type
        val listJson = myPrefs.getString(contactsNode, "")
        if (listJson == ""){
            return arrayListOf()
        }
        return gson.fromJson(listJson, listType)
    }

    fun addNews(data: ArrayList<News>){
        val arr = getNews()
        arr.addAll(data)
        saveNews(arr)
    }

    fun saveStat(stat: Stat){
        val myPrefs = context.getSharedPreferences(prefsNode, MODE_PRIVATE).edit()
        myPrefs.putString(statNode, gson.toJson(stat))
        myPrefs.apply()
    }

    fun getStat(): Stat{
        val myPrefs = context.getSharedPreferences(prefsNode, MODE_PRIVATE)
        val listJson = myPrefs.getString(statNode, "")
        if (listJson == ""){
            return Stat("0", "0","0")
        }
        return gson.fromJson(listJson, Stat::class.java)
    }

}