package com.example.androiduidesign.authencation

import android.app.Application
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import com.example.androiduidesign.R
import com.example.androiduidesign.utils.API_RESPONSE_LOG
import com.example.androiduidesign.utils.BASE_URL
import com.example.androiduidesign.utils.EMAIL
import com.example.androiduidesign.utils.PASSWORD
import com.example.androiduidesign.utils.POST
import com.example.androiduidesign.utils.SIGNUP_URL
import com.example.androiduidesign.utils.TEN
import com.example.androiduidesign.utils.ZERO
import com.example.androiduidesign.webservice.without_retrofit.BaseViewModel
import com.example.androiduidesign.webservice.without_retrofit.HTTPCallback
import java.net.URL
import org.json.JSONObject

class SignupViewModel(): BaseViewModel() {
    val passwordStatus : MutableLiveData<Int> = MutableLiveData(ZERO)
    val password :MutableLiveData<String> = MutableLiveData()
    val cpassword : MutableLiveData<String> = MutableLiveData()
    val signupResult = MutableLiveData<Int>()
    var email: MutableLiveData<String> = MutableLiveData()
    var name: MutableLiveData<String> = MutableLiveData()
    var mobileNo: MutableLiveData<String> = MutableLiveData()

    fun performValidation() {

        if (email.value.isNullOrEmpty()) {
            signupResult.value = R.string.toast_email_empty
            return
        } else if (mobileNo.value.isNullOrEmpty()) {
            signupResult.value = R.string.toast_phone_empty
            return
        } else if (name.value.isNullOrEmpty()) {
            signupResult.value = R.string.toast_name_empty
            return
        } else if (mobileNo.value?.length ?: TEN < TEN) {
            signupResult.value = R.string.toast_valid_phone
            return
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.value).matches()) {
            signupResult.value = R.string.toast_email_not_valid
            return
        } else if (password.value.isNullOrEmpty()) {
            signupResult.value = R.string.toast_password_empty
        } else if(cpassword.value.isNullOrEmpty()) {
            signupResult.value = R.string.toast_confirm_password
        } else {
            performLoginApiCall()
        }
    }

    private fun performLoginApiCall() {
        val cred = JSONObject()
        cred.apply {
            put(EMAIL, email.value)
            put(PASSWORD, password.value)
        }
        val url = URL(BASE_URL + SIGNUP_URL)
        apiCall(cred, url, POST, Any::class.java, object : HTTPCallback {
            override fun <T> successCallback(output: String, dataClass: T?) {
                signupResult.postValue(R.string.user_created_message)
                Log.d(API_RESPONSE_LOG,output)
            }

            override fun failureCallback(responseCode: Int, output: String) {
                signupResult.postValue(R.string.user_not_created)
                Log.d(API_RESPONSE_LOG,output)
            }
        })
    }
}