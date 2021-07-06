package com.example.testdiary.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "diaryItems")
data class DiaryItem (
    @PrimaryKey(autoGenerate = true)
    var id : Long = 0L,
    @ColumnInfo(name = "diary_post_author")
    var author: String = "",
    @ColumnInfo(name = "diary_post_date")
    var date: String = "",
    @ColumnInfo(name = "diary_post_message")
    var message: String = ""

)

fun Date.stringalize(): String{
    val formatDate = SimpleDateFormat("dd.MM.yyyy")
    return formatDate.format(this)
}