package com.example.androiduidesign.onboardingscreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androiduidesign.databinding.SplashScreenItemContainerLayoutBinding

class OnboardingAdapter(private val onBoardingItems: ArrayList<OnBoardingItemsModel>) : RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder>() {
    lateinit var binding : SplashScreenItemContainerLayoutBinding

    class OnboardingViewHolder(binding:SplashScreenItemContainerLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        //Will be Implemented Later
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingViewHolder {
        binding = SplashScreenItemContainerLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return OnboardingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OnboardingViewHolder, position: Int) {
      binding.viewModel = onBoardingItems[position]
    }

    override fun getItemCount(): Int {
        return onBoardingItems.size
    }
}