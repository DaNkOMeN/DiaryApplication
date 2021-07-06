package com.example.testdiary

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.example.testdiary.data.DiaryItem
import com.example.testdiary.data.stringalize

import com.example.testdiary.database.DiaryDatabase
import com.example.testdiary.database.DiaryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class MainViewModel @Inject constructor(private val diaryRepository : DiaryRepository) : ViewModel() {

    var allDiaryItems: LiveData<List<DiaryItem>> = diaryRepository.readAllData

    fun getPostById(index: Int) : DiaryItem {
        if (index == -1) return DiaryItem()
        var diaryItem: DiaryItem? = DiaryItem()
        viewModelScope.launch (Dispatchers.IO){
              diaryItem = diaryRepository.getDiaryPostDyIndex(index)
        }
        return diaryItem?: DiaryItem()
    }

    fun addNewPost(diaryItem: DiaryItem) {
        viewModelScope.launch(Dispatchers.IO) {
            diaryRepository.addDiaryPost(diaryItem)
        }
    }

    fun deletePost(diaryItem: DiaryItem) {
        viewModelScope.launch(Dispatchers.IO) {
            diaryRepository.deleteDiaryPost(diaryItem)
        }
    }

    fun updatePost(diaryItem: DiaryItem) {
        viewModelScope.launch (Dispatchers.IO){
            diaryRepository.updateDiaryPost(diaryItem)
        }
    }

    fun deletePostByIndex(index: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            diaryRepository.deleteDiaryPostByIndex(index)
        }
    }

    fun deleteAllPosts() {
        viewModelScope.launch(Dispatchers.IO) {
            diaryRepository.deleteAllDiaryPosts()
        }
    }
}
