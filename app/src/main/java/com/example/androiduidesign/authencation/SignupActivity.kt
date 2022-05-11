package com.example.androiduidesign.authencation

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.androiduidesign.R
import com.example.androiduidesign.databinding.ActivitySignupBinding


class SignupActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        val adapter = ArrayAdapter.createFromResource(this, R.array.countryCodes, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerCountryCode.adapter = adapter

        val passwordbarone = false
        val passwordbartwo = false
        val passwordbarthree = false
        val passwordbarfour = false

        binding.editxtSignupPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                //Will be Implemented Later
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //Will be Implemented Later
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().length >= 8) {
                    binding.apply {
                        passwordbarOne.setBackgroundResource(R.color.green_500)
                        textviewSignupPasswordTypeHeader.apply {
                            text = context.getString(R.string.password_type_weak)
                            setBackgroundResource(R.color.red)
                        }
                    }
                    if (s.toString().length >= 8 && s.toString().contains("[A-Z]".toRegex())) {
                        binding.passwordbarTwo.setBackgroundResource(R.color.green_500)
                        if (s.toString().length >= 8 && s.toString().contains("[a-z]".toRegex()) && s.toString().contains("[A-Z]".toRegex())
                        ) {
                            binding.passwordbarThree.setBackgroundResource(R.color.green_500)
                            if (s.toString().length >= 8 && s.toString().contains("[a-z]".toRegex()) && s.toString().contains("[A-Z]".toRegex()) && s.toString().contains("[0-9]".toRegex())) {
                                binding.passwordbarFour.setBackgroundResource(R.color.green_500)
                            }
                        }
                    }
                }
            }
        })
    }
}