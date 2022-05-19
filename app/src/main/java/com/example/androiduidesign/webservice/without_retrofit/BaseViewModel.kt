package com.example.androiduidesign.webservice.without_retrofit

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject

open class BaseViewModel(application: Application):AndroidViewModel(application){

    fun<T> apiCall(jsonObject: JSONObject, url: URL, requestmethod: String, data: Class<T>,
        httpCallback: HTTPCallback){
        viewModelScope.launch(Dispatchers.IO) {
            val httpURLConnection = url.openConnection() as HttpURLConnection
            with(httpURLConnection) {
                requestMethod = requestmethod
                setRequestProperty("Content-Type", "application/json")
                val writer = OutputStreamWriter(outputStream)
                writer.write(jsonObject.toString())
                writer.flush()
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    val response = httpURLConnection.inputStream.bufferedReader().use { it.readText() }
                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val dataObject = gson.fromJson(response,data)
                    httpCallback.successCallback(responseMessage,dataObject)
                } else {
                    httpCallback.failureCallback(responseCode,responseMessage)
                }
            }
        }
    }
}