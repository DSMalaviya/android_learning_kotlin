package com.example.sevenminworkout.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [HistryEntity::class], version = 1)
abstract class HistryDatabase : RoomDatabase() {
    abstract fun histryDao(): HistryDao

    companion object {
        @Volatile
        private var INSTANCE: HistryDatabase? = null

        fun getInstance(context: Context): HistryDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        HistryDatabase::class.java,
                        "histry_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE=instance
                }
                return instance;
            }
        }
    }
}