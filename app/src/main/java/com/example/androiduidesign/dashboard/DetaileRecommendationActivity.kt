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
        detailRecommendationsadapter = DetaileRecommendationAdapter(mutableListOf<DetailRecommendationModel>().apply {
            add(DetailRecommendationModel(1,null,null,null,null,null,null,R.drawable.recommendation_item_section_one,getString(R.string.text_family_package),getString(R.string.food_item_description),"\$12.00",getString(R.string.zero_quantity)))
            add(DetailRecommendationModel(0,R.drawable.recommendation_res_header_one,getString(R.string.resturant_name_sample),getString(R.string.resurant_des_one),getString(R.string.four_five_rating),getString(R.string.zero_one_kelometer),getString(R.string.thirteen_minutes),null,null,null,null,null))
            add(DetailRecommendationModel(0,R.drawable.recommendation_res_header_one,getString(R.string.resturant_name_sample),getString(R.string.resurant_des_one),getString(R.string.four_five_rating),getString(R.string.zero_one_kelometer),getString(R.string.thirteen_minutes),null,null,null,null,null))
            add(DetailRecommendationModel(0,R.drawable.recommendation_res_header_one,getString(R.string.resturant_name_sample),getString(R.string.resurant_des_one),getString(R.string.four_five_rating),getString(R.string.zero_one_kelometer),getString(R.string.thirteen_minutes),null,null,null,null,null))
            add(DetailRecommendationModel(0,R.drawable.recommendation_res_header_one,getString(R.string.resturant_name_sample),getString(R.string.resurant_des_one),getString(R.string.four_five_rating),getString(R.string.zero_one_kelometer),getString(R.string.thirteen_minutes),null,null,null,null,null))
            add(DetailRecommendationModel(0,R.drawable.recommendation_res_header_one,getString(R.string.resturant_name_sample),getString(R.string.resurant_des_one),getString(R.string.four_five_rating),getString(R.string.zero_one_kelometer),getString(R.string.thirteen_minutes),null,null,null,null,null))
            add(DetailRecommendationModel(0,R.drawable.recommendation_res_header_one,getString(R.string.resturant_name_sample),getString(R.string.resurant_des_one),getString(R.string.four_five_rating),getString(R.string.zero_one_kelometer),getString(R.string.thirteen_minutes),null,null,null,null,null))
        })
        binding.recyclerviewDeatilRecommendation.adapter = detailRecommendationsadapter
    }

}