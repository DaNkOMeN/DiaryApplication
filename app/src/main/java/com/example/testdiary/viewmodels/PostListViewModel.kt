package com.example.testdiary.viewmodels

import androidx.compose.runtime.MutableState
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testdiary.data.DiaryItem
import com.example.testdiary.database.DiaryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostListViewModel
@Inject
constructor(
    private val diaryRepository: DiaryRepository,
) : ViewModel() {
    val allDiaryItems: MutableState<List<DiaryItem>> = diaryRepository.readAllData

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
        viewModelScope.launch(Dispatchers.IO) {
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

    fun getLastPost(): DiaryItem {
        return allDiaryItems.value?.last() ?: DiaryItem()
    }
}