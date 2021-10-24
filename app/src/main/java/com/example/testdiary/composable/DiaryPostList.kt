package com.example.testdiary.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.testdiary.BaseApplication

import com.example.testdiary.data.DiaryItem
import com.example.testdiary.data.DiarySortStatus
import com.example.testdiary.viewmodels.PostListViewModel

@Composable
fun DiaryPostList(
    navigateToPostDetail: (Long) -> Unit = {},
    navigateToEditPost: (Long) -> Unit = {},
    navigateToAddPost: () -> Unit = {},
    deletePost: (Long) -> Unit = {},
    deleteAllPosts: () -> Unit = {},
    application: BaseApplication?,
    modifier: Modifier = Modifier,
    postList: List<DiaryItem> = emptyList()
) {
    val sortStatus = remember { mutableStateOf(DiarySortStatus.DATE) }
    var items = postList
    items = when (sortStatus.value) {
        DiarySortStatus.DATE -> items.sortedBy { it.date }
        DiarySortStatus.AUTHOR -> items.sortedBy { it.author }
        DiarySortStatus.ID -> items.sortedBy { it.id }
        DiarySortStatus.DATEREVERSE -> items.sortedByDescending { it.date }
        DiarySortStatus.AUTHORREVERSE -> items.sortedByDescending { it.author }
        DiarySortStatus.IDREVERSE -> items.sortedByDescending { it.id }
    }

    val openDeleteAllPostDialog = remember { mutableStateOf(false) }
    val deletePostState = remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.background,
        topBar = { DiaryListHead(app = application, sortStatus = sortStatus) },
        bottomBar = { DiaryDeleteButton(deleteAllDiaryPosts = openDeleteAllPostDialog) },
        floatingActionButton = { DiaryAddPostButton(navigateToAddPost = navigateToAddPost) }
    ) {
        LazyColumn {
            items(items) { diaryItem ->
                DiaryCard(
                    diaryItem = diaryItem,
                    navigateToPostDetail = navigateToPostDetail,
                    navigateToEditPost = navigateToEditPost,
                    deletePostState = deletePostState,
                    deleteDiaryPost = deletePost,
                    modifier = Modifier
                        .padding(all = 10.dp)
                        .fillMaxWidth()
                        .clip(
                            RoundedCornerShape(
                                topStart = 20.dp,
                                topEnd = 20.dp,
                                bottomEnd = 20.dp,
                                bottomStart = 20.dp
                            )
                        )
                        .background(color = MaterialTheme.colors.primary)
                        .clickable { navigateToPostDetail(diaryItem.id) }
                        .border(BorderStroke(3.dp, MaterialTheme.colors.onPrimary)),
                )
            }
        }
    }

    Spacer(modifier = Modifier.padding(10.dp))
    DiaryPostDeleteAllAlertDialog(
        deleteAllPosts = deleteAllPosts,
        deleteAllDiaryPostsState = openDeleteAllPostDialog
    )


}
