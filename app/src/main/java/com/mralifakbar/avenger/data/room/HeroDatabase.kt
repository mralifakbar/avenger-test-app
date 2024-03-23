package com.mralifakbar.avenger.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mralifakbar.avenger.data.model.Hero

@Database(
    entities = [Hero::class],
    version = 1,
    exportSchema = false
)
abstract class HeroDatabase : RoomDatabase() {
    abstract fun heroDao(): HeroDao

    companion object {
        @Volatile
        private var INSTANCE: HeroDatabase? = null

        fun getDatabase(context: Context): HeroDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    HeroDatabase::class.java,
                    "Hero.db"
                ).build()
            }
    }
}
