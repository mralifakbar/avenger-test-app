package com.mralifakbar.avenger.ui.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mralifakbar.avenger.data.model.Hero
import com.mralifakbar.avenger.data.repository.HeroRepository

class MainViewModel(application: Application): ViewModel() {
    private val mHeroRepository: HeroRepository = HeroRepository(application)

    fun getAllHeroes(): LiveData<List<Hero>> = mHeroRepository.getAllHeroes()
}