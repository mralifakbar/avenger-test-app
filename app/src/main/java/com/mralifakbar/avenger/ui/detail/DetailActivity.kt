package com.mralifakbar.avenger.ui.detail

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.mralifakbar.avenger.R
import com.mralifakbar.avenger.databinding.ActivityDetailBinding
import com.mralifakbar.avenger.ui.main.MainViewModel
import com.mralifakbar.avenger.utils.ViewModelFactory

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListener()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        detailViewModel = obtainViewModel(this)

        setObserver()
    }

    private fun setObserver() {
        detailViewModel.getDetailHero(intent.getIntExtra(EXTRA_ID, 0)).observe(this) {hero ->
            with(binding) {
                toolbar.title = hero.name ?: "Hero"

                if (hero.name == "Super Man") {
                    ivHeroesImage.setImageDrawable(ContextCompat.getDrawable(ivHeroesImage.context, R.drawable.superman))
                } else if (hero.name == "Hulk") {
                    ivHeroesImage.setImageDrawable(ContextCompat.getDrawable(ivHeroesImage.context, R.drawable.avenger_hulk))
                } else {
                    ivHeroesImage.setImageDrawable(ContextCompat.getDrawable(ivHeroesImage.context, R.drawable.avenger_ironman))
                }

                if (hero.rating == "Normal") {
                    star1.setImageDrawable(ContextCompat.getDrawable(ivHeroesImage.context, R.drawable.star_2))
                    star2.setImageDrawable(ContextCompat.getDrawable(ivHeroesImage.context, R.drawable.star))
                    star3.setImageDrawable(ContextCompat.getDrawable(ivHeroesImage.context, R.drawable.star))
                } else if (hero.rating == "Very Good") {
                    star1.setImageDrawable(ContextCompat.getDrawable(ivHeroesImage.context, R.drawable.star_2))
                    star2.setImageDrawable(ContextCompat.getDrawable(ivHeroesImage.context, R.drawable.star_2))
                    star3.setImageDrawable(ContextCompat.getDrawable(ivHeroesImage.context, R.drawable.star))
                } else {
                    star1.setImageDrawable(ContextCompat.getDrawable(ivHeroesImage.context, R.drawable.star_2))
                    star2.setImageDrawable(ContextCompat.getDrawable(ivHeroesImage.context, R.drawable.star_2))
                    star3.setImageDrawable(ContextCompat.getDrawable(ivHeroesImage.context, R.drawable.star_2))
                }

                star1.setOnClickListener {
                    detailViewModel.updateHero(hero.copy(rating = "Normal"))
                }
                star2.setOnClickListener {
                    detailViewModel.updateHero(hero.copy(rating = "Very Good"))
                }
                star3.setOnClickListener {
                    detailViewModel.updateHero(hero.copy(rating = "Awesome"))
                }
            }
        }
    }

    private fun setListener() {
        with(binding) {
            toolbar.setNavigationOnClickListener {
                finish()
            }
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): DetailViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[DetailViewModel::class.java]
    }

    companion object {
        const val EXTRA_ID = "extra_id"
    }
}