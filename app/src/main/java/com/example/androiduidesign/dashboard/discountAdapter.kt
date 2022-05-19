package com.example.androiduidesign.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.example.androiduidesign.databinding.DiscountItemLayoutBinding

class discountAdapter(private val DiscountItems: ArrayList<DiscountItemModel>) :
    RecyclerView.Adapter<discountAdapter.DiscountViewHolder>()  {

    class DiscountViewHolder(private val binding: DiscountItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(itemViewModel: DiscountItemModel) {
            binding.setVariable(BR.dataModel, itemViewModel)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscountViewHolder {
       val binding = DiscountItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return discountAdapter.DiscountViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DiscountViewHolder, position: Int) {
        holder.bind(DiscountItems[position])
    }

    override fun getItemCount(): Int {
        return DiscountItems.size
    }
}