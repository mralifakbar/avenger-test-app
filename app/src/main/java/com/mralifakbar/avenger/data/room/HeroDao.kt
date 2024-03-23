package com.mralifakbar.avenger.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.mralifakbar.avenger.data.model.Hero

@Dao
interface HeroDao {
    @Update
    fun update(hero: Hero)

    @Query("SELECT * FROM hero ORDER BY id ASC")
    fun getAllHeroes(): LiveData<List<Hero>>

    @Query("SELECT * FROM hero WHERE id =:id")
    fun getDetailHero(id: Int): LiveData<Hero>
}