package com.example.androiduidesign.authentication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.androiduidesign.R
import com.example.androiduidesign.databinding.ActivityVerficationScreenBinding
import com.example.androiduidesign.utils.THIRTYTHREE
import com.example.androiduidesign.utils.TWENTY
import com.example.androiduidesign.utils.getSpannable
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
        countDownTimer()
    }

    private fun countDownTimer() {
        object : CountDownTimer(60000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                val m = (millisUntilFinished / 1000) / 60
                val s = (millisUntilFinished / 1000) % 60
                val format = String.format("%02d:%02d", m, s)
                binding.textViewTextResendCode.text = getString(R.string.text_resend_code,format)
            }

            override fun onFinish() {
                binding.textViewTextResendCode.setText("done!")
            }
        }.start()
    }



    private fun setSpannableText() {
        val spannable = getSpannable(binding.textviewUpdateNumber.text.toString(), TWENTY, THIRTYTHREE, ContextCompat.getColor(this@VerficationScreenActivity, R.color.green_500)) {
            val signInIntent = Intent(this@VerficationScreenActivity, ForgetPasswordActivity::class.java)
            startActivity(signInIntent)
            finish()
        }
        binding.textviewUpdateNumber.text = spannable
    }
}