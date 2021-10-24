package com.example.testdiary.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testdiary.data.DiaryItem
import com.example.testdiary.viewmodels.PostDetailViewModel

/**
 * Внешний вид поста, открытого, при нажатии на
 * @see DiaryCard()
 *
 */


@Composable
fun DiaryOpenPost(
    navigateToPostList: () -> Unit,
    navigateToEditPost: (Long) -> Unit,
    state: PostDetailViewModel.UIState
) {
    when (state) {
        is PostDetailViewModel.UIState.Error -> ErrorPost()
        is PostDetailViewModel.UIState.Loading -> LoadingPost()
        is PostDetailViewModel.UIState.Success -> CorrectOpenPost(
            navigateToPostList = navigateToPostList,
            navigateToEditPost = navigateToEditPost,
            diaryItem = state.currentPost
        )
    }

}

@Composable
fun ErrorPost() {
    Text(text = "ERROR")

}

@Composable
fun LoadingPost() {
    Text(text = "LOADING")
}

@Composable
fun CorrectOpenPost(
    navigateToPostList: () -> Unit,
    navigateToEditPost: (Long) -> Unit,
    diaryItem: DiaryItem
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
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
                        text = "Просмотр поста",
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
                Column(modifier = Modifier.padding(20.dp)) {
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
                            text = "Автор поста: ${diaryItem.author}",
                            modifier = Modifier
                                .fillMaxWidth(0.8f)
                                .background(color = MaterialTheme.colors.secondary)
                        )
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
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                ) {
                    Column {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = MaterialTheme.colors.primary),
                            text = "Сообщение"
                        )
                        Spacer(modifier = Modifier.padding(10.dp))
                        Card(
                            modifier = Modifier
                                .fillMaxHeight(0.8f),
                            backgroundColor = Color.LightGray
                        ) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = diaryItem.message
                            )
                        }

                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = { navigateToEditPost(diaryItem.id) }
                        ) {
                            Text(text = "Редактировать пост")
                        }
                    }
                }
            }

        }
    }
}


@Composable
@Preview(showBackground = true)
fun ErrorPostPreview() {
    ErrorPost()
}

@Composable
@Preview(showBackground = true)
fun LoadingPostPreview() {
    LoadingPost()
}

@Composable
@Preview(showBackground = true)
fun CorrectOpenPostPreview() {
    CorrectOpenPost(navigateToPostList = {}, navigateToEditPost = {}, DiaryItem())
}