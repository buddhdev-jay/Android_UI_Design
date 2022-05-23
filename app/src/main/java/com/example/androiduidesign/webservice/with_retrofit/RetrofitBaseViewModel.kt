package com.example.androiduidesign.webservice.with_retrofit

import androidx.lifecycle.ViewModel
import com.example.androiduidesign.webservice.without_retrofit.HTTPCallback
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class RetrofitBaseViewModel :ViewModel() {

    fun<T: Any> call(retrofitCall: Call<T>, apiCallBackListener: ApiCallBackListener) {
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