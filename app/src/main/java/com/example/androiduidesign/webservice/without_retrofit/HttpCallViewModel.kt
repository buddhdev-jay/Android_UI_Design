package com.example.androiduidesign.webservice.without_retrofit

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject

open class HttpCallViewModel:ViewModel(){

    fun apiCall(jsonObject: JSONObject, url: URL, requestmethod: String, httpCallback: HTTPCallback){
        viewModelScope.launch(Dispatchers.IO) {
            with(url.openConnection() as HttpURLConnection) {
                requestMethod = requestmethod
                val cred = jsonObject
                setRequestProperty("Content-Type", "application/json")
                val writer = OutputStreamWriter(outputStream)
                writer.write(cred.toString())
                writer.flush()
                println("URL : $url")
                println("cred: $cred")
                println("Response Code: $responseCode")
                if (responseCode == HttpURLConnection.HTTP_OK) {
                  httpCallback.successCallback(responseMessage)
                } else {
                    httpCallback.failerCallback(responseCode,responseMessage)
                }
                BufferedReader(InputStreamReader(inputStream)).use {
                    val response = StringBuffer()
                    var inputLine = it.readLine()
                    while (inputLine != null) {
                        response.append(inputLine)
                        inputLine = it.readLine()
                    }
                    println("Response : $response")
                }
            }
        }
    }
}