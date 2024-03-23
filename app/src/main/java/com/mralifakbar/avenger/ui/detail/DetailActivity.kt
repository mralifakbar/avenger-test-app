package com.mralifakbar.avenger.ui.detail

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
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
        detailViewModel.getDetailHero(intent.getIntExtra(EXTRA_ID, 0)).observe(this) {
            with(binding) {
                toolbar.title = it.name ?: "Hero"


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