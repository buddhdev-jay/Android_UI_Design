package com.example.androiduidesign

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.androiduidesign.onboardingscreen.OnBoardingActivity
import com.example.androiduidesign.utils.TWOTHOUSAND


class SplashScreenActivity : AppCompatActivity() {
    lateinit var handler : Handler
    lateinit var prefs :SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        initialSetup()
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }

    private fun initialSetup() {
        supportActionBar?.hide()
        handler  = Handler(Looper.getMainLooper())
        handler.postDelayed({
            val state: Boolean = prefs.getBoolean("lockedState", null == true)
            startActivity(Intent(this,OnBoardingActivity::class.java))
            finish()
        }, TWOTHOUSAND.toLong())
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        prefs = getSharedPreferences("spalashScreenSharedPref", Context.MODE_PRIVATE)
        prefs.edit().putBoolean("lockedState", true).apply()
    }
}