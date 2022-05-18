package com.example.androiduidesign.webservice.without_retrofit

interface HTTPCallback {
    fun<T:Any> successCallback(output:String , dataClass:T? = null)
    fun failureCallback(responseCode:Int,output:String)
}