package com.example.testdiary.navigation

import androidx.navigation.compose.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.navArgument

sealed class Screen(val route: String, val arguments: List<NamedNavArgument>) {

    object PostList : Screen(
        route = "postList",
        arguments = emptyList()
    )

    object PostDetail : Screen(
        route = "postDetail",
        arguments = listOf(navArgument("postId"){
            type = NavType.IntType

        })
    )

    object AddPost : Screen(
        route = "addPost",
        arguments = emptyList()
    )

}
