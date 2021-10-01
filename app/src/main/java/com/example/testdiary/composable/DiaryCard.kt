package com.example.testdiary.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testdiary.data.DiaryItem
import com.example.testdiary.data.stringalize
import java.util.*


//Карточка записи в списке всех записей
@Composable
fun DiaryCard(
    diaryItem: DiaryItem = DiaryItem(),
    navigateToPostDetail : (Long) -> Unit = {},
    deletePostState: MutableState<Boolean> = mutableStateOf(false),
    currentDiaryItem: MutableState<DiaryItem> = mutableStateOf(diaryItem)
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .padding(
                top = 10.dp,
                start = 20.dp,
                end = 20.dp
            )
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    topStart = 20.dp,
                    topEnd = 20.dp,
                    bottomEnd = 20.dp,
                    bottomStart = 20.dp
                )
            )
            .clickable { navigateToPostDetail(diaryItem.id) }
            .background(color = MaterialTheme.colors.primary)
            .border(BorderStroke(2.dp, MaterialTheme.colors.primary)),

    ) {
        Row(modifier = Modifier.background(color = MaterialTheme.colors.secondary)) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(0.7f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = diaryItem.author, color = MaterialTheme.colors.primary)
                Spacer(modifier = Modifier.padding(10.dp))
                Text(text = diaryItem.date, color = MaterialTheme.colors.primary)
            }
            Spacer(modifier = Modifier.padding(10.dp))
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxHeight(0.7f)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                IconButton(
                    modifier = Modifier
                        .padding(5.dp),
                    onClick = {
                        currentDiaryItem.value = diaryItem
                        deletePostState.value = true
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        null,
                        tint = MaterialTheme.colors.primary
                    )
                }
            }
        }

    }
}

@Composable
@Preview(showBackground = true)
fun DiaryCardPreview(){
    DiaryCard(
        diaryItem = DiaryItem(
            author = "Danko",
            date = Date().stringalize(),
            message = "Something bullshit"
        )
    )
}
