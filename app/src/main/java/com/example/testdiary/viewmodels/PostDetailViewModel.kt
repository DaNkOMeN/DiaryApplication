package com.example.testdiary.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testdiary.data.DiaryItem
import com.example.testdiary.database.DiaryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel
@Inject
constructor(
    private val savedStateHandle: SavedStateHandle,
    private val diaryRepository: DiaryRepository
) : ViewModel() {


    sealed class UIState {
        object Loading : UIState()
        data class Success(val currentPosts: DiaryItem) : UIState()
        object Error : UIState()
    }

    val postDetailState: State<UIState>
        get() = _postDetailState
    private val _postDetailState = mutableStateOf<UIState>(UIState.Loading)


    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                savedStateHandle.get<Int>("postId")?.let { postId ->
                    val diaryList: DiaryItem = diaryRepository.getDiaryPostDyIndex(postId)
                    withContext(Dispatchers.Main) {
                        _postDetailState.value = UIState.Success(diaryList)
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _postDetailState.value = UIState.Error
                }
            }
        }

    }

}