package com.example.androiduidesign.authencation

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.view.WindowManager
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.androiduidesign.R
import com.example.androiduidesign.dashboard.HomeScreenActivity
import com.example.androiduidesign.databinding.ActivitySignupBinding
import com.example.androiduidesign.utils.NINETEEN
import com.example.androiduidesign.utils.ONE
import com.example.androiduidesign.utils.THIRTYTHREE
import com.example.androiduidesign.utils.TWENTY
import com.example.androiduidesign.utils.TWENTYSIX
import com.example.androiduidesign.utils.ZERO
import com.example.androiduidesign.utils.getSpannable
import com.example.androiduidesign.utils.showMessage


class SignupActivity : AppCompatActivity() , View.OnClickListener{
    lateinit var binding: ActivitySignupBinding
    val signupViewModel : SignupViewModel by viewModels()
    lateinit var adapter: ArrayAdapter<CharSequence>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialSetup()
        signupViewModel.signupResult.observe(this){ response ->
            if(response.isSuccess){
                binding.progressbarSignUp.visibility = View.INVISIBLE
                showMessage(this,getString(R.string.user_created_message))
                startActivity(Intent(this@SignupActivity,SignInActivity::class.java))
                finish()
            } else {
                binding.progressbarSignUp.visibility = View.INVISIBLE
                showMessage(this,getString(R.string.user_not_created))
            }
        }
        signupViewModel.validationResult.observe(this){validationResult ->
            if(getString(validationResult).equals(R.string.validation_sucessful)){
                binding.progressbarSignUp.visibility = View.VISIBLE
            } else {
                showMessage(this, getString(validationResult))
            }
        }
        signupViewModel.password.observe(this) { password ->
            password.apply {
                if (isNotEmpty()) {
                    var passwordstatusupdate = ONE
                    if (length >= 8) {
                        if (contains(getString(R.string.numeric_regex).toRegex())) {
                            passwordstatusupdate += ONE
                        }
                        if (contains(getString(R.string.small_alphabet_regex).toRegex())) {
                            passwordstatusupdate += ONE
                        }
                        if (contains(getString(R.string.upper_alphabet_regex).toRegex())) {
                            passwordstatusupdate += ONE
                        }
                    }
                    signupViewModel.passwordStatus.value = passwordstatusupdate
                } else {
                    signupViewModel.passwordStatus.value = ZERO
                }
            }
        }
    }

    private fun initialSetup() {
        supportActionBar?.hide()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        adapter = ArrayAdapter.createFromResource(this, R.array.countryCodes, android.R.layout.simple_spinner_dropdown_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.apply {
            viewModel = signupViewModel
            spinnerCountryCode.adapter = adapter
            lifecycleOwner = this@SignupActivity
            onClickHandler = this@SignupActivity
        }
        setSpannableText()
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    private fun setSpannableText() {
        val spannable = getSpannable(binding.textviewNotHaveAccount.text.toString(), NINETEEN, TWENTYSIX, ContextCompat.getColor(this@SignupActivity, R.color.green_500)) {
            val signInIntent = Intent(this@SignupActivity, ForgetPasswordActivity::class.java)
            startActivity(signInIntent)
            finish()
        }
        binding.textviewNotHaveAccount.apply {
            text = spannable
            movementMethod = LinkMovementMethod.getInstance()
        }
    }

    override fun onClick(p0: View?) {
        binding.apply {
            when (p0) {
               imgViewBackArrow-> {
                    startActivity(Intent(this@SignupActivity, SignInActivity::class.java))
                    finish()
                }
                btnSignup -> {
                    binding.progressbarSignUp.visibility = View.VISIBLE
                    signupViewModel.performValidation()
                }
            }
        }

    }
}