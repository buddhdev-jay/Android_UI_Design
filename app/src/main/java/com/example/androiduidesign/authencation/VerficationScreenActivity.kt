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
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import com.example.androiduidesign.R
import com.example.androiduidesign.databinding.ActivityVerficationScreenBinding
import kotlinx.android.synthetic.main.activity_verfication_screen.edit_text_otp_one

class VerficationScreenActivity : AppCompatActivity() {
    lateinit var binding: ActivityVerficationScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verfication_screen)
        initialSetup()
    }

    private fun initialSetup() {
        supportActionBar?.hide()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_verfication_screen)
        setSpannableText()
        configureOtp(null,binding.editTextOtpOne,binding.editTextOtpTwo)
        configureOtp(binding.editTextOtpOne,binding.editTextOtpTwo,binding.editTextOtpThree)
        configureOtp(binding.editTextOtpTwo,binding.editTextOtpThree,binding.editTextOtpFour)
        configureOtp(binding.editTextOtpThree,binding.editTextOtpFour,null)
    }
    private fun setSpannableText() {
        val spannable = SpannableString(binding.textviewUpdateNumber.text)
        val clickableSpan2: ClickableSpan = object : ClickableSpan() {
            override fun onClick(p0: View) {
                val signInIntent = Intent(this@VerficationScreenActivity,ForgetPasswordActivity::class.java)
                startActivity(signInIntent)
                finish()
            }

            override fun updateDrawState(ds: TextPaint) {
                ds.setColor(ContextCompat.getColor(applicationContext, R.color.green_500))
                ds.bgColor = ContextCompat.getColor(applicationContext, R.color.white)
            }
        }
        spannable.setSpan(clickableSpan2, 20, 33, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.textviewUpdateNumber.text = spannable
        binding.textviewUpdateNumber.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun configureOtp(beforeEdittext: EditText?, currentEdittext: EditText, afterEdittext: EditText?) {
        currentEdittext.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //Will be implemented Later
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, count: Int) {
                if (count == 1) {
                    afterEdittext?.let { afterEdittext.requestFocus() } ?: run {
                        currentEdittext.clearFocus()
                        val hidekeyboard = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                        hidekeyboard?.hideSoftInputFromWindow(currentEdittext.windowToken, 0)
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                //Will be implemented Later
            }

        })
        currentEdittext.setOnKeyListener(View.OnKeyListener { view, i, keyEvent ->
            if (i == KeyEvent.KEYCODE_DEL && currentEdittext.text.isEmpty()) {
               beforeEdittext?.let { beforeEdittext.requestFocus() } ?: run {
                    val hidekeyboard = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                    hidekeyboard?.hideSoftInputFromWindow(currentEdittext.windowToken, 0)
                }
            }
            return@OnKeyListener false
        })
    }
}