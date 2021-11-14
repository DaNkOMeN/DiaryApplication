package com.example.testdiary.composable

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testdiary.data.DiaryItem
import com.example.testdiary.ui.theme.DiaryAppTheme
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
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
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
                    .fillMaxWidth(0.8f),
                color = MaterialTheme.colors.background
            )
            IconButton(modifier = Modifier.padding(5.dp),
                onClick = {
                    navigateToPostList()
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    null,
                    tint = MaterialTheme.colors.background,
                )
            }
        }
        Column(
            modifier = Modifier.padding(
                top = 40.dp,
                bottom = 20.dp,
                start = 20.dp,
                end = 20.dp
            )
        ) {
            Box {
                Column(
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        modifier = Modifier.wrapContentSize(align = Alignment.Center),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Автор поста: ",
                        )
                        TextField(
                            value = newAuthor.value,
                            onValueChange = { newAuthor.value = it },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                            modifier = Modifier.wrapContentHeight(align = Alignment.CenterVertically)
                                .background(color = MaterialTheme.colors.background),
                            singleLine = true,
                            textStyle = TextStyle(fontSize = 20.sp),
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = MaterialTheme.colors.background
                            )
                        )
                    }
                    Row {
                        Text(
                            text = "Дата создания поста: ${diaryItem.date}"

                        )
                        Text(text = diaryItem.date)
                    }
                }
            }
            Column(modifier = Modifier.padding(top = 40.dp)) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "Сообщение:",
                    color = MaterialTheme.colors.primary
                )
                Card(
                    modifier = Modifier
                        .fillMaxHeight(fraction = 0.9f)
                        .fillMaxWidth(),
                    border = BorderStroke(3.dp, MaterialTheme.colors.primary),
                    shape = RoundedCornerShape(40.dp),
                    backgroundColor = MaterialTheme.colors.background
                ) {
                    Box(
                        modifier = Modifier
                            .padding(20.dp)
                    ) {
                        TextField(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = MaterialTheme.colors.background),
                            value = newMessage.value,
                            onValueChange = {
                                newMessage.value = it
                            },
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = MaterialTheme.colors.background
                            )
                        )
                    }
                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    onClick = {
                        diaryItem.author = newAuthor.value
                        diaryItem.message = newMessage.value
                        updatePost(diaryItem)
                    }
                ) {
                    Text(text = "Обновить данные пост")
                }
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun DiaryEditPostPreview() {
    DiaryAppTheme {
        CorrectEditPost(
            navigateToPostList = {},
            diaryItem = DiaryItem(author = "jija", message = "Perjija"),
            updatePost = {}
        )
    }
}