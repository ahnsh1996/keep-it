package com.ahnsh1996.keepit.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ahnsh1996.keepit.model.KeepData

@Database(entities = [KeepData::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun keepDataDao(): KeepDataDao

    companion object {
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return synchronized(this) {
                Room.databaseBuilder(context, AppDatabase::class.java, "keepit-db").build()
            }
        }
    }
}