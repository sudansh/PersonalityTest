package com.sudansh.personalize.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import io.realm.Realm
import org.json.JSONObject
import java.io.InputStream

class MainViewModel(private val app: Application) : AndroidViewModel(app) {
    val realm: Realm = Realm.getDefaultInstance()
    val questionData = MutableLiveData<QuestionData>()

    fun getValues() {
        val localData = fetchFromDB()
        if (localData == null) {
            val obj = readJSONFromAsset()
            if (obj != null) {
                realm.executeTransaction {
                    it.createOrUpdateObjectFromJson(QuestionData::class.java, obj)
                    questionData.postValue(fetchFromDB())
                }
            }
        } else {
            questionData.postValue(localData)
        }
    }

    private fun readJSONFromAsset(): JSONObject? {
        return try {
            val inputStream: InputStream? = app.assets?.open("personality.json")
            val json = inputStream?.bufferedReader().use { it?.readText() }
            val obj = JSONObject(json)
            obj
        } catch (ex: Exception) {
            ex.printStackTrace()
            null
        }
    }

    private fun fetchFromDB(): QuestionData? {
        return realm.where(QuestionData::class.java).findFirst()
    }
}
