package com.example.androiduidesign.authencation

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Patterns
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.androiduidesign.R
import com.example.androiduidesign.dashboard.HomeScreenActivity
import com.example.androiduidesign.databinding.ActivityOnBoardingBinding
import com.example.androiduidesign.databinding.ActivitySignInBinding
import com.example.androiduidesign.utils.LOGIN_STATE
import com.example.androiduidesign.utils.NINETEEN
import com.example.androiduidesign.utils.SHAREDPREF_NAME
import com.example.androiduidesign.utils.SPLASHSCREEN_STATE
import com.example.androiduidesign.utils.THIRTYONE
import com.example.androiduidesign.utils.TWENETYFOUR
import com.example.androiduidesign.utils.TWENTYSIX
import com.example.androiduidesign.utils.getSpannable
import com.example.androiduidesign.utils.showMessage

class SignInActivity : AppCompatActivity(),View.OnClickListener {
    lateinit var binding: ActivitySignInBinding
    val loginViewModel : LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialSetup()

        binding.textviewNotHaveAccount.setOnClickListener {
            startActivity(Intent(this,SignupActivity::class.java))
        }

        loginViewModel.logInResult.observe(this) { response ->
            if (response.isSuccess) {
                binding.progressbarSignIn.visibility = View.INVISIBLE
                showMessage(this, getString(R.string.login_successful))
                val prefs = getSharedPreferences(SHAREDPREF_NAME, Context.MODE_PRIVATE)
                prefs.edit().putBoolean(LOGIN_STATE,true).apply()
                startActivity(Intent(this@SignInActivity, HomeScreenActivity::class.java))
                finish()
            } else {
                binding.btnSignIn.visibility = View.VISIBLE
                binding.progressbarSignIn.visibility = View.INVISIBLE
                showMessage(this, getString(R.string.login_unsuccessful))
            }
        }
        loginViewModel.validationResult.observe(this) { validationResult ->
            if(getString(validationResult) ==  getString(R.string.validation_sucessful)){
                binding.btnSignIn.visibility = View.INVISIBLE
                binding.progressbarSignIn.visibility = View.VISIBLE
            } else {
                showMessage(this, getString(validationResult))
            }
        }
    }

    private fun setSpannableText() {
        val spannable = getSpannable(binding.textviewNotHaveAccount.text.toString(), TWENETYFOUR, THIRTYONE, ContextCompat.getColor(this@SignInActivity, R.color.green_500)) {
            val signInIntent = Intent(this@SignInActivity, SignupActivity::class.java)
            startActivity(signInIntent)
            finish()
        }
        binding.textviewNotHaveAccount.apply {
            text = spannable
            movementMethod = LinkMovementMethod.getInstance()
        }
    }

    private fun initialSetup() {
        supportActionBar?.hide()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)
        setSpannableText()
        binding.apply {
            viewModel = loginViewModel
            onClickHandler = this@SignInActivity
        }
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btn_sign_in -> {
                loginViewModel.performValidation()
            }
            R.id.btn_face_id -> {
                showMessage(this@SignInActivity, getString(R.string.face_id_clicked))
            }
            R.id.btn_facebook -> {
                showMessage(this@SignInActivity, getString(R.string.facebook_clicked))
            }
            R.id.btn_google -> {
                showMessage(this@SignInActivity, getString(R.string.google_clicked))
            }
            R.id.textview_forget_password -> {
                startActivity(Intent(this,ForgetPasswordActivity::class.java))
            }
        }
    }
}