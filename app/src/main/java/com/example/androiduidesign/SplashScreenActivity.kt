package com.example.androiduidesign

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.androiduidesign.authencation.SignInActivity
import com.example.androiduidesign.dashboard.HomeScreenActivity
import com.example.androiduidesign.onboardingscreen.OnBoardingActivity
import com.example.androiduidesign.utils.LOGIN_STATE
import com.example.androiduidesign.utils.SHAREDPREF_NAME
import com.example.androiduidesign.utils.SPLASHSCREEN_STATE
import com.example.androiduidesign.utils.TWOTHOUSAND


class SplashScreenActivity : AppCompatActivity() {
    lateinit var handler : Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        handler  = Handler(Looper.getMainLooper())
        val prefs = getSharedPreferences(SHAREDPREF_NAME, Context.MODE_PRIVATE)
        if(prefs.getBoolean(SPLASHSCREEN_STATE,false)) {
            if(!prefs.getBoolean(LOGIN_STATE,false)) {
                startActivity(Intent(this, SignInActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this, HomeScreenActivity::class.java))
                finish()
            }
        } else {
            prefs.edit().putBoolean(SPLASHSCREEN_STATE,true).apply()
            handler.postDelayed({
                startActivity(Intent(this,OnBoardingActivity::class.java))
                finish()
            }, TWOTHOUSAND.toLong())
        }

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }
}