package com.example.androiduidesign.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.example.androiduidesign.databinding.FoodCategoriesItemLayoutBinding

class foodCategoriesAdapter(private val FoodCategoriesItems: ArrayList<RecommendationItemModel>) :
    RecyclerView.Adapter<foodCategoriesAdapter.FoodCategoriesViewHolder>() {

    class FoodCategoriesViewHolder(private val binding: FoodCategoriesItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(itemViewModel: RecommendationItemModel) {
            binding.setVariable(BR.dataModel, itemViewModel)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodCategoriesViewHolder {
        val binding = FoodCategoriesItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return foodCategoriesAdapter.FoodCategoriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodCategoriesViewHolder, position: Int) {
        holder.bind(FoodCategoriesItems[position])
    }

    override fun getItemCount(): Int {
        return FoodCategoriesItems.size
    }
}