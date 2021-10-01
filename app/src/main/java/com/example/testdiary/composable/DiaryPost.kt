package com.example.testdiary.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.testdiary.data.DiaryItem
import com.example.testdiary.data.stringalize
import com.example.testdiary.viewmodels.PostDetailAddViewModel
import java.util.*

@Composable
fun DiaryPost(
    isEdit: Boolean = true,
    viewModel: PostDetailAddViewModel,
    navigateToPostList: () -> Unit,
) {
    var diaryItem = DiaryItem()
    val author = remember { mutableStateOf(diaryItem.author) }
    val message = remember { mutableStateOf(diaryItem.message) }
    val date = Date()
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.background)
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
                        .background(color = MaterialTheme.colors.secondary)
                        .border(border = BorderStroke(2.dp, MaterialTheme.colors.primary))
                ) {
                    Text(
                        text = "Добавить новый пост",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(0.9f),
                        color = MaterialTheme.colors.primary

                    )
                    IconButton(
                        modifier = Modifier.padding(5.dp),
                        onClick = {
                            navigateToPostList()
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Кнопка возврата к списку",
                            tint = MaterialTheme.colors.primary,
                            modifier = Modifier.background(color = MaterialTheme.colors.secondary)
                        )
                    }
                }

                Spacer(modifier = Modifier.fillMaxHeight(0.1f))
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.LightGray)
                        .padding(20.dp),
                    value = author.value,
                    onValueChange = { author.value = it },
                    label = {
                        Text(
                            text = "Автор",
                            style = TextStyle(fontWeight = FontWeight.SemiBold)
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    placeholder = { Text(text = "Введите автора") },
                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
                )
                Spacer(modifier = Modifier.fillMaxHeight(0.1f))
                Row(modifier = Modifier.padding(20.dp)) {
                    Text(text = "Дата поста")
                    Spacer(modifier = Modifier.padding(20.dp))
                    Text(modifier = Modifier.fillMaxWidth(0.8f), text = date.stringalize())
                }
                Spacer(modifier = Modifier.fillMaxHeight(0.2f))
            }

        }
        Card(
            modifier = Modifier
                .fillMaxHeight(0.7f)
                .fillMaxWidth(),
            backgroundColor = Color.LightGray
        ) {
            OutlinedTextField(
                value = message.value,
                onValueChange = { message.value = it },
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .padding(20.dp),
                label = {
                    Text(
                        text = "Сообщение для поста",
                        style = TextStyle(fontWeight = FontWeight.SemiBold)
                    )
                },
                placeholder = { Text(text = "Введите сообщение для поста") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
            )
        }
        Spacer(modifier = Modifier.padding(20.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colors.secondary),
            onClick = {
                if (isEdit) {
                    diaryItem.author = author.value
                    diaryItem.message = message.value
                } else {
                }
                viewModel.addDiaryItemToRepository(diaryItem = diaryItem)
                navigateToPostList()
            }
        ) {
            Text(
                text = if (isEdit) "Сохранить изменения" else "Добавить",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(0.9f),
                color = MaterialTheme.colors.primary
            )
        }


    }
}

@Composable
@Preview(showBackground = true)
fun DiaryPostPreview() {
    val viewModel: PostDetailAddViewModel = hiltViewModel()
    DiaryPost(
        isEdit = true,
        viewModel = viewModel,
        navigateToPostList = {},
    )
}