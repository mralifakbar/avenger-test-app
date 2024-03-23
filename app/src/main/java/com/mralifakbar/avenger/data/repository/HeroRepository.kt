package com.mralifakbar.avenger.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.mralifakbar.avenger.data.model.Hero
import com.mralifakbar.avenger.data.room.HeroDao
import com.mralifakbar.avenger.data.room.HeroDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class HeroRepository(application: Application) {
    private val mHeroDao: HeroDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = HeroDatabase.getDatabase(application)
        mHeroDao = db.heroDao()
    }

    fun getAllHeroes(): LiveData<List<Hero>> = mHeroDao.getAllHeroes()

    fun getDetailHero(id: Int): LiveData<Hero> = mHeroDao.getDetailHero(id)

    fun update(hero: Hero) {
        executorService.execute { mHeroDao.update(hero) }
    }
}