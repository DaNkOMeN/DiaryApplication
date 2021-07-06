package com.example.testdiary.composable

import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.testdiary.MainViewModel
import com.example.testdiary.data.DiaryItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


private const val DELAY_BETWEEN_SCROLL_MS = 8L
private const val SCROLL_DX = 1f

@Composable
fun <T : Any> AutoScrollingLazyColumn(
    items: List<T>,
    modifier: Modifier = Modifier,
    scrollDx: Float = SCROLL_DX,
    delayBetweenScrollMs: Long = DELAY_BETWEEN_SCROLL_MS,
    divider: @Composable () -> Unit = { Spacer(modifier = Modifier.height(9.dp)) },
    itemContent: @Composable (item: T) -> Unit,

) {
    var itemsListState by remember { mutableStateOf(items) }
    val lazyListState = rememberLazyListState()

    LazyColumn(
        state = lazyListState,
        modifier = modifier,
    ) {
        items(items) {
            itemContent(item = it )
            divider()

            if (it == itemsListState.last()) {
                val currentList = itemsListState

                val secondPart = currentList.subList(0, lazyListState.firstVisibleItemIndex)
                val firstPart = currentList.subList(lazyListState.firstVisibleItemIndex, currentList.size)

                rememberCoroutineScope().launch {
                    lazyListState.scrollToItem(0, maxOf(0, lazyListState.firstVisibleItemScrollOffset - scrollDx.toInt()))
                }

                itemsListState = firstPart + secondPart
            }
        }
    }
//    LaunchedEffect(Unit) {
//        autoScroll(lazyListState, scrollDx, delayBetweenScrollMs)
//    }
}

private tailrec suspend fun autoScroll(
    lazyListState: LazyListState,
    scrollDx: Float,
    delayBetweenScrollMs: Long,
) {
    lazyListState.scroll(MutatePriority.PreventUserInput) {
        scrollBy(scrollDx)
    }
    delay(delayBetweenScrollMs)

    autoScroll(lazyListState, scrollDx, delayBetweenScrollMs)
}
