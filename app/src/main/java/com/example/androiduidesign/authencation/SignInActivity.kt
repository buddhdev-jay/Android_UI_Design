package com.example.androiduidesign.authencation

import android.content.Intent
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
import com.example.androiduidesign.utils.NINETEEN
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

        loginViewModel.logInResult.observe(this) { result ->
            if (result == getString(R.string.login_successful)) {
                showMessage(this,result)
                startActivity(Intent(this@SignInActivity,HomeScreenActivity::class.java))
                finish()
            } else {
                showMessage(this,result)
            }

        }

    }

    private fun setSpannableText() {
        val spannable = getSpannable(binding.textviewNotHaveAccount.text.toString(), TWENETYFOUR, THIRTYONE, ContextCompat.getColor(this@SignInActivity, R.color.green_500)) {
            val signInIntent = Intent(this@SignInActivity, ForgetPasswordActivity::class.java)
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
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        binding.apply {
            viewModel = loginViewModel
            onClickHandler = this@SignInActivity
        }
    }

    override fun onClick(p0: View?) {
        binding.apply {
            when (p0) {
                btnSignIn -> {
                    loginViewModel.performValidation()
                }
                btnFaceId -> {
                    showMessage(this@SignInActivity, getString(R.string.face_id_clicked))
                }
                btnFacebook -> {
                    showMessage(this@SignInActivity, getString(R.string.facebook_clicked))
                }
                btnGoogle -> {
                    showMessage(this@SignInActivity, getString(R.string.google_clicked))
                }
            }
        }
    }
}