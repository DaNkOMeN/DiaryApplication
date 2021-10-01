package com.example.testdiary.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
    viewModel: PostDetailViewModel
) {
    when (val uiState = viewModel.postDetailState.value) {
        is PostDetailViewModel.UIState.Error -> DiaryOpenPostError()
        is PostDetailViewModel.UIState.Loading -> DiaryOpenPostLoading()
        is PostDetailViewModel.UIState.Success -> TrulyOpenPost(
            navigateToPostList = navigateToPostList,
            viewModel = viewModel
        )
    }

}

@Composable
fun DiaryOpenPostError() {
    Text(text = "ERROR")

}

@Composable
fun DiaryOpenPostLoading() {
    Text(text = "LOADING")
}

@Composable
fun TrulyOpenPost(
    navigateToPostList: () -> Unit,
    viewModel: PostDetailViewModel
) {
    val value = viewModel.postDetailState.value as PostDetailViewModel.UIState.Success
    val diaryItem = value.currentPosts

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
            Text(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                text = diaryItem.message
            )
        }

        Spacer(modifier = Modifier.fillMaxHeight(0.1f))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { }//navigateToDiaryOpenItem(diaryItem = diaryItem) }
        ) {
            Text(text = "Редактировать пост")
        }
    }
}