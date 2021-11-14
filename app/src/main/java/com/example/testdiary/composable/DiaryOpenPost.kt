package com.example.testdiary.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import com.example.testdiary.ui.theme.DiaryAppTheme
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
                text = "Просмотр поста",
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
                Column {
                    Text(
                        text = "Автор поста: ${diaryItem.author}"
                    )
                    Text(
                        text = "Дата создания поста: ${diaryItem.date}"

                    )
                }
            }

            Column(modifier = Modifier.fillMaxHeight()) {
                Card(
                    modifier = Modifier
                        .fillMaxHeight(fraction = 0.9f)
                        .fillMaxWidth()
                        .padding(top = 40.dp),
                    border = BorderStroke(3.dp, MaterialTheme.colors.primary),
                    shape = RoundedCornerShape(40.dp),
                    backgroundColor = MaterialTheme.colors.background
                ) {
                    Box(
                        modifier = Modifier
                            .padding(20.dp)
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(),
                            text = diaryItem.message
                        )
                    }
                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    onClick = { navigateToEditPost(diaryItem.id) }
                ) {
                    Text(text = "Редактировать пост")
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
    DiaryAppTheme {
        CorrectOpenPost(navigateToPostList = {}, navigateToEditPost = {}, DiaryItem())
    }
}