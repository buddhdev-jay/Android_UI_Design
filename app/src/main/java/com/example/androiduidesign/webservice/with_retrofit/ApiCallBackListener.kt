package com.example.androiduidesign.webservice.with_retrofit

interface ApiCallBackListener {
    fun<T: Any> onSuccess(data: T)
    fun onFailure(error: ErrorResponse)
}