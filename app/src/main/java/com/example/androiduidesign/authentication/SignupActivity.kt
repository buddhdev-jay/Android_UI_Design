package com.example.androiduidesign.authentication

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Patterns
import android.view.View
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.androiduidesign.R
import com.example.androiduidesign.databinding.ActivitySignupBinding
import com.example.androiduidesign.utils.NINETEEN
import com.example.androiduidesign.utils.ONE
import com.example.androiduidesign.utils.THIRTYONE
import com.example.androiduidesign.utils.TWENETYFOUR
import com.example.androiduidesign.utils.TWENTYSIX
import com.example.androiduidesign.utils.ZERO
import com.example.androiduidesign.utils.getSpannable
import com.example.androiduidesign.utils.showMessage
import kotlinx.android.synthetic.main.activity_signup.btn_signup
import kotlinx.android.synthetic.main.activity_signup.img_view_back_arrow


class SignupActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding
    val signupViewModel : SignupViewModel by viewModels()
    lateinit var adapter: ArrayAdapter<CharSequence>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intialSetup()
        img_view_back_arrow.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }
        btn_signup.setOnClickListener {
         signupViewModel.performValidation()
        }
        signupViewModel.logInResult.observe(this){  result ->
            showMessage(this,result)
        }
        signupViewModel.password.observe(this) { password ->
            password.apply {
                if (isNotEmpty()) {
                    var passwordStatusUpdate = ONE
                    if (length >= 8) {
                        if (toString().contains(getString(R.string.numeric_regex).toRegex())) {
                            passwordStatusUpdate += ONE
                        }
                        if (toString().contains(getString(R.string.small_alphabet_regex).toRegex())) {
                            passwordStatusUpdate += ONE
                        }
                        if (toString().contains(getString(R.string.upper_alphabet_regex).toRegex())) {
                            passwordStatusUpdate += ONE
                        }
                    }
                    signupViewModel.passwordStatus.value = passwordStatusUpdate
                } else {
                    signupViewModel.passwordStatus.value = ZERO
                }
            }
        }
    }

    private fun intialSetup() {
        supportActionBar?.hide()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        adapter = ArrayAdapter.createFromResource(this, R.array.countryCodes, android.R.layout.simple_spinner_dropdown_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.apply {
            viewModel = signupViewModel
            spinnerCountryCode.adapter = adapter
            lifecycleOwner = this@SignupActivity
        }
        setSpannableText()
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    private fun setSpannableText() {
        val spannable = getSpannable(binding.textviewNotHaveAccount.text.toString(), NINETEEN, TWENTYSIX, ContextCompat.getColor(this@SignupActivity, R.color.green_500)) {}
        binding.textviewNotHaveAccount.text = spannable
        binding.textviewNotHaveAccount.movementMethod = LinkMovementMethod.getInstance()
    }
}