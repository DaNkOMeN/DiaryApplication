package com.example.testdiary.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import com.example.testdiary.BaseApplication
import com.example.testdiary.data.DiarySortStatus
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.toSize
import com.example.testdiary.R


//Выпадающий список с возможными сортировками
//TODO посмотреть как сделана фильтрация на дота-приложении
@Composable
fun DiaryDropdownSort(
    openDropdownSort: MutableState<Boolean>,
    sortStatus: MutableState<DiarySortStatus>
) {
    val sortStatusList = listOf(DiarySortStatus.ID, DiarySortStatus.AUTHOR, DiarySortStatus.DATE)
    var selectedIndex = remember { mutableStateOf(0) }

    Box(
        modifier = Modifier.fillMaxWidth().wrapContentSize(Alignment.TopEnd),
        contentAlignment = Alignment.TopEnd
    ) {
        DropdownMenu(
            modifier = Modifier
                .background(color = MaterialTheme.colors.secondary)
                .border(border = BorderStroke(2.dp, MaterialTheme.colors.primary)),
            expanded = openDropdownSort.value,
            onDismissRequest = { openDropdownSort.value = false }) {
            Column {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(color = MaterialTheme.colors.secondary)
                        .border(border = BorderStroke(2.dp, MaterialTheme.colors.onSecondary)),
                    text = stringResource(R.string.sort_by),
                    color = MaterialTheme.colors.primary
                )
                sortStatusList.forEachIndexed { index, s ->
                    DropdownMenuItem(onClick = {
                        if (s == sortStatus.value)
                            sortStatus.value = reverseSort(s)
                        else
                            sortStatus.value = s
                        selectedIndex.value = index
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


fun reverseSort(sortStatus: DiarySortStatus): DiarySortStatus {
    when (sortStatus) {
        DiarySortStatus.ID -> return DiarySortStatus.IDREVERSE
        DiarySortStatus.DATE -> return DiarySortStatus.DATEREVERSE
        DiarySortStatus.AUTHOR -> return DiarySortStatus.AUTHORREVERSE
        DiarySortStatus.IDREVERSE -> return DiarySortStatus.ID
        DiarySortStatus.DATEREVERSE -> return DiarySortStatus.DATE
        DiarySortStatus.AUTHORREVERSE -> return DiarySortStatus.AUTHOR
    }
    return DiarySortStatus.ID
}

fun translateSortStatus(sortStatus: DiarySortStatus): String {
    when (sortStatus) {
        DiarySortStatus.ID -> return "Номеру"
        DiarySortStatus.AUTHOR -> return "Автору"
        DiarySortStatus.DATE -> return "Дате"
    }
    return ""
}
