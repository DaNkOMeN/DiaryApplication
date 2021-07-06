package com.example.testdiary.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.testdiary.data.DiaryItem

@Database(entities = [DiaryItem::class], version = 1, exportSchema = false)
abstract class DiaryDatabase : RoomDatabase() {


    abstract fun getDiaryDao(): DiaryDao

    companion object {

        private var INSTANCE: DiaryDatabase? = null

        fun getInstance(context: Context): DiaryDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DiaryDatabase::class.java,
                        "diary_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}