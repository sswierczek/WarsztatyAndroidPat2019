package com.intive.warsztatyandroid.home.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.intive.warsztatyandroid.R
import com.intive.warsztatyandroid.android.collections.replaceAllItems
import com.intive.warsztatyandroid.android.view.loadImage
import com.intive.warsztatyandroid.home.model.data.HomeItem

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private val homeItems = mutableListOf<HomeItem>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.home_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        homeItems[position].run {
            holder.homeTitle.text = title
            holder.homeImage.loadImage(thumbnailUrl)
        }

    }

    override fun getItemCount() = homeItems.size

    fun updateItems(newItems: List<HomeItem>) {
        homeItems.replaceAllItems(newItems)
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val homeTitle: TextView = view.findViewById(R.id.homeTitle)
        val homeImage: ImageView = view.findViewById(R.id.homeImage)
    }
}