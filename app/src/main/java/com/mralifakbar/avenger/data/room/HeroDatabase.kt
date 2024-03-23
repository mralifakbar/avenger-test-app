package com.mralifakbar.avenger.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
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
                ).addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        db.execSQL("INSERT INTO hero (name, rating) VALUES ('Super Man', 'Very Good')")
                        db.execSQL("INSERT INTO hero (name, rating) VALUES ('Iron Man', 'Normal')")
                        db.execSQL("INSERT INTO hero (name, rating) VALUES ('Hulk', 'Awesome')")
                    }
                }).build()
            }
    }
}
