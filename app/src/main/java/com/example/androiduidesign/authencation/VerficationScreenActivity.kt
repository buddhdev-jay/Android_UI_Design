package com.example.androiduidesign.authencation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import com.example.androiduidesign.R
import com.example.androiduidesign.databinding.ActivityVerficationScreenBinding
import com.example.androiduidesign.utils.THIRTYTHREE
import com.example.androiduidesign.utils.TWENTY
import kotlinx.android.synthetic.main.activity_verfication_screen.edit_text_otp_four
import kotlinx.android.synthetic.main.activity_verfication_screen.edit_text_otp_one
import kotlinx.android.synthetic.main.activity_verfication_screen.edit_text_otp_three
import kotlinx.android.synthetic.main.activity_verfication_screen.edit_text_otp_two

class VerficationScreenActivity : AppCompatActivity() {
    lateinit var binding: ActivityVerficationScreenBinding
    val verficationViewModel: VerificationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verfication_screen)
        initialSetup()

        verficationViewModel.editTextOne.observe(this) {
            if (it.length == 1) {
                binding.editTextOtpTwo.requestFocus()
            }
        }
        verficationViewModel.editTextTwo.observe(this) {
            if (it.length == 1) {
                binding.editTextOtpThree.requestFocus()
            }
            if (it.isEmpty()) {
                binding.editTextOtpOne.requestFocus()
            }
        }
        verficationViewModel.editTextThree.observe(this) {
            if (it.length == 1) {
                binding.editTextOtpFour.requestFocus()
            }
            if (it.isEmpty()) {
                binding.editTextOtpTwo.requestFocus()
            }
        }
        verficationViewModel.editTextFour.observe(this) {
            if (it.length == 1) {
                binding.editTextOtpFour.clearFocus()
                val hidekeyboard = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                hidekeyboard?.hideSoftInputFromWindow(binding.editTextOtpFour.windowToken, 0)
            }
            if (it.isEmpty()) {
                binding.editTextOtpThree.requestFocus()
            }
        }
        binding.imgViewBackArrowOtp.setOnClickListener {
            startActivity(Intent(this@VerficationScreenActivity, ForgetPasswordActivity::class.java))
            finish()
        }
        binding.btnVerify.setOnClickListener {
            if (edit_text_otp_one.toString().isNullOrEmpty() && edit_text_otp_two.toString().isNullOrEmpty() && edit_text_otp_three.toString().isNullOrEmpty() && edit_text_otp_four.toString().isNullOrEmpty()){
                Toast.makeText(this,getString(R.string.toast_verfication_button_tapped),Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this,getString(R.string.toast_enter_otp),Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initialSetup() {
        supportActionBar?.hide()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_verfication_screen)
        setSpannableText()
        binding.apply {
            viewModel = verficationViewModel
            lifecycleOwner = this@VerficationScreenActivity
        }
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    private fun setSpannableText() {
        val spannable = SpannableString(binding.textviewUpdateNumber.text)
        val clickableSpan2: ClickableSpan = object : ClickableSpan() {
            override fun onClick(p0: View) {
                val signInIntent = Intent(this@VerficationScreenActivity, ForgetPasswordActivity::class.java)
                startActivity(signInIntent)
                finish()
            }

            override fun updateDrawState(ds: TextPaint) {
                ds.setColor(ContextCompat.getColor(applicationContext, R.color.green_500))
                ds.bgColor = ContextCompat.getColor(applicationContext, R.color.white)
            }
        }
        spannable.setSpan(clickableSpan2, TWENTY, THIRTYTHREE, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.textviewUpdateNumber.text = spannable
        binding.textviewUpdateNumber.movementMethod = LinkMovementMethod.getInstance()
    }
}