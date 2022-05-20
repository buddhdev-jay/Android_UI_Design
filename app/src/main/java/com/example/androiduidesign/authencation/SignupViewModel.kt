package com.example.androiduidesign.authencation

import android.app.Application
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import com.example.androiduidesign.R
import com.example.androiduidesign.utils.BASE_URL
import com.example.androiduidesign.utils.POST
import com.example.androiduidesign.utils.SIGNUP_URL
import com.example.androiduidesign.webservice.without_retrofit.BaseViewModel
import com.example.androiduidesign.webservice.without_retrofit.HTTPCallback
import java.net.URL
import org.json.JSONObject

class SignupViewModel(application: Application): BaseViewModel(application) {
    val passwordStatus : MutableLiveData<Int> = MutableLiveData(0)
    val password :MutableLiveData<String> = MutableLiveData()
    val cpassword : MutableLiveData<String> = MutableLiveData()
    val signupResult = MutableLiveData<String>()
    var email: MutableLiveData<String> = MutableLiveData()
    var name: MutableLiveData<String> = MutableLiveData()
    var mobileNo: MutableLiveData<String> = MutableLiveData()

    fun performValidation() {

        if (email.value.isNullOrEmpty()) {
            signupResult.value = getApplication<Application>().resources.getString(R.string.toast_email_empty)
            return
        } else if (mobileNo.value.isNullOrEmpty()) {
            signupResult.value = getApplication<Application>().resources.getString(R.string.toast_phone_empty)
            return
        } else if (name.value.isNullOrEmpty()) {
            signupResult.value = getApplication<Application>().resources.getString(R.string.toast_name_empty)
            return
        } else if (mobileNo.value?.length ?: 10 < 10) {
            signupResult.value = getApplication<Application>().resources.getString(R.string.toast_valid_phone)
            return
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.value).matches()) {
            signupResult.value = getApplication<Application>().resources.getString(R.string.toast_email_not_valid)
            return
        } else if (password.value.isNullOrEmpty()) {
            signupResult.value = getApplication<Application>().resources.getString(R.string.toast_password_empty)
        } else if(cpassword.value.isNullOrEmpty()) {
            signupResult.value = getApplication<Application>().resources.getString(R.string.toast_confirm_password)
        } else {
            performLoginApiCall()
        }
    }

    private fun performLoginApiCall() {
        val cred = JSONObject()
        cred.apply {
            put(getApplication<Application>().resources.getString(R.string.email_key), email.value)
            put(getApplication<Application>().resources.getString(R.string.password_key), password.value)
        }
        val url = URL(BASE_URL + SIGNUP_URL)
        apiCall(cred, url, POST, Any::class.java, object : HTTPCallback {
            override fun <T> successCallback(output: String, dataClass: T?) {
                signupResult.postValue(getApplication<Application>().resources.getString(R.string.user_created_message))
                Log.d(getApplication<Application>().resources.getString(R.string.text_api_response),output)
            }

            override fun failureCallback(responseCode: Int, output: String) {
                signupResult.postValue(getApplication<Application>().resources.getString(R.string.user_not_created))
                Log.d(getApplication<Application>().resources.getString(R.string.text_api_response),output)
            }
        })
    }
}