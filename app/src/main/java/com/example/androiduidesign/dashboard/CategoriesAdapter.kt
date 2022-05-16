package com.example.androiduidesign.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androiduidesign.databinding.CategoriesItemLayoutBinding
import com.example.androiduidesign.databinding.SplashScreenItemContainerLayoutBinding
import com.example.androiduidesign.onboardingscreen.OnBoardingItemsModel
import com.example.androiduidesign.onboardingscreen.OnboardingAdapter

class CategoriesAdapter(private val CategoriesItems: ArrayList<CategoriesItemModel>) :
    RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    lateinit var binding: CategoriesItemLayoutBinding

    class CategoriesViewHolder(binding: CategoriesItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        binding =
            CategoriesItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        binding.viewModel = CategoriesItems[position]
    }

    override fun getItemCount(): Int {
        return CategoriesItems.size
    }
}