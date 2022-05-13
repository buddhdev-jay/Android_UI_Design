package com.example.androiduidesign.onboardingscreen

import android.database.DatabaseUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.example.androiduidesign.ONE
import com.example.androiduidesign.R
import com.example.androiduidesign.ZERO
import com.example.androiduidesign.databinding.ActivityOnBoardingBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_on_boarding.btn_onBoading
import kotlinx.android.synthetic.main.activity_on_boarding.tab_layout
import kotlinx.android.synthetic.main.activity_on_boarding.viewpager_onboarding

class OnBoardingActivity : AppCompatActivity() {
    lateinit var binding: ActivityOnBoardingBinding
    var onBoardingItemList: ArrayList<OnBoardingItemsModel> = arrayListOf()
    var onBoardingadapter: OnboardingAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialSetup()
        binding.btnOnBoading.setOnClickListener {
            if (viewpager_onboarding.currentItem + 1 < onBoardingadapter?.itemCount ?: ZERO) {
                viewpager_onboarding.setCurrentItem(viewpager_onboarding.currentItem + ONE)
            } else {
                btn_onBoading.text = getString(R.string.splash_screen_btn_txt_get_started)
                Toast.makeText(this,getString(R.string.toast_test_get_started),Toast.LENGTH_SHORT).show()
            }
        }
        binding.viewpagerOnboarding.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (position + 1 < onBoardingadapter?.itemCount ?: ZERO) {
                    btn_onBoading.text = getString(R.string.splash_screen_btn_txt_continue)
                } else {
                    btn_onBoading.text = getString(R.string.splash_screen_btn_txt_get_started)
                }
                super.onPageSelected(position)
            }
        })
    }

    private fun initialSetup() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_on_boarding)
        supportActionBar?.hide()
        setAdapter()
        TabLayoutMediator(tab_layout, binding.viewpagerOnboarding) { tab, position -> }.attach()
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    private fun setAdapter() {
        setData()
        onBoardingadapter = OnboardingAdapter(onBoardingItemList)
        binding.viewpagerOnboarding.adapter = onBoardingadapter
    }

    private fun setData() {
        onBoardingItemList.apply {
            add(OnBoardingItemsModel(R.drawable.splash_screen_one, getString(R.string.onboarding_title_item_one),getString(R.string.onboarding_des_item_one)))
            add(OnBoardingItemsModel(R.drawable.splash_screen_two, getString(R.string.onboarding_title_item_two), getString(R.string.onboarding_des_item_two)))
            add(OnBoardingItemsModel(R.drawable.splash_screen_three, getString(R.string.onboarding_title_item_three), getString(R.string.onboarding_des_item_three)))
        }
    }
}