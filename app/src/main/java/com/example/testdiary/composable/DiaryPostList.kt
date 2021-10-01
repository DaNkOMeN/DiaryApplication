package com.example.testdiary.composable

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.testdiary.BaseApplication

import com.example.testdiary.data.DiaryItem
import com.example.testdiary.data.DiarySortStatus
import com.example.testdiary.viewmodels.PostListViewModel
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@Composable
fun DiaryPostList(
    navigateToPostDetail: (Long) -> Unit,
    application: BaseApplication,
    postListViewModel: PostListViewModel,
) {
    var items = postListViewModel.allDiaryItems.observeAsState(listOf()).value
    items = when (application.currentSort.value) {
        DiarySortStatus.DATE -> items.sortedBy { it.date }
        DiarySortStatus.AUTHOR -> items.sortedBy { it.author }
        DiarySortStatus.ID -> items.sortedBy { it.id }
        DiarySortStatus.DATEREVERSE -> items.sortedByDescending { it.date }
        DiarySortStatus.AUTHORREVERSE -> items.sortedByDescending { it.author }
        DiarySortStatus.IDREVERSE -> items.sortedByDescending { it.id }

    }
    Box(
        modifier = Modifier
            .fillMaxHeight(0.8f)
            .fillMaxWidth()
    ) {
        DiaryDropdownSort(application)
        val deleteAllPostsState = remember { mutableStateOf(false) }
        val deletePostState = remember { mutableStateOf(false) }
        val currentDiaryItem = remember { mutableStateOf(DiaryItem()) }
        Scaffold(
            backgroundColor = MaterialTheme.colors.background,
            topBar = { DiaryListHead(app = application) },
            bottomBar = { DiaryDeleteButton(deleteAllDiaryPosts = deleteAllPostsState) }
        ) {
            LazyColumn {
                items(items) { diaryItem ->
                    DiaryCard(
                        diaryItem = diaryItem,
                        navigateToPostDetail = navigateToPostDetail,
                        deletePostState = deletePostState,
                        currentDiaryItem = currentDiaryItem
                    )
                }
            }
        }

        Spacer(modifier = Modifier.padding(10.dp))
        DiaryPostDeleteAllAlertDialog(
            deleteAllDiaryPostsState = deleteAllPostsState
        )
        DiaryPostDeleteAlertDialog(
            deletePostState = deletePostState,
            diaryItem = currentDiaryItem.value
        )
    }
}

@Composable
@Preview(showBackground = true)
fun DiaryPostListPreview() {
    val postListViewModel: PostListViewModel = hiltViewModel()
    DiaryPostList(
        navigateToPostDetail = {},
        application = BaseApplication(),
        postListViewModel = postListViewModel
    )
}