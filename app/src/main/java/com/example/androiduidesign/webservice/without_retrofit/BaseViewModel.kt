package com.example.androiduidesign.webservice.without_retrofit

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiduidesign.utils.ApiInterface
import com.example.androiduidesign.utils.RetrofitObject
import com.example.androiduidesign.webservice.with_retrofit.ApiCallBackListener
import com.example.androiduidesign.webservice.with_retrofit.ErrorResponse
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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class BaseViewModel():ViewModel(){

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



    fun<T: Any> retrofitCall(retrofitCall: Call<T>, apiCallBackListener: ApiCallBackListener) {
        retrofitCall.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                response.body()?.let { responseBody ->
                    apiCallBackListener.onSuccess(responseBody)
                }
                response.errorBody()?.let {
                    val error = JSONObject(it.charStream().readText())
                    apiCallBackListener.onFailure(ErrorResponse(error.getString("error")))
                }
            }
            override fun onFailure(call: Call<T>, t: Throwable) {
                apiCallBackListener.onFailure(ErrorResponse(t.toString()))
            }
        })
    }
}