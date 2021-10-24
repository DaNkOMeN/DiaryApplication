package com.example.testdiary.composable

import android.widget.Space
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
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
import com.example.testdiary.viewmodels.PostListViewModel
import java.util.*


//Карточка записи в списке всех записей
@Composable
fun DiaryCard(
    diaryItem: DiaryItem = DiaryItem(),
    navigateToPostDetail: (Long) -> Unit = {},
    navigateToEditPost: (Long) -> Unit = {},
    deleteDiaryPost: (Long) -> Unit = {},
    deletePostState: MutableState<Boolean> = mutableStateOf(false),
    modifier: Modifier = Modifier,
    shape: CornerBasedShape = RoundedCornerShape(20.dp)
) {

    DiaryPostDeleteAlertDialog(
        deleteDiaryPostState = deletePostState,
        diaryItem = diaryItem,
        deleteDiaryPost = deleteDiaryPost
    )

    Card(
        shape = shape,
        modifier = modifier,
        border = BorderStroke(3.dp, MaterialTheme.colors.onPrimary)
    ) {
        Row(modifier = Modifier.background(color = MaterialTheme.colors.primary)) {
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(0.7f)
                    .align(Alignment.CenterVertically)
                    .background(MaterialTheme.colors.primary),
            ) {
                Row {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Author:", color = MaterialTheme.colors.background)
                        Spacer(modifier = Modifier.padding(10.dp))
                        Text(text = "Date:", color = MaterialTheme.colors.background)
                    }
                    Spacer(modifier = Modifier.padding(10.dp))
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = diaryItem.author, color = MaterialTheme.colors.background)
                        Spacer(modifier = Modifier.padding(10.dp))
                        Text(text = diaryItem.date, color = MaterialTheme.colors.background)
                    }
                }
            }
            Spacer(modifier = Modifier.padding(10.dp))
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(
                    modifier = Modifier
                        .size(35.dp)
                        .padding(5.dp),
                    onClick = { deletePostState.value = true }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        null,
                        tint = MaterialTheme.colors.background
                    )
                }
                Spacer(modifier = Modifier.padding(5.dp))
                IconButton(
                    modifier = Modifier
                        .size(35.dp)
                        .padding(5.dp),
                    onClick = {
                        navigateToEditPost(diaryItem.id)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        null,
                        tint = MaterialTheme.colors.background
                    )
                }
            }
        }

    }
}

@Composable
@Preview(showBackground = true)
fun DiaryCardPreview() {
    DiaryCard(
        diaryItem = DiaryItem(
            author = "Danko",
            date = Date().stringalize(),
            message = "Something bullshit"
        ),
        modifier = Modifier
            .padding(all = 10.dp)
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.primary)
            .clickable { },
        shape = MaterialTheme.shapes.small
    )
}
