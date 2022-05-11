package com.example.androiduidesign.authencation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignupViewModel : ViewModel() {

    val passwordStatus : MutableLiveData<Int> = MutableLiveData(0)


}