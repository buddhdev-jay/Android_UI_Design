package com.example.androiduidesign.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androiduidesign.databinding.CategoriesItemLayoutBinding
import com.example.androiduidesign.databinding.RecommendationRestaurantsHeaderItemLayoutBinding
import com.example.androiduidesign.databinding.RecommendationRestaurantsSectionItemLayoutBinding

class DetaileRecommendationAdapter(private val DetailRecommendationItems: ArrayList<DetailRecommendationModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var headerbinding: RecommendationRestaurantsHeaderItemLayoutBinding
    lateinit var sectionbinding: RecommendationRestaurantsSectionItemLayoutBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 0) {
            headerbinding = RecommendationRestaurantsHeaderItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return DetaileRecommendationAdapter.HeaderViewHolder(headerbinding)
        } else {
            sectionbinding =RecommendationRestaurantsSectionItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return DetaileRecommendationAdapter.SectionViewHolder(sectionbinding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        headerbinding.viewModel = DetailRecommendationItems[position]
        sectionbinding.viewModel = DetailRecommendationItems[position]
    }

    override fun getItemCount(): Int {
       return DetailRecommendationItems.size
    }
    class SectionViewHolder(binding: RecommendationRestaurantsSectionItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    class HeaderViewHolder(binding: RecommendationRestaurantsHeaderItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }
}


