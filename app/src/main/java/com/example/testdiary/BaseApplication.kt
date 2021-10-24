package com.example.testdiary

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import com.example.testdiary.data.DiarySortStatus
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication: Application() {
    val isDark = mutableStateOf(false)
    val sort = mutableStateOf(false)
    val showDropdownList = mutableStateOf(false)
    val currentSort = mutableStateOf(DiarySortStatus.DATE)


    fun toggleLightTheme(){
        isDark.value = !isDark.value
        sort.value = !sort.value
    }

    fun toggleDropdownList(){
        showDropdownList.value = !showDropdownList.value
    }

    fun setSort(sortStatus: DiarySortStatus){
        currentSort.value = sortStatus
    }



}