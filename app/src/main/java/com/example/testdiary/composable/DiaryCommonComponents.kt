package com.example.testdiary.composable

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.testdiary.data.DiaryItem

/*
    Содержится список общих элементов для всей программы
 */

//Кнопка удаления всех записей.
//TODO переделать, убрать в какое нибудь место
@Composable
fun DiaryDeleteButton(deleteAllDiaryPosts: MutableState<Boolean>) {
    Box(
        modifier = Modifier
            .padding(10.dp)
            .background(color = MaterialTheme.colors.background)
            .border(BorderStroke(2.dp, MaterialTheme.colors.secondary))
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.padding(10.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            onClick = { deleteAllDiaryPosts.value = true },
        ) {
            Text(text = "Удалить все записи")
        }

    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
@Preview(showBackground = true)
fun DiaryDeleteButtonPreview() {
    val mutableState = mutableStateOf(false)
    DiaryDeleteButton(deleteAllDiaryPosts = mutableState)
}


//Кнопка добавления новой записи в дневник
//TODO сделать повыразительней, может куда то убрать
@Composable
fun DiaryAddPostButton(navController: NavHostController) {
    FloatingActionButton(
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.secondary,
        onClick = {
            navController.navigate("diary_post") {
                popUpTo = navController.graph.startDestinationId
                launchSingleTop = true
            }
        }
        ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Кнопка добавления новой записи в дневник"
        )
    }
}

@Composable
@Preview(showBackground = true)
fun DiaryAppPostButtonPreview() {
    DiaryAddPostButton(navController = rememberNavController())
}


//Диалог удаления всех постов
//Посмотреть как реализовано удаления в других программах
@Composable
fun DiaryPostDeleteAllAlertDialog(
//    viewModel: MainViewModel?,
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
//                    viewModel?.deleteAllPosts()
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

@SuppressLint("UnrememberedMutableState")
@Composable
@Preview(showBackground = true)
fun DiaryPostDeleteAllAlertDialogPreview() {
    DiaryPostDeleteAllAlertDialog( deleteAllDiaryPostsState = mutableStateOf(true))
}


//Удаление одного поста
//TODO тоже что нибдуь придумать
@Composable
fun DiaryPostDeleteAlertDialog(
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
//                    viewModel.deletePost(diaryItem)
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