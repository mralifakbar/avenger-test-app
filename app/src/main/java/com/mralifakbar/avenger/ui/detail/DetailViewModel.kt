package com.mralifakbar.avenger.ui.detail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mralifakbar.avenger.data.model.Hero
import com.mralifakbar.avenger.data.repository.HeroRepository

class DetailViewModel(application: Application): ViewModel() {
    private val mHeroRepository: HeroRepository = HeroRepository(application)

    fun getDetailHero(id: Int): LiveData<Hero> = mHeroRepository.getDetailHero(id)
}