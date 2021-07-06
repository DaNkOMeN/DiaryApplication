package com.example.testdiary

import android.content.Context
import androidx.room.Room
import com.example.testdiary.database.DiaryDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDiaryDatabase(@ApplicationContext app: Context) = Room.databaseBuilder(
        app, DiaryDatabase::class.java, "diary_database"
    ).build()

    @Singleton
    @Provides
    fun provideDiaryDao(db: DiaryDatabase) = db.getDiaryDao()

    @Singleton
    @Provides
    fun provideBaseAplication(@ApplicationContext app: Context): BaseApplication {
        return app as BaseApplication
    }

}