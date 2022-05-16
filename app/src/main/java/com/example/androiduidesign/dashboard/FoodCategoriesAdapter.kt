package com.example.androiduidesign.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androiduidesign.databinding.FoodCategoriesItemLayoutBinding

class FoodCategoriesAdapter(private val FoodCategoriesItems: ArrayList<RecommendationItemModel>) :
    RecyclerView.Adapter<FoodCategoriesAdapter.FoodCategoriesViewHolder>() {

    class FoodCategoriesViewHolder(binding: FoodCategoriesItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    lateinit var binding: FoodCategoriesItemLayoutBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodCategoriesViewHolder {
        binding = FoodCategoriesItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodCategoriesAdapter.FoodCategoriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodCategoriesViewHolder, position: Int) {
        binding.viewModel =  FoodCategoriesItems[position]
    }

    override fun getItemCount(): Int {
        return FoodCategoriesItems.size
    }
}