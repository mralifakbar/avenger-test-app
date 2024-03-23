package com.mralifakbar.avenger.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mralifakbar.avenger.R
import com.mralifakbar.avenger.data.model.Hero
import com.mralifakbar.avenger.databinding.ActivityMainBinding
import com.mralifakbar.avenger.databinding.ItemHeroesBinding
import com.mralifakbar.avenger.ui.adapter.ListHeroAdapter
import com.mralifakbar.avenger.utils.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ListHeroAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[MainViewModel::class.java]


        val layoutManager = LinearLayoutManager(this)
        binding.rvHeroes.layoutManager = layoutManager
    }

    override fun onResume() {
        super.onResume()
    }

    private fun setObserver() {
        mainViewModel.getAllHeroes().observe(this@MainActivity) {
            if (it != null) {
                setHeroesData(it)
            }
        }
    }

    private fun setHeroesData(hero: List<Hero>) {
        adapter = ListHeroAdapter(object : ListHeroAdapter.OnItemClick {
            override fun onItemClicked(data: Hero) {
//                Intent(this@MainActivity, DetailUserActivity::class.java).also {
//                    it.putExtra(DetailUserActivity.EXTRA_USERNAME, data.username)
//                    it.putExtra(DetailUserActivity.EXTRA_AVATAR_URL, data.avatarUrl)
//                    startActivity(it)
//                }
            }
        })

        val items = arrayListOf<Hero>()
        hero.map {
            val item = Hero(it.id, it.name, it.rating)
            items.add(item)
        }
        adapter.submitList(items)
        binding.rvHeroes.adapter = adapter
    }
}