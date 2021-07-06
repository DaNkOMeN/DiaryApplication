package com.example.testdiary.database

import androidx.lifecycle.LiveData
import com.example.testdiary.data.DiaryItem
import javax.inject.Inject

class DiaryRepository @Inject constructor(private val diaryDao: DiaryDao) {

    val readAllData: LiveData<List<DiaryItem>> = diaryDao.getAllDiaryPosts()

    suspend fun addDiaryPost(diaryItem: DiaryItem) {
        diaryDao.insertDiaryPost(diaryItem)
    }

    suspend fun updateDiaryPost(diaryItem: DiaryItem) {
        diaryDao.updateDiaryPost(diaryItem)
    }

    suspend fun deleteDiaryPost(diaryItem: DiaryItem) {
        diaryDao.deleteDiaryPost(diaryItem)
    }

    suspend fun deleteDiaryPostByIndex(index: Int) {
        diaryDao.deleteDiaryPostByIndex(index)
    }

    suspend fun getDiaryPostDyIndex(index: Int): DiaryItem? {
        return diaryDao.getDiaryPostById(index)
    }

    suspend fun deleteAllDiaryPosts() {
        diaryDao.deleteAllDiaryItems()
    }
}