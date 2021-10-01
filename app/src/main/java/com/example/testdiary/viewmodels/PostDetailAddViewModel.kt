package com.example.testdiary.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testdiary.data.DiaryItem
import com.example.testdiary.database.DiaryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostDetailAddViewModel
@Inject
constructor(
    private val diaryRepository: DiaryRepository
) : ViewModel() {

    fun addDiaryItemToRepository(diaryItem: DiaryItem) {
        viewModelScope.launch(Dispatchers.IO) {
            diaryRepository.addDiaryPost(diaryItem = diaryItem)
        }
    }

}