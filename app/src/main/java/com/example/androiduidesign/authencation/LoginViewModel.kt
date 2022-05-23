package com.example.androiduidesign.authencation

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import com.example.androiduidesign.R
import com.example.androiduidesign.utils.API_RESPONSE_LOG
import com.example.androiduidesign.utils.BASE_URL
import com.example.androiduidesign.utils.EMAIL
import com.example.androiduidesign.utils.LOGIN_URL
import com.example.androiduidesign.utils.PASSWORD
import com.example.androiduidesign.utils.POST
import com.example.androiduidesign.webservice.without_retrofit.BaseViewModel
import com.example.androiduidesign.webservice.without_retrofit.HTTPCallback
import java.net.URL
import org.json.JSONObject

class LoginViewModel(): BaseViewModel() {

    val password : MutableLiveData<String> = MutableLiveData()
    var email: MutableLiveData<String> = MutableLiveData()
    val logInResult = MutableLiveData<LoginResponseModel>()
    val validationResult = MutableLiveData<Int>()

    fun performValidation() {

        if (email.value.isNullOrEmpty()) {
            validationResult.value = R.string.toast_email_empty
            return
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.value).matches()) {
            validationResult.value = R.string.toast_email_not_valid
            return
        } else if (password.value.isNullOrEmpty()) {
            validationResult.value = R.string.toast_password_empty
        } else {
            performLoginApiCall()
        }
    }

    private fun performLoginApiCall() {
        val cred = JSONObject()
        cred.apply {
            put(EMAIL, email.value)
            put(PASSWORD,password.value)
        }
        val url = URL(BASE_URL + LOGIN_URL)
        apiCall(cred, url, POST, LoginModel::class.java, object : HTTPCallback {
            override fun <T> successCallback(output: String, dataClass: T?) {
                logInResult.postValue(LoginResponseModel(true,dataClass))
                Log.d(API_RESPONSE_LOG,output)
            }

            override fun failureCallback(responseCode: Int, output: String) {
                logInResult.postValue(LoginResponseModel(false,Any::class.java))
                Log.d(API_RESPONSE_LOG,output)
            }
        })
    }
}
