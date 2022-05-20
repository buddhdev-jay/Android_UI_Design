package com.example.androiduidesign.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.example.androiduidesign.databinding.FoodCategoriesItemLayoutBinding

class FoodCategoriesAdapter(private val FoodCategoriesItems: ArrayList<RecommendationItemModel>) :
    RecyclerView.Adapter<FoodCategoriesAdapter.FoodCategoriesViewHolder>() {

    class FoodCategoriesViewHolder(private val binding: FoodCategoriesItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(itemViewModel: RecommendationItemModel) {
            binding.setVariable(BR.dataModel, itemViewModel)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodCategoriesViewHolder {
        val binding = FoodCategoriesItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodCategoriesAdapter.FoodCategoriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodCategoriesViewHolder, position: Int) {
        holder.bind(FoodCategoriesItems[position])
    }

    override fun getItemCount(): Int = FoodCategoriesItems.size
}