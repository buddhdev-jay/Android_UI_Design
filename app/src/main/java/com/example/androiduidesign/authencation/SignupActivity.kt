package com.example.androiduidesign.authencation

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.androiduidesign.R
import com.example.androiduidesign.databinding.ActivitySignupBinding
import com.example.androiduidesign.onboardingscreen.OnBoardingActivity
import kotlinx.android.synthetic.main.activity_signup.imgview_backarrow


class SignupActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding
    val signupViewModel : SignupViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        val adapter = ArrayAdapter.createFromResource(this, R.array.countryCodes, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        imgview_backarrow.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }
        binding.viewModel = signupViewModel
        binding.spinnerCountryCode.adapter = adapter
        binding.lifecycleOwner = this
        signupViewModel.password.observe(this) { password ->
            if (password.length >= 8) {
                signupViewModel.passwordStatus.value = 1
            }
            if( password.length >= 8 && password.toString().contains("[A-Z]".toRegex())) {
                signupViewModel.passwordStatus.value = 2
            }
            if(password.length >= 8 && password.toString().contains("[a-z]".toRegex())){
                signupViewModel.passwordStatus.value = 3
            }
            if( password.length >= 8 && password.toString().contains("[0-9]".toRegex() )&& password.length >= 8 && password.toString().contains("[a-z]".toRegex()) &&  password.toString().contains("[A-Z]".toRegex())){
                signupViewModel.passwordStatus.value = 4
            }
        }
    }
}