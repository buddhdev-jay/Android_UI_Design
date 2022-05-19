package com.example.androiduidesign.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.example.androiduidesign.databinding.CategoriesItemLayoutBinding

class CategoriesAdapter(private val CategoriesItems: ArrayList<CategoriesItemModel>) :
    RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    class CategoriesViewHolder(private val binding: CategoriesItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(itemViewModel: CategoriesItemModel) {
            binding.setVariable(BR.dataModel, itemViewModel)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
       val binding = CategoriesItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.bind(CategoriesItems[position])
    }

    override fun getItemCount(): Int {
        return CategoriesItems.size
    }
}