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
    lateinit var adapter: ArrayAdapter<CharSequence>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intialSetup()
        imgview_backarrow.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }

        signupViewModel.password.observe(this) { password ->
            if (password.length >= 8) {
                var passwordStausupdate = 1
                if (password.toString().contains(getString(R.string.numeric_regex).toRegex())) {
                    passwordStausupdate += 1
                }
                if (password.toString().contains(getString(R.string.small_alphabet_regex).toRegex())) {
                    passwordStausupdate += 1
                }
                if (password.toString().contains(getString(R.string.upper_alphabet_regex).toRegex())) {
                    passwordStausupdate += 1
                }
                signupViewModel.passwordStatus.value = passwordStausupdate
            } else {
                signupViewModel.passwordStatus.value = 0
            }
        }
    }

    private fun intialSetup() {
        supportActionBar?.hide()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        adapter = ArrayAdapter.createFromResource(this, R.array.countryCodes, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.apply {
            viewModel = signupViewModel
            spinnerCountryCode.adapter = adapter
            lifecycleOwner = this@SignupActivity
        }
    }
}