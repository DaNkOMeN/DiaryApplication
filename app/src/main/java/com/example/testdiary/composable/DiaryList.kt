package com.example.testdiary.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.testdiary.MainViewModel
import com.example.testdiary.data.DiaryItem


@Composable
fun DiaryList(
    items: List<DiaryItem>,
    navController: NavHostController,
    deletePostState : MutableState<Boolean>,
    currentDiaryItem: MutableState<DiaryItem>
) {
    LazyColumn {
        items(
            items
        ) { diaryItem ->
            DiaryCard(diaryItem, navController, deletePostState, currentDiaryItem)
        }
    }
}
