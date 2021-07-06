package com.example.testdiary

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.testdiary.composable.DiaryMainMenu
import com.example.testdiary.composable.DiaryOpenPost
import com.example.testdiary.composable.DiaryPost
import com.example.testdiary.data.DiaryItem
import com.example.testdiary.ui.theme.DiaryAppTheme
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {



    @Inject
    lateinit var application: BaseApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //если поставить false, то часть шапки будет под табло с временем, уровнем мобильного сигнала и строки уведомлений
        WindowCompat.setDecorFitsSystemWindows(window, true)
        setContent {
            DiaryAppTheme(application.isDark.value) {
                DiaryApplication()
            }

        }
    }

    @Composable
    fun DiaryApplication() {

        val navController = rememberNavController()
//        val mainViewModel: MainViewModel by viewModels()
        val mainViewModel = viewModel(MainViewModel::class.java)

        NavHost(navController = navController, startDestination = "main_menu", builder = {
            composable("main_menu") {
                DiaryMainMenu(
                    application = application,
                    navController = navController,
                    mainViewModel =mainViewModel,
                    app = application
                )
            }
            composable("diary_post") {
                DiaryPost(
                    isEdit = false,
                    diaryItem = DiaryItem(),
                    navController = navController,
                    viewModel = mainViewModel
                )
            }
            composable(
                "diary_open_post/{post}",
                arguments = listOf(navArgument("post") {
                    type = NavType.StringType
                })
            ) { backStackEntry ->
                backStackEntry.arguments?.getString("post")?.let { json ->
                    val diaryItem = Gson().fromJson(json, DiaryItem::class.java)
                    DiaryOpenPost(
                        diaryItem = diaryItem,
                        navController = navController,
                        mainViewModel = mainViewModel
                    )
                }

            }

            composable(
                "diary_post_edit/{post}",
                arguments = listOf(navArgument("post") {
                    type = NavType.StringType
                })
            ) { backStackEntry ->
                backStackEntry.arguments?.getString("post")?.let { json ->
                    val diaryItem = Gson().fromJson(json, DiaryItem::class.java)
                    DiaryPost(
                        isEdit = true,
                        diaryItem = diaryItem,
                        navController = navController,
                        viewModel = mainViewModel
                    )
                }


            }

        })
    }
}
