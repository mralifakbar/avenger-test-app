package com.mralifakbar.avenger.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mralifakbar.avenger.R
import com.mralifakbar.avenger.data.model.Hero
import com.mralifakbar.avenger.databinding.ItemHeroesBinding

class ListHeroAdapter(var onItemClick: OnItemClick): ListAdapter<Hero, ListHeroAdapter.ViewHolder>(
    DIFF_CALLBACK
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        ItemHeroesBinding.inflate(LayoutInflater.from(parent.context), parent, false).also {
            return ViewHolder(it)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(val binding: ItemHeroesBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(hero: Hero) {
            with(binding) {
                tvHeroesName.text = hero.name
                tvHeroesRating.text = hero.rating

                if (hero.name == "Super Man") {
                    ivHeroesImage.setImageDrawable(ContextCompat.getDrawable(ivHeroesImage.context, R.drawable.superman))
                } else if (hero.name == "Hulk") {
                    ivHeroesImage.setImageDrawable(ContextCompat.getDrawable(ivHeroesImage.context, R.drawable.avenger_hulk))
                } else {
                    ivHeroesImage.setImageDrawable(ContextCompat.getDrawable(ivHeroesImage.context, R.drawable.avenger_ironman))
                }

                root.setOnClickListener {
                    onItemClick.onItemClicked(hero)
                }
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Hero>() {
            override fun areItemsTheSame(oldItem: Hero, newItem: Hero): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Hero, newItem: Hero): Boolean {
                return oldItem == newItem
            }
        }
    }

    interface OnItemClick {
        fun onItemClicked(data: Hero)
    }

}