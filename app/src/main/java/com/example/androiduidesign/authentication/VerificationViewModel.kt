package com.example.androiduidesign.authentication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class VerificationViewModel : ViewModel() {
    val editTextOne : MutableLiveData<String> = MutableLiveData()
    val editTextTwo : MutableLiveData<String> = MutableLiveData()
    val editTextThree : MutableLiveData<String> = MutableLiveData()
    val editTextFour : MutableLiveData<String> = MutableLiveData()
}