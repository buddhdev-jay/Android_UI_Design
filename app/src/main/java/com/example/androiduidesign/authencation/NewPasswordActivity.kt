package com.example.androiduidesign.authencation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.androiduidesign.R
import com.example.androiduidesign.databinding.ActivityNewPasswordBinding
import com.example.androiduidesign.utils.THIRTYEIGHT
import com.example.androiduidesign.utils.THIRTYONE
import com.example.androiduidesign.utils.THIRTYTHREE
import com.example.androiduidesign.utils.TWENTY
import com.example.androiduidesign.utils.getSpannable

class NewPasswordActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityNewPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialSetup()
    }

    private fun initialSetup() {
        supportActionBar?.hide()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_new_password)
        setSpannableText()
        binding.onClickHandler = this@NewPasswordActivity
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    private fun setSpannableText() {
        val spannable = getSpannable(binding.textviewHaveAccountNewPassword.text.toString(), THIRTYONE, THIRTYEIGHT, ContextCompat.getColor(this@NewPasswordActivity, R.color.green_500)) {
            val signInIntent = Intent(this@NewPasswordActivity, SignInActivity::class.java)
            startActivity(signInIntent)
            finish()
        }
        binding.textviewHaveAccountNewPassword.text = spannable
        binding.textviewHaveAccountNewPassword.movementMethod = LinkMovementMethod.getInstance()
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.btn_change_password -> {
                if (binding.editxtNewPasswordPassword.text.toString() == binding.ediTxtNewConfirmPassword.text.toString()) {
                    Toast.makeText(this@NewPasswordActivity, getString(R.string.text_change_password_clicked), Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@NewPasswordActivity, SignInActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this@NewPasswordActivity, getString(R.string.text_password_confirm_password_not_equal), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}