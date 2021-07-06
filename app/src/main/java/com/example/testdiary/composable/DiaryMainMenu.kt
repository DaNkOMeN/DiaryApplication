package com.example.testdiary.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.testdiary.BaseApplication
import com.example.testdiary.MainViewModel

import com.example.testdiary.data.DiaryItem
import com.example.testdiary.data.DiarySortStatus

@Composable
fun DiaryMainMenu(
    app: BaseApplication,
    navController: NavHostController,
    mainViewModel: MainViewModel,
    application: BaseApplication
) {

    var items = mainViewModel.allDiaryItems.observeAsState(listOf()).value
    when (application.currentSort.value) {
        DiarySortStatus.DATE -> items = items.sortedBy { it.date }
        DiarySortStatus.AUTHOR -> items = items.sortedBy { it.author }
        DiarySortStatus.ID -> items = items.sortedBy { it.id }
        DiarySortStatus.DATEREVERSE -> items = items.sortedByDescending { it.date }
        DiarySortStatus.AUTHORREVERSE -> items = items.sortedByDescending { it.author }
        DiarySortStatus.IDREVERSE -> items = items.sortedByDescending { it.id }

    }
    DiaryDropdownSort(app)
    Column(modifier = Modifier.background(color = MaterialTheme.colors.background)) {

        val deleteAllPostsState = remember { mutableStateOf(false) }
        val deletePostState = remember { mutableStateOf(false) }
        val currentDiaryItem = remember { mutableStateOf(DiaryItem()) }

        DiaryListHead(app)

        Box(
            modifier = Modifier
                .fillMaxHeight(0.8f)
                .fillMaxWidth()
        ) {
            DiaryListWithButton(
                items,
                navController,
                deletePostState,
                currentDiaryItem
            )

        }

        Spacer(modifier = Modifier.padding(10.dp))
        DiaryDeleteButton(deleteAllDiaryPosts = deleteAllPostsState)
        DiaryPostDeleteAllAlertDialog(
            viewModel = mainViewModel,
            deleteAllDiaryPostsState = deleteAllPostsState
        )
        DiaryPostDeleteAlertDialog(
            viewModel = mainViewModel,
            deletePostState = deletePostState,
            diaryItem = currentDiaryItem.value
        )
    }

}