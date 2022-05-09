package com.example.androiduidesign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import com.example.androiduidesign.onboardingscreen.OnBoardingActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        initialSetup()
    }
    private fun initialSetup() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this,OnBoardingActivity::class.java))
            finish()
        }, TWOTHOUSAND.toLong())
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }
}