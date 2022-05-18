package com.example.androiduidesign.webservice.without_retrofit

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

open class BaseViewModel:ViewModel(){

    fun<T: Any> apiCall(
        jsonObject: JSONObject, url: URL, requestmethod: String, data: Class<T>? = null,
        httpCallback: HTTPCallback){
        viewModelScope.launch(Dispatchers.IO) {
            with(url.openConnection() as HttpURLConnection) {
                requestMethod = requestmethod
                setRequestProperty("Content-Type", "application/json")
                val writer = OutputStreamWriter(outputStream)
                writer.write(jsonObject.toString())
                writer.flush()
                val httpURLConnection = url.openConnection() as HttpURLConnection
                val response = httpURLConnection.inputStream.bufferedReader().use { it.readText() }
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val prettyJson = gson.toJson(JsonParser.parseString(response))
                    val dataObject = gson.fromJson(prettyJson,data)
                    httpCallback.successCallback(responseMessage,dataObject)
                } else {
                    httpCallback.failureCallback(responseCode,responseMessage)
                }
            }
        }
    }
}