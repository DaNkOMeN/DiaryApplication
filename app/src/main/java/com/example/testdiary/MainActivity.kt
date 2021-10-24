package com.example.testdiary

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.example.testdiary.composable.DiaryEditPost
import com.example.testdiary.composable.DiaryOpenPost
import com.example.testdiary.composable.DiaryNewPost
import com.example.testdiary.composable.DiaryPostList
import com.example.testdiary.data.DiarySortStatus
import com.example.testdiary.navigation.Screen
import com.example.testdiary.ui.theme.DiaryAppTheme
import com.example.testdiary.viewmodels.PostDetailAddViewModel
import com.example.testdiary.viewmodels.PostDetailEditViewModel
import com.example.testdiary.viewmodels.PostDetailViewModel
import com.example.testdiary.viewmodels.PostListViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
@OptIn(ExperimentalAnimationApi::class)
class MainActivity : AppCompatActivity() {


    @Inject
    lateinit var application: BaseApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //если поставить false, то часть шапки будет под табло с временем, уровнем мобильного сигнала и строки уведомлений
        WindowCompat.setDecorFitsSystemWindows(window, true)
        setContent {
            DiaryAppTheme(application.isDark.value) {
                BoxWithConstraints {
                    val navController = rememberAnimatedNavController()
                    AnimatedNavHost(
                        navController = navController,
                        startDestination = Screen.PostList.route,
                        builder = {
                            addPostDetail(navController = navController)
                            addPostList(
                                navController = navController,
                                application = application
                            )
                            addPost(navController)
                            addEditPost(navController)
                        })
                }
            }

        }
    }
}


@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.addPostList(
    navController: NavController,
    application: BaseApplication
) {
    composable(
        route = Screen.PostList.route,
        enterTransition = { _, _ ->
            slideInHorizontally(
                initialOffsetX = { 100 },
                animationSpec = tween(
                    durationMillis = 1000,
                    easing = FastOutSlowInEasing
                )
            ) + fadeIn(animationSpec = tween(1000))
        },
        popExitTransition = { _, target ->
            slideOutHorizontally(
                targetOffsetX = { 100 },
                animationSpec = tween(
                    durationMillis = 1000,
                    easing = FastOutSlowInEasing
                )
            ) + fadeOut(animationSpec = tween(1000))
        }
    ) {
        val postListViewModel: PostListViewModel = hiltViewModel()
        val postList = postListViewModel.allDiaryItems.observeAsState(listOf()).value
        DiaryPostList(
            navigateToPostDetail = { postId ->
                navController.navigate(route = "${Screen.PostDetail.route}/$postId")
            },
            navigateToEditPost = { postId ->
                navController.navigate(route = "${Screen.EditPost.route}/$postId")
            },
            navigateToAddPost = { navController.navigate(route = Screen.AddPost.route) },
            deletePost = { postId ->
                postListViewModel.deletePostByIndex(postId)
            },
            deleteAllPosts = {
                postListViewModel.deleteAllPosts()
            },
            postList = postList,
            application = application
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.addPostDetail(navController: NavController) {
    composable(
        route = Screen.PostDetail.route + "/{postId}",
        arguments = Screen.PostDetail.arguments,
        enterTransition = { _, _ ->
            slideInHorizontally(
                initialOffsetX = { 100 },
                animationSpec = tween(
                    durationMillis = 300,
                    easing = FastOutSlowInEasing
                )
            ) + fadeIn(animationSpec = tween(300))
        },
        popExitTransition = { _, target ->
            slideOutHorizontally(
                targetOffsetX = { 100 },
                animationSpec = tween(
                    durationMillis = 300,
                    easing = FastOutSlowInEasing
                )
            ) + fadeOut(animationSpec = tween(300))
        }
    ) {
        val viewModel: PostDetailViewModel = hiltViewModel()
        DiaryOpenPost(
            navigateToPostList = {
                navController.navigate(route = Screen.PostList.route)
            },
            navigateToEditPost = { postId ->
                navController.navigate(route = "${Screen.EditPost.route}/$postId")
            },
            state = viewModel.postDetailState.value
        )

    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.addEditPost(navController: NavController) {
    composable(
        route = Screen.EditPost.route + "/{postId}",
        arguments = Screen.EditPost.arguments,
        enterTransition = { _, _ ->
            slideInHorizontally(
                initialOffsetX = { 100 },
                animationSpec = tween(
                    durationMillis = 300,
                    easing = FastOutSlowInEasing
                )
            ) + fadeIn(animationSpec = tween(300))
        },
        popExitTransition = { _, target ->
            slideOutHorizontally(
                targetOffsetX = { 100 },
                animationSpec = tween(
                    durationMillis = 300,
                    easing = FastOutSlowInEasing
                )
            ) + fadeOut(animationSpec = tween(300))
        }
    ) {
        val viewModel: PostDetailEditViewModel = hiltViewModel()
        DiaryEditPost(
            navigateToPostList = {
                navController.navigate(route = Screen.PostList.route)
            },
            updatePost = { diaryItem ->
                viewModel.updateDiaryItem(diaryItem = diaryItem)
            } ,
            state = viewModel.postDetailEditState.value
        )

    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.addPost(navController: NavController) {
    composable(
        route = Screen.AddPost.route
    ) {
        val viewModel: PostDetailAddViewModel = hiltViewModel()
        DiaryNewPost(
            navigateToPostList = {
                navController.navigate(route = Screen.PostList.route)
            },
            addNewPost = {
                diaryItem ->
                viewModel.addDiaryItemToRepository(diaryItem = diaryItem)
            }
        )

    }
}
