package com.example.androiduidesign.authencation

import android.app.Application
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import com.example.androiduidesign.R
import com.example.androiduidesign.utils.BASE_URL
import com.example.androiduidesign.utils.LOGIN_URL
import com.example.androiduidesign.utils.POST
import com.example.androiduidesign.webservice.without_retrofit.BaseViewModel
import com.example.androiduidesign.webservice.without_retrofit.HTTPCallback
import java.net.URL
import org.json.JSONObject

class LoginViewModel(application: Application): BaseViewModel(application) {

    val password : MutableLiveData<String> = MutableLiveData()
    var email: MutableLiveData<String> = MutableLiveData()
    val logInResult = MutableLiveData<String>()

    fun performValidation() {

        if (email.value.isNullOrEmpty()) {
            logInResult.value = getApplication<Application>().resources.getString(R.string.toast_email_empty)
            return
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.value).matches()) {
            logInResult.value = getApplication<Application>().resources.getString(R.string.toast_email_not_valid)
            return
        } else if (password.value.isNullOrEmpty()) {
            logInResult.value = getApplication<Application>().resources.getString(R.string.toast_password_empty)
        } else {
            performLoginApiCall()
        }
    }

    private fun performLoginApiCall() {
        val cred = JSONObject()
        cred.apply {
            put(getApplication<Application>().resources.getString(R.string.email_key), email.value)
            put(getApplication<Application>().resources.getString(R.string.password_key),password.value)
        }
        val url = URL(BASE_URL + LOGIN_URL)
        apiCall(cred, url, POST, Any::class.java, object : HTTPCallback {
            override fun <T> successCallback(output: String, dataClass: T?) {
                logInResult.postValue(getApplication<Application>().resources.getString(R.string.login_successful))
                Log.d(getApplication<Application>().resources.getString(R.string.text_api_response),output)
            }

            override fun failureCallback(responseCode: Int, output: String) {
                logInResult.postValue(getApplication<Application>().resources.getString(R.string.login_unsuccessful))
                Log.d(getApplication<Application>().resources.getString(R.string.text_api_response),output)
            }
        })
    }
}