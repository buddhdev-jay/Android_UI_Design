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
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.androiduidesign.R
import com.example.androiduidesign.databinding.ActivityForgetPasswordBinding
import com.example.androiduidesign.databinding.ActivitySignInBinding
import com.example.androiduidesign.onboardingscreen.OnBoardingActivity
import com.example.androiduidesign.utils.THIRTYEIGHT
import com.example.androiduidesign.utils.THIRTYONE
import kotlinx.android.synthetic.main.activity_forget_password.check_box_email
import kotlinx.android.synthetic.main.activity_forget_password.img_view_back_arrow_forget_password

class ForgetPasswordActivity : AppCompatActivity() {
    lateinit var binding: ActivityForgetPasswordBinding
    val forgetPasswordViewModel : ForgetPasswordViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialSetup()

        binding.apply {
            checkBoxEmail.setOnCheckedChangeListener { _, value ->
                checkBoxEmail.isChecked = value
                checkBoxPhone.isChecked = !value
            }
            checkBoxPhone.setOnCheckedChangeListener { _, value ->
                checkBoxPhone.isChecked = value
                checkBoxEmail.isChecked = !value
            }
            btnSendCodeForgetPassword.setOnClickListener {
                startActivity(Intent(this@ForgetPasswordActivity, VerficationScreenActivity::class.java))
                finish()
            }
            img_view_back_arrow_forget_password.setOnClickListener {
                startActivity(Intent(this@ForgetPasswordActivity, SignInActivity::class.java))
                finish()
            }
        }
    }

    private fun initialSetup() {
        supportActionBar?.hide()
        binding = DataBindingUtil.setContentView(this,R.layout.activity_forget_password)
        setSpannableText()
        binding.apply {
            viewModel = forgetPasswordViewModel
            lifecycleOwner = this@ForgetPasswordActivity
        }
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    private fun setSpannableText() {
        val spannable = SpannableString(binding.textviewHaveAccountForgetPassword.text)
        val clickableSpan2: ClickableSpan = object : ClickableSpan() {
            override fun onClick(p0: View) {
                val forgetPasswordIntent = Intent(this@ForgetPasswordActivity,SignInActivity::class.java)
                startActivity(forgetPasswordIntent)
                finish()
            }

            override fun updateDrawState(ds: TextPaint) {
                ds.setColor(ContextCompat.getColor(applicationContext, R.color.green_500))
                ds.bgColor = ContextCompat.getColor(applicationContext, R.color.white)
            }
        }
        spannable.setSpan(clickableSpan2, THIRTYONE, THIRTYEIGHT, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.textviewHaveAccountForgetPassword.text = spannable
        binding.textviewHaveAccountForgetPassword.movementMethod = LinkMovementMethod.getInstance()
    }
}