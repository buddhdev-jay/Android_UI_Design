package com.example.androiduidesign.authentication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignupViewModel : ViewModel() {
    val passwordStatus : MutableLiveData<Int> = MutableLiveData(0)
    val password :MutableLiveData<String> = MutableLiveData()
}