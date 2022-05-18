package com.example.androiduidesign.authencation

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Patterns
import android.view.View
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.androiduidesign.R
import com.example.androiduidesign.databinding.ActivitySignupBinding
import com.example.androiduidesign.onboardingscreen.OnBoardingActivity
import com.example.androiduidesign.utils.NINETEEN
import com.example.androiduidesign.utils.ONE
import com.example.androiduidesign.utils.TWENTYSIX
import com.example.androiduidesign.utils.ZERO
import com.example.androiduidesign.webservice.without_retrofit.HTTPCallback
import java.net.URL
import kotlinx.android.synthetic.main.activity_signup.btn_signup
import kotlinx.android.synthetic.main.activity_signup.edi_txt_signup_email
import kotlinx.android.synthetic.main.activity_signup.edi_txt_signup_full_name
import kotlinx.android.synthetic.main.activity_signup.edi_txt_signup_password
import kotlinx.android.synthetic.main.activity_signup.img_view_back_arrow
import org.json.JSONObject


class SignupActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding
    val signupViewModel : SignupViewModel by viewModels()
    lateinit var adapter: ArrayAdapter<CharSequence>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intialSetup()
        img_view_back_arrow.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }
        btn_signup.setOnClickListener {
            binding.apply {
                when {
                    this.ediTxtSignupFullName.text.toString().isEmpty() -> {
                        Toast.makeText(this@SignupActivity,getString(R.string.toast_name_empty),Toast.LENGTH_SHORT).show()
                    }
                    this.ediTxtSignupEmail.text.toString().isEmpty() -> {
                        Toast.makeText(this@SignupActivity,getString(R.string.toast_email_empty),Toast.LENGTH_SHORT).show()
                    }
                    this.ediTxtSignupPassword.text.toString().isEmpty() -> {
                        Toast.makeText(this@SignupActivity,getString(R.string.toast_password_empty),Toast.LENGTH_SHORT).show()
                    }
                    this.ediTxtSignupConfirmPassword.text.toString().isEmpty() -> {
                        Toast.makeText(this@SignupActivity,getString(R.string.toast_confirm_password),Toast.LENGTH_SHORT).show()
                    }
                    this.ediTxtSignupPhoneNumber.text.toString().isEmpty() -> {
                        Toast.makeText(this@SignupActivity,getString(R.string.toast_phone_empty) ,Toast.LENGTH_SHORT).show()
                    }
                    this.ediTxtSignupPhoneNumber.text.toString().length < 10 -> {
                        Toast.makeText(this@SignupActivity,getString(R.string.toast_valid_phone),Toast.LENGTH_SHORT).show()
                    }
                    !Patterns.EMAIL_ADDRESS.matcher(this.ediTxtSignupEmail.text.toString()).matches() -> {
                        Toast.makeText(this@SignupActivity,getString(R.string.toast_email_not_valid),Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        val cred = JSONObject()
                        cred.put("name",edi_txt_signup_full_name.text.toString())
                        cred.put("email", edi_txt_signup_email.text.toString())
                        cred.put("passsword", edi_txt_signup_password.text.toString())
                        signupViewModel.apiCall(cred,"https://6283714992a6a5e46223ee72.mockapi.io/api/v1/createUser" as URL,"POST",object :HTTPCallback{
                            override fun successCallback(output: String) {
                                Toast.makeText(this@SignupActivity,output,Toast.LENGTH_SHORT).show()
                            }
                            override fun failerCallback(responseCode: Int, output: String) {
                                Toast.makeText(this@SignupActivity,output,Toast.LENGTH_SHORT).show()
                            }

                        })
                    }
                }
            }
        }


        signupViewModel.password.observe(this) { password ->
            password.apply {
                if (this.isNotEmpty()) {
                    var passwordstatusupdate = ONE
                    if (this.length >= 8) {
                        if (this.toString().contains(getString(R.string.numeric_regex).toRegex())) {
                            passwordstatusupdate += ONE
                        }
                        if (this.toString().contains(getString(R.string.small_alphabet_regex).toRegex())) {
                            passwordstatusupdate += ONE
                        }
                        if (this.toString().contains(getString(R.string.upper_alphabet_regex).toRegex())) {
                            passwordstatusupdate += ONE
                        }
                    }
                    signupViewModel.passwordStatus.value = passwordstatusupdate
                } else {
                    signupViewModel.passwordStatus.value = ZERO
                }
            }
        }
    }

    private fun intialSetup() {
        supportActionBar?.hide()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        adapter = ArrayAdapter.createFromResource(this, R.array.countryCodes, android.R.layout.simple_spinner_dropdown_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.apply {
            viewModel = signupViewModel
            spinnerCountryCode.adapter = adapter
            lifecycleOwner = this@SignupActivity
        }
        setSpannableText()
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    private fun setSpannableText() {
        val spannable = SpannableString(binding.textviewNotHaveAccount.text)
        val clickableSpan2: ClickableSpan = object : ClickableSpan() {
            override fun onClick(p0: View) {
                val signInIntent = Intent(this@SignupActivity,SignInActivity::class.java)
                startActivity(signInIntent)
            }

            override fun updateDrawState(ds: TextPaint) {
                ds.setColor(ContextCompat.getColor(applicationContext, R.color.green_500))
                ds.bgColor = ContextCompat.getColor(applicationContext, R.color.white)
            }
        }
        spannable.setSpan(clickableSpan2, NINETEEN, TWENTYSIX, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.textviewNotHaveAccount.text = spannable
        binding.textviewNotHaveAccount.movementMethod = LinkMovementMethod.getInstance()
    }
}