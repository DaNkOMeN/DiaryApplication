package com.example.testdiary.composable

import android.widget.ImageButton
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.example.testdiary.MainViewModel
import com.example.testdiary.data.DiaryItem


@Composable
fun DiaryDeleteButton(deleteAllDiaryPosts: MutableState<Boolean>) {
    Box(
        modifier = Modifier
            .padding(10.dp)
            .background(color = MaterialTheme.colors.background)
            .border(BorderStroke(2.dp, MaterialTheme.colors.secondary))
    ) {
        Spacer(modifier = Modifier.padding(10.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { deleteAllDiaryPosts.value = true }
        ) {
            Text(text = " Удалить все записи")
        }

    }
}

@Composable
fun DiaryListWithButton(
    items: List<DiaryItem>,
    navController: NavHostController,
    deletePostState: MutableState<Boolean>,
    currentDiaryItem: MutableState<DiaryItem>
) {
    Scaffold(backgroundColor = MaterialTheme.colors.background,
        floatingActionButton = { DiaryAddPostButton(navController = navController) }) {
        DiaryList(items, navController, deletePostState, currentDiaryItem)
    }

}


@Composable
fun DiaryAddPostButton(navController: NavHostController) {
    FloatingActionButton(
//        modifier = Modifier.border(BorderStroke(2.dp, MaterialTheme.colors.secondary)),
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.secondary,
        onClick = {
            navController.navigate("diary_post") {
                popUpTo = navController.graph.startDestination
                launchSingleTop = true
            }
        },

    ) {
        Text(text = "+")
    }
}

@Composable
fun DiaryPostDeleteAllAlertDialog(
    viewModel: MainViewModel,
    deleteAllDiaryPostsState: MutableState<Boolean>
) {
    if (deleteAllDiaryPostsState.value) {
        AlertDialog(
            onDismissRequest = { deleteAllDiaryPostsState.value = false },
            title = { Text(text = "Подтверждение удаления") },
            text = {
                Text(
                    text = "Вы действительно хотите удалить все посты?"
                )
            },
            confirmButton = {
                IconButton(onClick = {
                    viewModel.deleteAllPosts()
                    deleteAllDiaryPostsState.value = false
                }) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        null,
                        tint = MaterialTheme.colors.primary,
                        modifier = Modifier.background(color = MaterialTheme.colors.secondary)
                    )
                }
            },
            dismissButton = {
                IconButton(onClick = { deleteAllDiaryPostsState.value = false }) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        null,
                        tint = MaterialTheme.colors.primary,
                        modifier = Modifier.background(color = MaterialTheme.colors.secondary)
                    )
                }
            },
        )
    }
}

@Composable
fun DiaryPostDeleteAlertDialog(
    viewModel: MainViewModel,
    deletePostState: MutableState<Boolean>,
    diaryItem: DiaryItem
) {
    if (deletePostState.value) {
        AlertDialog(
            onDismissRequest = { deletePostState.value = false },
            title = { Text(text = "Подтверждение удаления") },
            text = {
                Text(
                    text = "Вы действительно хотите удалить пост от ${diaryItem.date}?"
                )
            },
            confirmButton = {
                IconButton(onClick = {
                    viewModel.deletePost(diaryItem)
                    deletePostState.value = false
                }) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        null,
                        tint = MaterialTheme.colors.primary,
                        modifier = Modifier.background(color = MaterialTheme.colors.secondary)
                    )
                }
            },
            dismissButton = {
                IconButton(onClick = { deletePostState.value = false }) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        null,
                        tint = MaterialTheme.colors.primary,
                        modifier = Modifier.background(color = MaterialTheme.colors.secondary)
                    )
                }
            }
        )


    }
}