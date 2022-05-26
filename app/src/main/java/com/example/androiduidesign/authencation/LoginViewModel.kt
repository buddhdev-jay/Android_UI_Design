package com.example.androiduidesign.authencation

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import com.example.androiduidesign.R
import com.example.androiduidesign.utils.API_RESPONSE_LOG
import com.example.androiduidesign.utils.ApiInterface
import com.example.androiduidesign.utils.BASE_URL
import com.example.androiduidesign.utils.EMAIL
import com.example.androiduidesign.utils.LOGIN_URL
import com.example.androiduidesign.utils.PASSWORD
import com.example.androiduidesign.utils.POST
import com.example.androiduidesign.utils.RetrofitObject
import com.example.androiduidesign.webservice.with_retrofit.ApiCallBackListener
import com.example.androiduidesign.webservice.with_retrofit.ErrorResponse
import com.example.androiduidesign.webservice.with_retrofit.RetrofitBaseViewModel
import com.example.androiduidesign.webservice.without_retrofit.BaseViewModel
import com.example.androiduidesign.webservice.without_retrofit.HTTPCallback
import java.net.URL
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(): BaseViewModel() {

    val password : MutableLiveData<String> = MutableLiveData()
    var email: MutableLiveData<String> = MutableLiveData()
    val logInResult = MutableLiveData<LoginResponseModel>()
    val checkedRemberMe =  MutableLiveData<Boolean>()
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
            validationResult.value = R.string.validation_sucessful
            performLoginApiCall()
        }
    }

    private fun performLoginApiCall() {
        retrofitCall(RetrofitObject.retrofitClient.loginUser(UserModel(email.value ?: "eve.holt@reqres.in",password.value ?: "cityslicka")),object : ApiCallBackListener {
            override fun <T : Any> onSuccess(data: T) {
                logInResult.postValue(LoginResponseModel(true, data))
            }

            override fun onFailure(error: ErrorResponse) {
                logInResult.postValue(LoginResponseModel(false,error))
            }
        })
    }
}
