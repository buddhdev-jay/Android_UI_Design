package com.example.androiduidesign.webservice.without_retrofit

interface HTTPCallback {
    fun successCallback(output:String)
    fun failerCallback(responseCode:Int,output:String)
}