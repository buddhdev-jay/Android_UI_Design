package com.example.androiduidesign.authentication

import android.app.Application
import android.util.Patterns
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androiduidesign.R

class SignupViewModel(application: Application) : AndroidViewModel(application) {
    val passwordStatus : MutableLiveData<Int> = MutableLiveData(0)
    val password :MutableLiveData<String> = MutableLiveData()
    val cpassword : MutableLiveData<String> = MutableLiveData()
    val logInResult = MutableLiveData<String>()
    fun getLogInResult(): LiveData<String> = logInResult
    var email: MutableLiveData<String> = MutableLiveData()
    var name: MutableLiveData<String> = MutableLiveData()
    var mobileNo: MutableLiveData<String> = MutableLiveData()

    fun performValidation() {
        if (email.value.isNullOrEmpty()) {
            logInResult.value = getApplication<Application>().resources.getString(R.string.toast_email_empty)
            return
        } else if (mobileNo.value.isNullOrEmpty()) {
            logInResult.value = getApplication<Application>().resources.getString(R.string.toast_phone_empty)
            return
        } else if (name.value.isNullOrEmpty()) {
            logInResult.value = getApplication<Application>().resources.getString(R.string.toast_name_empty)
            return
        } else if (mobileNo.value?.length ?: 10 < 10) {
            logInResult.value = getApplication<Application>().resources.getString(R.string.toast_valid_phone)
            return
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.value).matches()) {
            logInResult.value = getApplication<Application>().resources.getString(R.string.toast_email_not_valid)
            return
        } else if (password.value.isNullOrEmpty()) {
            logInResult.value = getApplication<Application>().resources.getString(R.string.toast_password_empty)
        }else if(cpassword.value.isNullOrEmpty()) {
            logInResult.value = getApplication<Application>().resources.getString(R.string.toast_confirm_password)
        } else if (passwordStatus.value != 4){
           logInResult.value = getApplication<Application>().resources.getString(R.string.password_should_strong)
        } else {
            logInResult.value = getApplication<Application>().resources.getString(R.string.signup_btn_clicked)
        }
    }
}