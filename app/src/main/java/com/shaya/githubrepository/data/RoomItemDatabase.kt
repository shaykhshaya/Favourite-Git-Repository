package com.shaya.githubrepository.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RoomItem::class], version = 1, exportSchema = false)
abstract class RoomItemDatabase: RoomDatabase() {

    abstract fun roomItemDao(): RoomItemDao

    companion object {
        @Volatile
        private var INSTANCE: RoomItemDatabase? = null
        fun getDatabase(context: Context): RoomItemDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    RoomItemDatabase::class.java,
                    "item_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }

}