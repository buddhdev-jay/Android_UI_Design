package com.example.androiduidesign.authencation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.util.Patterns
import android.view.WindowManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.androiduidesign.R
import com.example.androiduidesign.databinding.ActivitySignInBinding
import com.example.androiduidesign.utils.THIRTYONE
import com.example.androiduidesign.utils.THIRTYTHREE
import com.example.androiduidesign.utils.TWENETYFOUR
import com.example.androiduidesign.utils.TWENTY
import com.example.androiduidesign.utils.getSpannable
import com.example.androiduidesign.utils.showMessage

class SignInActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialSetup()

        binding.apply {
            btnSignIn.setOnClickListener {
                when {
                    ediTxtSignInEmail.text.toString().isEmpty() -> {
                        showMessage(this@SignInActivity,getString(R.string.toast_email_empty))
                    }
                    editxtSignInPassword.text.toString().isEmpty() -> {
                        showMessage(this@SignInActivity,getString(R.string.toast_password_empty))
                    }
                    !Patterns.EMAIL_ADDRESS.matcher(ediTxtSignInEmail.text.toString()).matches() -> {
                       showMessage(this@SignInActivity,getString(R.string.toast_email_not_valid))
                    }
                    else -> {
                        showMessage(this@SignInActivity,getString(R.string.toast_text_sign_in_button_pressed))
                    }
                }
            }
            btnFaceId.setOnClickListener {
                showMessage(this@SignInActivity,getString(R.string.face_id_clicked))
            }
            btnFacebook.setOnClickListener {
                showMessage(this@SignInActivity,getString(R.string.facebook_clicked))
            }
            btnGoogle.setOnClickListener {
                showMessage(this@SignInActivity,getString(R.string.google_clicked))
            }
            textviewForgetPassword.setOnClickListener {
                showMessage(this@SignInActivity,getString(R.string.forget_password_clicked))
            }
        }
    }

    private fun initialSetup() {
        supportActionBar?.hide()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)
        setSpannableText()
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    private fun setSpannableText() {
        val spannable = getSpannable(binding.textviewNotHaveAccount.text.toString(), TWENETYFOUR, THIRTYONE, ContextCompat.getColor(this@SignInActivity, R.color.green_500)) {}
        binding.textviewNotHaveAccount.text = spannable
        binding.textviewNotHaveAccount.movementMethod = LinkMovementMethod.getInstance()
    }
}