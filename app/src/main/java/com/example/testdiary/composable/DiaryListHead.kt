package com.example.testdiary.composable

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testdiary.BaseApplication
import com.example.testdiary.data.DiarySortStatus


//Заголовок главного меню с постами

@Composable
fun DiaryListHead(
    app: BaseApplication?,
    sortStatus: MutableState<DiarySortStatus> = mutableStateOf(DiarySortStatus.DATE),
) {
    val openDropdownSort = remember { mutableStateOf(false) }

    DiaryDropdownSort(openDropdownSort = openDropdownSort, sortStatus = sortStatus)

    Row(
        modifier = Modifier.padding(horizontal = 20.dp)
    ) {
        Card(
            shape = RoundedCornerShape(20.dp),
            border = BorderStroke(width = 4.dp, color = MaterialTheme.colors.onSecondary)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .background(MaterialTheme.colors.secondary)
            ) {
                Text(
                    text = "Мой дневник",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(0.9f),
                    color = MaterialTheme.colors.background
                )
                Column {
                    IconButton(onClick = { app?.toggleLightTheme() }) {
                        Icon(
                            imageVector = Icons.Filled.Share,
                            "Кнопка смена темы приложения",
                            tint = MaterialTheme.colors.background
                        )
                    }
                    IconButton(onClick = { openDropdownSort.value = true }) {
                        Icon(
                            imageVector = Icons.Filled.Build,
                            "Кнопка для вызова методов сортировки",
                            tint = MaterialTheme.colors.background
                        )
                    }

                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun DiaryListHeadPreview() {
    DiaryListHead(
        app = null,
    )
}
