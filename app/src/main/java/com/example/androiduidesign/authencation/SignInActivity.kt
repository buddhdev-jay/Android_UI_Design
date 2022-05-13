package com.example.androiduidesign.authencation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.androiduidesign.R
import com.example.androiduidesign.databinding.ActivityOnBoardingBinding
import com.example.androiduidesign.databinding.ActivitySignInBinding
import com.example.androiduidesign.utils.NINETEEN
import com.example.androiduidesign.utils.THIRTYONE
import com.example.androiduidesign.utils.TWENETYFOUR
import com.example.androiduidesign.utils.TWENTYSIX
import com.example.androiduidesign.utils.getSpannable

class SignInActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialSetup()
        binding.textviewNotHaveAccount.setOnClickListener {
            startActivity(Intent(this,SignupActivity::class.java))
        }
        binding.apply {
            btnSignIn.setOnClickListener {
                when {
                    this.ediTxtSignInEmail.toString().isEmpty() -> {
                        Toast.makeText(this@SignInActivity,getString(R.string.toast_email_empty), Toast.LENGTH_SHORT).show()
                    }
                    this.editxtSignInPassword.toString().isEmpty() -> {
                        Toast.makeText(this@SignInActivity,getString(R.string.toast_password_empty),Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        Toast.makeText(this@SignInActivity,getString(R.string.sign_in_btn_clicked),Toast.LENGTH_SHORT).show()
                    }
                }
            }
            btnFaceId.setOnClickListener {
                Toast.makeText(this@SignInActivity,getString(R.string.face_id_clicked),Toast.LENGTH_SHORT).show()
            }
            btnFacebook.setOnClickListener {
                Toast.makeText(this@SignInActivity,getString(R.string.facebook_clicked),Toast.LENGTH_SHORT).show()
            }
            btnGoogle.setOnClickListener {
                Toast.makeText(this@SignInActivity,getString(R.string.google_clicked),Toast.LENGTH_SHORT).show()
            }
            textviewForgetPassword.setOnClickListener {
                startActivity(Intent(this@SignInActivity,ForgetPasswordActivity::class.java))
            }
        }
    }

    private fun setSpannableText() {
        val spannable = getSpannable(binding.textviewNotHaveAccount.text.toString(), TWENETYFOUR, THIRTYONE, ContextCompat.getColor(this@SignInActivity, R.color.green_500)) {
            val signInIntent = Intent(this@SignInActivity,SignupActivity::class.java)
            startActivity(signInIntent)
            finish()
        }
        binding.textviewNotHaveAccount.text = spannable
        binding.textviewNotHaveAccount.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun initialSetup() {
        supportActionBar?.hide()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)
        setSpannableText()
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }
}