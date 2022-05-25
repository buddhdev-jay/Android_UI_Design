package com.example.androiduidesign.authencation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
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
import com.example.androiduidesign.utils.ZERO
import com.example.androiduidesign.utils.getSpannable
import kotlinx.android.synthetic.main.activity_verfication_screen.edit_text_otp_four
import kotlinx.android.synthetic.main.activity_verfication_screen.edit_text_otp_one
import kotlinx.android.synthetic.main.activity_verfication_screen.edit_text_otp_three
import kotlinx.android.synthetic.main.activity_verfication_screen.edit_text_otp_two

class VerficationScreenActivity : AppCompatActivity(),View.OnClickListener {
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
                val hideKeyboard = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                hideKeyboard?.hideSoftInputFromWindow(binding.editTextOtpFour.windowToken, ZERO)
            }
            if (it.isEmpty()) {
                binding.editTextOtpThree.requestFocus()
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
            onClickHandler = this@VerficationScreenActivity
        }
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        countDownTimer()
    }

    private fun countDownTimer() {
        object : CountDownTimer(60000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                val m = (millisUntilFinished / 1000) / 60
                val s = (millisUntilFinished / 1000) % 60
                val format = String.format(getString(R.string.otp_time_format), m, s)
                binding.textViewTextResendCode.text = getString(R.string.text_resend_code,format)
            }

            override fun onFinish() {
                binding.textViewTextResendCode.text = (getString(R.string.text_request_again))
            }
        }.start()
    }



    private fun setSpannableText() {
        val spannable = getSpannable(binding.textviewUpdateNumber.text.toString(), TWENTY, THIRTYTHREE, ContextCompat.getColor(this@VerficationScreenActivity, R.color.green_500)) {
            val signInIntent = Intent(this@VerficationScreenActivity, ForgetPasswordActivity::class.java)
            startActivity(signInIntent)
            finish()
        }
        binding.textviewUpdateNumber.apply {
            text = spannable
            movementMethod = LinkMovementMethod.getInstance()
        }
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.btn_verify -> {
                if (edit_text_otp_one.text.toString().isEmpty() || edit_text_otp_two.text.toString().isEmpty() || edit_text_otp_three.text.toString().isEmpty() || edit_text_otp_four.text.toString().isEmpty()){
                    Toast.makeText(this,getString(R.string.toast_enter_otp),Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this,getString(R.string.toast_verfication_button_tapped),Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@VerficationScreenActivity,NewPasswordActivity::class.java))
                    finish()
                }
            }
            R.id.img_view_back_arrow_otp -> {
                startActivity(Intent(this@VerficationScreenActivity, ForgetPasswordActivity::class.java))
                finish()
            }
        }
    }
}