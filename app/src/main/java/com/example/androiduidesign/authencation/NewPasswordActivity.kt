package com.example.androiduidesign.authencation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.WindowManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.androiduidesign.R
import com.example.androiduidesign.databinding.ActivityNewPasswordBinding
import com.example.androiduidesign.databinding.ActivityVerficationScreenBinding
import com.example.androiduidesign.utils.THIRTYTHREE
import com.example.androiduidesign.utils.TWENTY
import com.example.androiduidesign.utils.getSpannable

class NewPasswordActivity : AppCompatActivity() {
    lateinit var binding: ActivityNewPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_password)
        initialSetup()
        binding.apply {
            btnChangePassword.setOnClickListener {
                if (editxtNewPasswordPassword.text.toString() == ediTxtNewConfirmPassword.text.toString()) {
                    Toast.makeText(this@NewPasswordActivity, getString(R.string.text_change_password_clicked), Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@NewPasswordActivity, getString(R.string.text_password_confirm_password_not_equal), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initialSetup() {
        supportActionBar?.hide()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_new_password)
        setSpannableText()
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    private fun setSpannableText() {
        val spannable = getSpannable(binding.textviewHaveAccountNewPassword.text.toString(), TWENTY, THIRTYTHREE, ContextCompat.getColor(this@NewPasswordActivity, R.color.green_500)) {
            val signInIntent = Intent(this@NewPasswordActivity, ForgetPasswordActivity::class.java)
            startActivity(signInIntent)
            finish()
        }
        binding.textviewHaveAccountNewPassword.text = spannable
        binding.textviewHaveAccountNewPassword.movementMethod = LinkMovementMethod.getInstance()
    }
}