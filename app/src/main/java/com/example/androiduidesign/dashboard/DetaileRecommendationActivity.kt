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
        initialSetup()
    }

    private fun initialSetup() {
        supportActionBar?.hide()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detaile_recommendation)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setAdapter()
    }

    private fun setAdapter() {
        setData()
        detailRecommendationsadapter = DetaileRecommendationAdapter(detailItemList)
        binding.recyclerviewDeatilRecommendation.adapter = detailRecommendationsadapter
    }

    private fun setData() {
       detailItemList.apply {
            add(DetailRecommendationModel(0,R.drawable.recommendation_res_header_one,"Bamboo Restaurant","Rice, chicken & duck, Snacks","4.5","0.1 km","13 min",null,null,null,null,null))
           add(DetailRecommendationModel(1,null,null,null,null,null,null,R.drawable.recommendation_item_section_one,"Super Family Package","2 Chicken Wings, 2 Rice, ...","\$12.00","0"))
        }
    }
}