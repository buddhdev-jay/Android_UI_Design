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
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.androiduidesign.R
import com.example.androiduidesign.databinding.ActivityForgetPasswordBinding
import com.example.androiduidesign.utils.THIRTYEIGHT
import com.example.androiduidesign.utils.THIRTYONE
import com.example.androiduidesign.utils.TWENETYFOUR
import com.example.androiduidesign.utils.getSpannable
import kotlinx.android.synthetic.main.activity_forget_password.img_view_back_arrow_forget_password

class ForgetPasswordActivity : AppCompatActivity() ,View.OnClickListener{
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
        }
    }

    private fun initialSetup() {
        supportActionBar?.hide()
        binding = DataBindingUtil.setContentView(this,R.layout.activity_forget_password)
        setSpannableText()
        binding.apply {
            viewModel = forgetPasswordViewModel
            lifecycleOwner = this@ForgetPasswordActivity
            onClickHandler = this@ForgetPasswordActivity

        }
    }

    private fun setSpannableText() {

        val spannable = getSpannable(binding.textviewHaveAccountForgetPassword.text.toString(), THIRTYONE, THIRTYEIGHT, ContextCompat.getColor(this@ForgetPasswordActivity, R.color.green_500)) {
            val signInIntent = Intent(this@ForgetPasswordActivity, SignInActivity::class.java)
            startActivity(signInIntent)
            finish()
        }
        binding.textviewHaveAccountForgetPassword.apply {
            text = spannable
            movementMethod = LinkMovementMethod.getInstance()
        }
    }

    override fun onClick(p0: View?) {
        when (p0?.id){
            R.id.img_view_email_icon_forget_password,R.id.txt_view_email_header_forget_password,R.id.txt_view_email_forget_password -> {
                    binding.apply {
                        checkBoxEmail.isChecked = !checkBoxEmail.isChecked
                    }
            }
            R.id.img_view_phone_icon_forget_password,R.id.txt_view_phone_header_forget_password,R.id.txt_view_phone_forget_password -> {
                binding.apply {
                    checkBoxPhone.isChecked = !checkBoxPhone.isChecked
                }
            }
            R.id.btn_send_code_forget_password -> {

                val intent = Intent(this@ForgetPasswordActivity, VerficationScreenActivity::class.java)
                if(binding.checkBoxEmail.isChecked) {
                    intent.putExtra("otpType",0)
                } else {
                    intent.putExtra("otpType",1)
                }
                startActivity(intent)
                finish()
            }
            R.id.img_view_back_arrow_forget_password -> {
                startActivity(Intent(this@ForgetPasswordActivity, SignInActivity::class.java))
                finish()
            }
        }
    }
}