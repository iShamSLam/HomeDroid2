package com.example.homedroid2.Components

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.homedroid2.R
import com.example.homedroid2.R.layout.recycler_view_item
import com.example.homedroid2.model.Meals.Meal
import com.squareup.picasso.Picasso

class MealAdapter(diffCallback: DiffUtil.ItemCallback<Meal>?) :
    PagedListAdapter<Meal, MealAdapter.BooksViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        return BooksViewHolder(
            LayoutInflater.from(parent.context).inflate(recycler_view_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        holder.bindBooks(getItem(position)!!)
    }

    class BooksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView? = itemView.findViewById(R.id.tv_meal)
        val mealImageVIew: ImageView? = itemView?.findViewById(R.id.iv_meal)
        val tag: TextView? = itemView?.findViewById(R.id.tv_tag)
        fun bindBooks(meals: Meal) {

            titleTextView?.text = meals.strArea
            tag?.text = meals.strCategory
            Picasso.get().load(meals.strMealThumb).into(mealImageVIew)
            }
        }
    }
}