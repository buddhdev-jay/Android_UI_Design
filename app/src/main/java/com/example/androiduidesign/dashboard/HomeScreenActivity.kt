package com.example.androiduidesign.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import com.example.androiduidesign.R
import com.example.androiduidesign.databinding.ActivityHomeScreenBinding
import com.example.androiduidesign.databinding.ActivitySignInBinding
import com.example.androiduidesign.onboardingscreen.OnBoardingItemsModel
import com.example.androiduidesign.onboardingscreen.OnboardingAdapter

class HomeScreenActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeScreenBinding
    var CategoriesItemList: ArrayList<CategoriesItemModel> = arrayListOf()
    var Categoriesadapter: CategoriesAdapter? = null
    var RecommendationItemList: ArrayList<RecommendationItemModel> = arrayListOf()
    var Recommendationadapter: RecommendationAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialSetup()
    }

    private fun initialSetup() {
        supportActionBar?.hide()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home_screen)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setAdapter()

    }

    private fun setAdapter() {
        setData()
        Categoriesadapter = CategoriesAdapter(CategoriesItemList)
        binding.recyclerviewCategories.adapter = Categoriesadapter
        Recommendationadapter = RecommendationAdapter(RecommendationItemList)
        binding.recyclerviewRecommendation.adapter = Recommendationadapter
    }

    private fun setData() {
        CategoriesItemList.apply {
            add(CategoriesItemModel(R.drawable.nearest_icon,"Nearest"))
            add(CategoriesItemModel(R.drawable.new_food_icon,"New Foods"))
            add(CategoriesItemModel(R.drawable.best_seller_icon,"Best Seller"))
            add(CategoriesItemModel(R.drawable.healthy_icon,"Healthy"))
        }
        RecommendationItemList.apply {
            add(RecommendationItemModel(R.drawable.recommendation_image_one,"Nasi Goreng  Dower, Rumbai","1.02 km","8 min","4.9","6rb+ ratings","15%"))
            add(RecommendationItemModel(R.drawable.recommendation_image_one,"Nasi Goreng  Dower, Rumbai","1.02 km","8 min","4.9","6rb+ ratings","15%"))
            add(RecommendationItemModel(R.drawable.recommendation_image_one,"Nasi Goreng  Dower, Rumbai","1.02 km","8 min","4.9","6rb+ ratings","15%"))
            add(RecommendationItemModel(R.drawable.recommendation_image_one,"Nasi Goreng  Dower, Rumbai","1.02 km","8 min","4.9","6rb+ ratings","15%"))
        }
    }
}