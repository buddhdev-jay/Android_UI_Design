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
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.androiduidesign.R
import com.example.androiduidesign.databinding.ActivityForgetPasswordBinding
import com.example.androiduidesign.databinding.ActivitySignInBinding
import kotlinx.android.synthetic.main.activity_forget_password.check_box_email

class ForgetPasswordActivity : AppCompatActivity() {
    lateinit var binding: ActivityForgetPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialSetup()

        binding.apply {
            checkBoxEmail.setOnCheckedChangeListener { _, value ->
                if (!value){
                    checkBoxEmail.isChecked = false
                    checkBoxPhone.isChecked = true
                } else {
                    checkBoxEmail.isChecked = true
                    checkBoxPhone.isChecked = false
                }
            }
            checkBoxPhone.setOnCheckedChangeListener { _, value ->
                 if (!value){
                    checkBoxEmail.isChecked = true
                    checkBoxPhone.isChecked = false
                } else {
                    checkBoxEmail.isChecked = false
                    checkBoxPhone.isChecked = true
                }
            }
            btnSendCodeForgetPassword.setOnClickListener {
                Toast.makeText(this@ForgetPasswordActivity,getString(R.string.toast_text_send_code), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initialSetup() {
        supportActionBar?.hide()
        binding = DataBindingUtil.setContentView(this,R.layout.activity_forget_password)
        setSpannableText()
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
        spannable.setSpan(clickableSpan2, 31, 38, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.textviewHaveAccountForgetPassword.text = spannable
        binding.textviewHaveAccountForgetPassword.movementMethod = LinkMovementMethod.getInstance()
    }
}