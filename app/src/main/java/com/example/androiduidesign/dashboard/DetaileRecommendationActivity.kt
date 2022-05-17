package com.example.androiduidesign.dashboard

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import com.example.androiduidesign.R
import com.example.androiduidesign.databinding.ActivityDetaileRecommendationBinding
import com.example.androiduidesign.databinding.ActivityHomeScreenBinding

class DetaileRecommendationActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetaileRecommendationBinding
    private var detailItemList: ArrayList<DetailRecommendationModel> = arrayListOf()
    var detailRecommendationsadapter: DetaileRecommendationAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detaile_recommendation)
    }

    private fun initialSetup() {
        supportActionBar?.hide()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detaile_recommendation)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setAdapter()
    }

    private fun setAdapter() {
        TODO("Not yet implemented")
    }
}