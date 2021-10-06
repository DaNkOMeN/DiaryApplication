package com.example.testdiary.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.testdiary.data.DiaryItem


@Dao
interface DiaryDao {

    @Query("SELECT * FROM diaryItems")
    fun getAllDiaryPosts(): LiveData<List<DiaryItem>>

    @Query("Select * from diaryItems where id = :id")
    fun getDiaryPostById(id: Int) : DiaryItem

    @Query("Delete from diaryItems where id = :index")
    fun deleteDiaryPostByIndex(index : Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDiaryPost(diaryItem: DiaryItem)

    @Update
    suspend fun updateDiaryPost(diaryItem: DiaryItem)

    @Delete
    suspend fun deleteDiaryPost(diaryItem: DiaryItem)

    @Query("Delete from diaryItems")
    suspend fun deleteAllDiaryItems()

}