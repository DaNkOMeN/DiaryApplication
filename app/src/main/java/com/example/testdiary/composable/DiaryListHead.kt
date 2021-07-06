package com.example.testdiary.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testdiary.BaseApplication


@Composable
fun DiaryListHead(app: BaseApplication) {
    Box(
        modifier = Modifier
            .fillMaxHeight(0.1f)
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.secondary)
            .border(border = BorderStroke(2.dp, MaterialTheme.colors.primary))
    ) {
        Row {
            Text(
                text = "Мой дневник",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(0.9f),
                color = MaterialTheme.colors.primary
            )
            Column {
                IconButton(onClick = { app.toggleLightTheme() }) {
                    Icon(
                        imageVector = Icons.Filled.Share,
                        null,
                        tint = MaterialTheme.colors.primary
                    )
                }
                IconButton(onClick = { app.toggleDropdownList() }) {
                    Icon(
                        imageVector = Icons.Filled.Build,
                        null,
                        tint = MaterialTheme.colors.primary
                    )
                }
            }

        }

    }
}