package com.example.androiduidesign.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.androiduidesign.databinding.CategoriesItemLayoutBinding
import com.example.androiduidesign.databinding.RecommendationRestaurantsHeaderItemLayoutBinding
import com.example.androiduidesign.databinding.RecommendationRestaurantsSectionItemLayoutBinding

class detaileRecommendationAdapter(private val DetailRecommendationItems: List<DetailRecommendationModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    class ItemHolder(val binding: RecommendationRestaurantsHeaderItemLayoutBinding): RecyclerView.ViewHolder(binding.root)
    class SectionHolder(val binding: RecommendationRestaurantsSectionItemLayoutBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            0 -> {
                val headerBinding = RecommendationRestaurantsHeaderItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ItemHolder(headerBinding)
            }
            else -> {
                val sectionBinding = RecommendationRestaurantsSectionItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                SectionHolder(sectionBinding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(DetailRecommendationItems[position].viewType) {
            0 -> {
                (holder as ItemHolder).binding.viewModel = DetailRecommendationItems[position]
            }
            else -> {
                (holder as SectionHolder).binding.viewModel = DetailRecommendationItems[position]
            }
        }
    }

    override fun getItemCount(): Int {
       return DetailRecommendationItems.size
    }

    override fun getItemViewType(position: Int): Int {
        return DetailRecommendationItems[position].viewType
    }
}


