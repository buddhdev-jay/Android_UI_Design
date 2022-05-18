package com.example.androiduidesign.onboardingscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.example.androiduidesign.R
import com.example.androiduidesign.authentication.SignInActivity
import com.example.androiduidesign.databinding.ActivityOnBoardingBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_on_boarding.btn_onBoading
import kotlinx.android.synthetic.main.activity_on_boarding.tab_layout
import kotlinx.android.synthetic.main.activity_on_boarding.viewpager_onboarding

class OnBoardingActivity : AppCompatActivity() {
    lateinit var binding: ActivityOnBoardingBinding
    var OnboardingItemList: ArrayList<OnBoardingItemsModel> = arrayListOf()
    var onBoardingadapter: OnboardingAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_on_boarding)
        supportActionBar?.hide()
        setAdapter()
        TabLayoutMediator(tab_layout, binding.viewpagerOnboarding) { tab, position -> }.attach()
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        binding.btnOnBoading.setOnClickListener {
            if (viewpager_onboarding.currentItem + 1 < onBoardingadapter?.itemCount ?: 0) {
                viewpager_onboarding.setCurrentItem(viewpager_onboarding.currentItem + 1)
            } else {
                startActivity(Intent(this,SignInActivity::class.java))
                finish()
            }
        }

        binding.viewpagerOnboarding.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (position + 1 < onBoardingadapter?.itemCount ?: 0) {
                    btn_onBoading.text = getString(R.string.splash_screen_btn_txt_continue)
                } else {
                    btn_onBoading.text = getString(R.string.splash_screen_btn_txt_get_started)
                }
                super.onPageSelected(position)
            }
        })
    }

    private fun setAdapter() {
        setData()
        onBoardingadapter = OnboardingAdapter(OnboardingItemList)
        binding.viewpagerOnboarding.adapter = onBoardingadapter
    }

    private fun setData() {
        OnboardingItemList.apply {
            add(OnBoardingItemsModel(R.drawable.splash_screen_one, getString(R.string.onboarding_title_item_one),getString(R.string.onboarding_des_item_one)))
            add(OnBoardingItemsModel(R.drawable.splash_screen_two, getString(R.string.onboarding_title_item_two), getString(R.string.onboarding_des_item_two)))
            add(OnBoardingItemsModel(R.drawable.splash_screen_three, getString(R.string.onboarding_title_item_three), getString(R.string.onboarding_des_item_three)))
        }
    }
}