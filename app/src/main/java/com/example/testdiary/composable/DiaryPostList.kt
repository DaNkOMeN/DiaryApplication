package com.example.testdiary.composable

import androidx.compose.animation.ExperimentalAnimationApi
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
    navigateToEditPost: (Long) -> Unit,
    application: BaseApplication?,
    postList: List<DiaryItem> = emptyList(),
    modifier: Modifier = Modifier
) {
    var items = postList
    items = when (application?.currentSort?.value) {
        DiarySortStatus.DATE -> items.sortedBy { it.date }
        DiarySortStatus.AUTHOR -> items.sortedBy { it.author }
        DiarySortStatus.ID -> items.sortedBy { it.id }
        DiarySortStatus.DATEREVERSE -> items.sortedByDescending { it.date }
        DiarySortStatus.AUTHORREVERSE -> items.sortedByDescending { it.author }
        DiarySortStatus.IDREVERSE -> items.sortedByDescending { it.id }
        else -> items
    }

//        DiaryDropdownSort(application)
    val deleteAllPostsState = remember { mutableStateOf(false) }
    val deletePostState = remember { mutableStateOf(false) }
    val currentDiaryItem = remember { mutableStateOf(DiaryItem()) }
    Scaffold(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.background,
        topBar = { DiaryListHead(app = application, modifier = Modifier.background(MaterialTheme.colors.secondary)) },
        bottomBar = { DiaryDeleteButton(deleteAllDiaryPosts = deleteAllPostsState) }
    ) {
        LazyColumn {
            items(items) { diaryItem ->
                DiaryCard(
                    diaryItem = diaryItem,
                    navigateToPostDetail = navigateToPostDetail,
                    navigateToEditPost = navigateToEditPost,
                    deletePostState = deletePostState,
                    currentDiaryItem = currentDiaryItem,
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
                        .clickable { navigateToPostDetail(diaryItem.id) }
                        .border(BorderStroke(2.dp, MaterialTheme.colors.primary)),
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

@Composable
@Preview(showBackground = true)
fun DiaryPostListPreview() {
    DiaryPostList(
        navigateToPostDetail = {},
        navigateToEditPost = {},
        application = null,
        postList = listOf(DiaryItem(), DiaryItem(), DiaryItem())
    )
}

@Composable
@Preview(showBackground = true)
fun DiaryPostEmptyListPreview() {
    DiaryPostList(
        navigateToPostDetail = {},
        navigateToEditPost = {},
        application = null
    )
}