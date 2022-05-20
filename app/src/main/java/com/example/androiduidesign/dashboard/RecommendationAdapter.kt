package com.example.androiduidesign.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.example.androiduidesign.databinding.CategoriesItemLayoutBinding
import com.example.androiduidesign.databinding.RecommendationItemLayoutBinding

class RecommendationAdapter(private val RecommendationsItems: ArrayList<RecommendationItemModel>) :
    RecyclerView.Adapter<RecommendationAdapter.RecommendationViewHolder>() {

    class RecommendationViewHolder(private val binding: RecommendationItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(itemViewModel: RecommendationItemModel) {
            binding.setVariable(BR.dataModel, itemViewModel)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendationViewHolder {
        val binding = RecommendationItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecommendationAdapter.RecommendationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecommendationViewHolder, position: Int) =  holder.bind(RecommendationsItems[position])

    override fun getItemCount(): Int = RecommendationsItems.size
}