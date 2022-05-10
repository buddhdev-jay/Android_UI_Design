package com.example.androiduidesign.onboardingscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.androiduidesign.R
import kotlinx.android.synthetic.main.activity_on_boarding.btn_onBoading
import kotlinx.android.synthetic.main.activity_on_boarding.viewpager_onboarding

class OnBoardingActivity : AppCompatActivity() {

    var OnbaordingItemList: ArrayList<OnBoardingItemsModel> = arrayListOf()
    var onBoardingadapter: OnboardingAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)
        supportActionBar?.hide()
        setData()
        onBoardingadapter = OnboardingAdapter(OnbaordingItemList)
        viewpager_onboarding.adapter = onBoardingadapter
        btn_onBoading.setOnClickListener {
            if (viewpager_onboarding.currentItem + 1 < onBoardingadapter!!.itemCount) {
                viewpager_onboarding.setCurrentItem(viewpager_onboarding.currentItem + 1)
            } else {
                btn_onBoading.text = "Get Started"
            }
        }
        viewpager_onboarding.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (position + 1 < onBoardingadapter!!.itemCount) {
                    btn_onBoading.text = "Continue"
                } else {
                    btn_onBoading.text = "Get Started"
                }
                super.onPageSelected(position)
            }
        })
    }

    private fun setData() {
        OnbaordingItemList.apply {
            add(OnBoardingItemsModel(R.drawable.splash_screen_one, getString(R.string.onboarding_title_item_one), getString(
                        R.string.onboarding_des_item_one)))
            add(OnBoardingItemsModel(R.drawable.splash_screen_two, getString(R.string.onboarding_title_item_two), getString(R.string.onboarding_des_item_two)))
            add(OnBoardingItemsModel(R.drawable.splash_screen_three, getString(R.string.onboarding_title_item_three), getString(R.string.onboarding_des_item_three))
            )
        }
    }
}