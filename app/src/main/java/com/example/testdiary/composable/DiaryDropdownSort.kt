package com.example.testdiary.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.testdiary.BaseApplication
import com.example.testdiary.data.DiarySortStatus
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.testdiary.R


//Выпадающий список с возможными сортировками
//TODO посмотреть как сделана фильтрация на дота-приложении
@Composable
fun DiaryDropdownSort(application: BaseApplication) {
    val sortStatusList = listOf(DiarySortStatus.ID, DiarySortStatus.AUTHOR, DiarySortStatus.DATE)

    var expanded by remember { mutableStateOf(false) }
    var selectedIndex = remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.TopEnd)

    ) {
        DropdownMenu(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colors.secondary)
                .border(border = BorderStroke(2.dp, MaterialTheme.colors.primary)),
            expanded = application.showDropdownList.value,
            onDismissRequest = { application.toggleDropdownList() }) {
            Column {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(color = MaterialTheme.colors.secondary)
                        .border(border = BorderStroke(2.dp, MaterialTheme.colors.primary)),
                    text = stringResource(R.string.sort_by)
                )
                sortStatusList.forEachIndexed { index, s ->
                    DropdownMenuItem(onClick = {
                        application.setSort(
                            if (s == application.currentSort.value) application.reverseSort(
                                s
                            ) else s
                        )
                        selectedIndex.value = index
                        expanded = false
                    }) {
                        Text(
                            text = translateSortStatus(s)
                        )
                    }
                }
            }

        }
    }
}

fun translateSortStatus(sortStatus: DiarySortStatus) : String{
    when (sortStatus) {
        DiarySortStatus.ID -> return "Номеру"
        DiarySortStatus.AUTHOR -> return "Автору"
        DiarySortStatus.DATE -> return "Дате"
    }
    return ""
}

@Composable
@Preview(showBackground = true)
fun DiaryDropdownSortPreview() {
    DiaryDropdownSort(application = BaseApplication())
}