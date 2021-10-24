package com.example.testdiary.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testdiary.data.DiaryItem
import com.example.testdiary.viewmodels.PostDetailEditViewModel
import com.example.testdiary.viewmodels.PostDetailViewModel

/**
 * Внешний вид поста, открытого, при нажатии на
 * @see DiaryCard()
 *
 */


@Composable
fun DiaryEditPost(
    navigateToPostList: () -> Unit,
    updatePost: (DiaryItem) -> Unit,
    state: PostDetailEditViewModel.UIState,
) {
    when (state) {
        is PostDetailEditViewModel.UIState.Error -> ErrorPost()
        is PostDetailEditViewModel.UIState.Loading -> LoadingPost()
        is PostDetailEditViewModel.UIState.Success -> CorrectEditPost(
            navigateToPostList = navigateToPostList,
            diaryItem = state.currentPost,
            updatePost = updatePost
        )
    }
}

@Composable
fun CorrectEditPost(
    navigateToPostList: () -> Unit,
    diaryItem: DiaryItem,
    updatePost: (DiaryItem) -> Unit,
) {

    val newAuthor = remember { mutableStateOf(diaryItem.author) }
    val newMessage = remember { mutableStateOf(diaryItem.message) }
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight(0.5f)
                .fillMaxWidth()
        ) {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colors.primary),
                ) {
                    Text(
                        text = "Редактирование поста",
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .background(color = MaterialTheme.colors.secondary)
                    )
                    IconButton(modifier = Modifier.padding(5.dp),
                        onClick = {
                            navigateToPostList()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            null,
                            tint = MaterialTheme.colors.primary,
                            modifier = Modifier.background(color = MaterialTheme.colors.secondary)
                        )
                    }
                }
                Spacer(modifier = Modifier.fillMaxHeight(0.1f))
                Card(
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.3f)
                        .clip(
                            RoundedCornerShape(
                                topStart = 15.dp,
                                topEnd = 15.dp,
                                bottomEnd = 15.dp,
                                bottomStart = 15.dp
                            )
                        )
                ) {
                    Row {
                        Text(
                            text = "Автор поста: ",
                            modifier = Modifier
                                .fillMaxWidth(0.2f)
                                .fillMaxHeight()
                                .background(color = MaterialTheme.colors.secondary)
                        )
                        TextField(
                            value = newAuthor.value,
                            onValueChange = { newAuthor.value = it },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
                        )

                    }
                }
                Spacer(modifier = Modifier.fillMaxHeight(0.1f))
                Card(
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(
                            RoundedCornerShape(
                                topStart = 15.dp,
                                topEnd = 15.dp,
                                bottomEnd = 15.dp,
                                bottomStart = 15.dp
                            )
                        )
                ) {
                    Text(
                        text = "Дата создания поста: ${diaryItem.date}",
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .background(color = MaterialTheme.colors.secondary)
                    )
                }
            }
            Spacer(modifier = Modifier.fillMaxHeight(0.2f))
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colors.primary),
            text = "Сообщение"
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Card(
            modifier = Modifier
                .fillMaxHeight(0.7f)
                .fillMaxWidth(),
            backgroundColor = Color.LightGray
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                value = newMessage.value,
                onValueChange = { newMessage.value = it },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
            )
        }
        Spacer(modifier = Modifier.fillMaxHeight(0.1f))
        Button(onClick = {
            diaryItem.message = newMessage.value
            diaryItem.author = newAuthor.value
            updatePost(diaryItem)
            navigateToPostList()
        }) {
            Text(text = "Обновить данные")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DiaryEditPostPreview() {
    CorrectEditPost(
        navigateToPostList = {},
        diaryItem = DiaryItem(author = "jija", message = "Perjija"),
        updatePost = {}
    )
}