package com.example.androiduidesign.authencation

import androidx.lifecycle.MutableLiveData
import com.example.androiduidesign.webservice.without_retrofit.BaseViewModel

class SignupViewModel : BaseViewModel() {
    val passwordStatus : MutableLiveData<Int> = MutableLiveData(0)
    val password :MutableLiveData<String> = MutableLiveData()


}