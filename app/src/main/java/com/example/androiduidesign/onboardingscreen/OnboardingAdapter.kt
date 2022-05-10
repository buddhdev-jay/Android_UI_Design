package com.example.androiduidesign.onboardingscreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androiduidesign.R

class OnboardingAdapter(val onBoardingItems: ArrayList<OnBoardingItemsModel>) :
    RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder>() {

    class OnboardingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val splashScreenImageView: ImageView = itemView.findViewById(R.id.imgview_splash_screen)
        val splashScreenHeadertextView: TextView =
            itemView.findViewById(R.id.txtview_splash_screen_heading)
        val splashScreenTagLinetextView: TextView =
            itemView.findViewById(R.id.txtview_splash_screen_tagline)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.splash_screen_item_container_layout, parent, false)
        return OnboardingViewHolder(view)
    }

    override fun onBindViewHolder(holder: OnboardingViewHolder, position: Int) {
        holder.apply {
            with(onBoardingItems[position]) {
                splashScreenHeadertextView.text = title
                splashScreenImageView.setImageResource(image)
                splashScreenTagLinetextView.text = description
            }
        }
    }

    override fun getItemCount(): Int {
        return onBoardingItems.size
    }
}