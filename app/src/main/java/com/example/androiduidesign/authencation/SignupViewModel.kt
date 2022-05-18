package com.example.androiduidesign.authencation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androiduidesign.webservice.without_retrofit.HttpCallViewModel

class SignupViewModel : HttpCallViewModel() {
    val passwordStatus : MutableLiveData<Int> = MutableLiveData(0)
    val password :MutableLiveData<String> = MutableLiveData()


}