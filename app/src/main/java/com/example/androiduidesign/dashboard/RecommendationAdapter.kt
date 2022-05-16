package com.example.androiduidesign.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androiduidesign.databinding.CategoriesItemLayoutBinding
import com.example.androiduidesign.databinding.RecommendationItemLayoutBinding

class RecommendationAdapter(private val RecommendationsItems: ArrayList<RecommendationItemModel>) :
    RecyclerView.Adapter<RecommendationAdapter.RecommendationViewHolder>() {
    lateinit var binding: RecommendationItemLayoutBinding

    class RecommendationViewHolder(binding: RecommendationItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendationViewHolder {
        binding = RecommendationItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RecommendationAdapter.RecommendationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecommendationViewHolder, position: Int) {
        binding.viewModel = RecommendationsItems[position]
    }

    override fun getItemCount(): Int {
        return RecommendationsItems.size
    }
}