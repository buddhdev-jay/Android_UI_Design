package com.example.androiduidesign.authencation

import android.app.Application
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import com.example.androiduidesign.R
import com.example.androiduidesign.utils.API_RESPONSE_LOG
import com.example.androiduidesign.utils.ApiInterface
import com.example.androiduidesign.utils.BASE_URL
import com.example.androiduidesign.utils.EMAIL
import com.example.androiduidesign.utils.PASSWORD
import com.example.androiduidesign.utils.POST
import com.example.androiduidesign.utils.RetrofitObject
import com.example.androiduidesign.utils.SIGNUP_URL
import com.example.androiduidesign.utils.TEN
import com.example.androiduidesign.utils.ZERO
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

class SignupViewModel(): BaseViewModel() {
    val passwordStatus : MutableLiveData<Int> = MutableLiveData(ZERO)
    val password :MutableLiveData<String> = MutableLiveData()
    val cpassword : MutableLiveData<String> = MutableLiveData()
    val signupResult = MutableLiveData<RegisterResponseModel>()
    val validationResult = MutableLiveData<Int>()
    val termsAndCondtionCheck = MutableLiveData<Boolean>()
    var email: MutableLiveData<String> = MutableLiveData()
    var name: MutableLiveData<String> = MutableLiveData()
    var mobileNo: MutableLiveData<String> = MutableLiveData()

    fun performValidation() {

         if (name.value.isNullOrEmpty()) {
            validationResult.value = R.string.toast_name_empty
            return
        } else if (email.value.isNullOrEmpty()) {
            validationResult.value = R.string.toast_email_empty
            return
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email.value).matches()) {
            validationResult.value = R.string.toast_email_not_valid
            return
        } else if (mobileNo.value.isNullOrEmpty()) {
            validationResult.value = R.string.toast_phone_empty
            return
        }  else if (password.value.isNullOrEmpty()) {
            validationResult.value = R.string.toast_password_empty
        } else if(cpassword.value.isNullOrEmpty()) {
            validationResult.value = R.string.toast_confirm_password
        } else if (mobileNo.value?.length ?: TEN < TEN) {
             validationResult.value = R.string.toast_valid_phone
             return
         } else if(termsAndCondtionCheck.value == true) {
             validationResult.value = R.string.terms_and_condition_not_checked
             return
         }
         else {
            validationResult.value = R.string.validation_sucessful
            performSignupApiCall()
        }
    }

    private fun performSignupApiCall() {
       retrofitCall(RetrofitObject.retrofitClient.signupUser(UserModel(email.value ?: "eve.holt@reqres.in",password.value ?: "cityslicka")),object : ApiCallBackListener {
            override fun <T : Any> onSuccess(data: T) {
                signupResult.postValue(RegisterResponseModel(true, data))
            }

            override fun onFailure(error: ErrorResponse) {
                signupResult.postValue(RegisterResponseModel(false,error))
            }
        })
    }
}