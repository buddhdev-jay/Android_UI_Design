package com.example.androiduidesign.dashboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.androiduidesign.R
import com.example.androiduidesign.databinding.ActivityHomeScreenBinding

class HomeScreenActivity : AppCompatActivity(),View.OnClickListener{
    lateinit var binding: ActivityHomeScreenBinding
    private var categoriesItemList: ArrayList<CategoriesItemModel> = arrayListOf()
    var categoriesadapter: CategoriesAdapter? = null
    var recommendationItemList: ArrayList<RecommendationItemModel> = arrayListOf()
    var recommendationadapter: RecommendationAdapter? = null
    var discountItemList: ArrayList<DiscountItemModel> = arrayListOf()
    var discountadapter: DiscountAdapter? = null
    var foodCategoriesadapter: FoodCategoriesAdapter? = null
    var cateegoriesItemListTakasimuraaa :ArrayList<RecommendationItemModel> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialSetup()
    }

    private fun initialSetup() {
        supportActionBar?.hide()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home_screen)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setAdapter()
        binding.onClickHandler = this@HomeScreenActivity

    }

    private fun setAdapter() {
        setData()
        categoriesadapter = CategoriesAdapter(categoriesItemList)
        binding.recyclerviewCategories.adapter = categoriesadapter
        recommendationadapter = RecommendationAdapter(recommendationItemList)
        binding.recyclerviewRecommendation.adapter = recommendationadapter
        discountadapter = DiscountAdapter(discountItemList)
        binding.recyclerviewDiscount.adapter = discountadapter
        foodCategoriesadapter = FoodCategoriesAdapter(cateegoriesItemListTakasimuraaa)
        binding.recyclerviewCategoriesItemOne.adapter = foodCategoriesadapter
    }

    private fun setData() {
        categoriesItemList.apply {
            add(CategoriesItemModel(R.drawable.nearest_icon,"Nearest"))
            add(CategoriesItemModel(R.drawable.new_food_icon,"New Foods"))
            add(CategoriesItemModel(R.drawable.best_seller_icon,"Best Seller"))
            add(CategoriesItemModel(R.drawable.healthy_icon,"Healthy"))
        }
        recommendationItemList.apply {
            add(RecommendationItemModel(R.drawable.categories_item_two_image, getString(R.string.categories_item_two), getString(R.string.distance_1_02), getString(R.string.text_14_mins), getString(R.string.text_4_9_rating), getString(R.string.categories_item_one_rating), getString(R.string.text_discount_fifteen)))
            add(RecommendationItemModel(R.drawable.categories_item_three_image, getString(R.string.categories_item_three),  getString(R.string.distance_1_02), getString(R.string.text_14_mins), getString(R.string.text_4_9_rating), getString(R.string.categories_item_one_rating), getString(R.string.text_discount_fifteen)))
            add(RecommendationItemModel(R.drawable.categories_item_one_image, getString(R.string.categories_title_one), getString(R.string.distance_1_02), getString(R.string.text_14_mins), getString(R.string.text_4_9_rating), getString(R.string.categories_item_one_rating), getString(R.string.text_discount_fifteen)))
        }
        discountItemList.apply {
            add(DiscountItemModel(R.drawable.discount_item_one))
            add(DiscountItemModel(R.drawable.discount_item_two))
        }
        cateegoriesItemListTakasimuraaa.apply {
            add(RecommendationItemModel(R.drawable.categories_item_one_image, getString(R.string.categories_title_one), getString(R.string.distance_1_02), getString(R.string.text_14_mins), getString(R.string.text_4_9_rating), getString(R.string.categories_item_one_rating), getString(R.string.text_discount_fifteen)))
            add(RecommendationItemModel(R.drawable.categories_item_two_image, getString(R.string.categories_item_two), getString(R.string.distance_1_02), getString(R.string.text_14_mins), getString(R.string.text_4_9_rating), getString(R.string.categories_item_one_rating), getString(R.string.text_discount_fifteen)))
            add(RecommendationItemModel(R.drawable.categories_item_three_image, getString(R.string.categories_item_three),  getString(R.string.distance_1_02), getString(R.string.text_14_mins), getString(R.string.text_4_9_rating), getString(R.string.categories_item_one_rating), getString(R.string.text_discount_fifteen)))
        }

    }

    override fun onClick(p0: View?) {
        when (p0?.id){
            R.id.text_view_see_all -> {
                startActivity(Intent(this@HomeScreenActivity,DetaileRecommendationActivity::class.java))
            }
            R.id.text_view_see_all_categories -> {
                Toast.makeText(this@HomeScreenActivity,getString(R.string.toast_see_all_categories_clicked),Toast.LENGTH_SHORT).show()
            }
            R.id.img_view_plus,R.id.text_view_plus -> {
                Toast.makeText(this@HomeScreenActivity,getString(R.string.toast_plus_button_tapped),Toast.LENGTH_SHORT).show()
            }
            R.id.img_view_pay,R.id.text_view_pay -> {
                Toast.makeText(this@HomeScreenActivity,getString(R.string.toast_pay_button_tapped),Toast.LENGTH_SHORT).show()
            }
        }
    }
}