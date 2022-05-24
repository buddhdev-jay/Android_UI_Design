package com.example.androiduidesign.utils

import com.example.androiduidesign.authencation.LoginModel
import com.example.androiduidesign.authencation.UserModel
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiInterface {

    @POST(REQRES_LOGIN_URL)
    fun loginUser(@Body userData: UserModel): Call<UserModel>

    @POST(REQRES_SIGNUP_URL)
    fun signupUser(@Body userData: UserModel): Call<UserModel>
    companion object {
        private var interceptor = OkHttpProfilerInterceptor()
        private val okHttpClientBuilder = OkHttpClient.Builder().addInterceptor(interceptor)
            .addInterceptor(
                Interceptor {
                    val builder = it.request().newBuilder()
                    builder.header("Content-Type", "application/json")
                    return@Interceptor it.proceed(builder.build())
                }
            )
        private val okHttpClient = okHttpClientBuilder.build()

        fun create() : ApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}