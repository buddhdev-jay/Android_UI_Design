package com.example.androiduidesign.authencation

import android.app.Application
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.androiduidesign.R
import com.example.androiduidesign.utils.CREATE_USER_BASE_URL
import com.example.androiduidesign.utils.POST
import com.example.androiduidesign.webservice.without_retrofit.BaseViewModel
import com.example.androiduidesign.webservice.without_retrofit.HTTPCallback
import java.net.URL
import kotlinx.android.synthetic.main.activity_signup.edi_txt_signup_email
import kotlinx.android.synthetic.main.activity_signup.edi_txt_signup_full_name
import kotlinx.android.synthetic.main.activity_signup.edi_txt_signup_password
import org.json.JSONObject

class SignupViewModel(application: Application): BaseViewModel(application) {
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
        } else {
            performLoginApiCall()
        }
    }

    private fun performLoginApiCall() {
        val cred = JSONObject()
        cred.put(getApplication<Application>().resources.getString(R.string.name_key),name.value)
        cred.put(getApplication<Application>().resources.getString(R.string.email_key), email.value)
        cred.put(getApplication<Application>().resources.getString(R.string.password_key),password.value)
        val url = URL(CREATE_USER_BASE_URL)
        apiCall(cred,url, POST,Any::class.java,object : HTTPCallback {
            override fun <T> successCallback(output: String, dataClass: T?) {
                logInResult.postValue(output)
            }
            override fun failureCallback(responseCode: Int, output: String) {
                logInResult.postValue(output)
            }
        })
    }
}