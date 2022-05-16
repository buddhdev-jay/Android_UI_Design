package com.example.androiduidesign.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import com.example.androiduidesign.R
import com.example.androiduidesign.databinding.ActivityHomeScreenBinding
import com.example.androiduidesign.databinding.ActivitySignInBinding

class HomeScreenActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialSetup()
    }

    private fun initialSetup() {
        supportActionBar?.hide()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home_screen)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }
}