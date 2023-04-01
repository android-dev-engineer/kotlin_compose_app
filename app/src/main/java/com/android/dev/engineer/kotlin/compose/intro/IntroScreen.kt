@file:OptIn(ExperimentalFoundationApi::class)

package com.android.dev.engineer.kotlin.compose.intro

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.dev.engineer.kotlin.compose.composable.ButtonComposable
import com.android.dev.engineer.kotlin.compose.composable.PagerIndicatorComposable
import com.android.dev.engineer.kotlin.compose.data.domain.MainNavGraph
import com.android.dev.engineer.kotlin.compose.ui.theme.KotlinComposeAppTheme
import kotlinx.coroutines.launch

@Composable
fun IntroScreen(
    onSkipClicked: (MainNavGraph) -> Unit
) {
    val introItems = listOf(
        IntroItem(title = "Page 1", imageVector = Icons.Filled.Call),
        IntroItem(title = "Page 2", imageVector = Icons.Filled.Search),
        IntroItem(title = "Page 3", imageVector = Icons.Filled.Info),
        IntroItem(title = "Page 4", imageVector = Icons.Filled.List)
    )
    IntroScreenComposable(
        introItems = introItems,
        onClickSkip = {
            // TODO update via ViewModel
            onSkipClicked(MainNavGraph.SignIn)
        }
    )
}

@Composable
private fun IntroScreenComposable(
    introItems: List<IntroItem>,
    onClickSkip: () -> Unit
) {
    KotlinComposeAppTheme {
        val pagerState = rememberPagerState()
        val coroutineScope = rememberCoroutineScope()
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            HorizontalPager(
                pageCount = introItems.size,
                state = pagerState,
                pageContent = { pageIndex ->
                    with(introItems[pageIndex]) {
                        Column(
                            modifier = Modifier
                                .padding(all = 16.dp)
                                .fillMaxHeight(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                modifier = Modifier.padding(bottom = 8.dp),
                                contentDescription = title,
                                imageVector = imageVector,
                                colorFilter = ColorFilter.tint(color = MaterialTheme.colors.primary)
                            )
                            Text(
                                text = title,
                                style = MaterialTheme.typography.h5,
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colors.primary
                            )
                        }
                    }
                }
            )

            ButtonComposable(
                modifier = Modifier
                    .padding(all = 16.dp)
                    .align(alignment = Alignment.TopEnd),
                text = "Skip",
                onClick = { onClickSkip() }
            )

            PagerIndicatorComposable(
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .align(alignment = Alignment.BottomCenter),
                selectedIndex = pagerState.currentPage,
                total = introItems.size,
                onClick = { index ->
                    coroutineScope.launch { pagerState.scrollToPage(index) }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewIntroScreen() {
    IntroScreenComposable(
        introItems = listOf(
            IntroItem(title = "Page 1", imageVector = Icons.Filled.Call),
            IntroItem(title = "Page 2", imageVector = Icons.Filled.Search),
            IntroItem(title = "Page 3", imageVector = Icons.Filled.Info),
            IntroItem(title = "Page 4", imageVector = Icons.Filled.List)
        ),
        onClickSkip = {}
    )
}