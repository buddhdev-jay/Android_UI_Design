package com.example.androiduidesign.authencation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ForgetPasswordViewModel:ViewModel() {

    val emailstatus : MutableLiveData<Boolean> = MutableLiveData(true)
    val passwordStatus: MutableLiveData<Boolean> = MutableLiveData()

    fun updatePassword(isEmailCheckd: Boolean){
        emailstatus.value = isEmailCheckd
        passwordStatus.value = !isEmailCheckd
    }
}