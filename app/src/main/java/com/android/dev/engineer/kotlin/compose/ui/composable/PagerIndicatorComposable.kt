package com.android.dev.engineer.kotlin.compose.ui.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

import com.android.dev.engineer.kotlin.compose.R

@Composable
fun PagerIndicatorComposable(
    modifier: Modifier = Modifier,
    selectedIndex: Int,
    total: Int,
    size: Dp = 12.dp,
    spacing: Dp = 8.dp,
    color: Color = MaterialTheme.colors.primary,
    onClick: (Int) -> Unit
) {
    LazyRow(
        modifier = modifier.testTag(tag = stringResource(id = R.string.test_tag_pager_indicator))
    ) {
        items(total) { index ->
            Box(
                modifier = Modifier
                    .clickable { onClick(index) }
                    .padding(all = spacing)
                    .size(size = size)
                    .then(
                        if (index == selectedIndex) {
                            Modifier
                                .testTag(tag = stringResource(id = R.string.test_tag_pager_indicator_selected))
                                .clip(shape = CircleShape)
                                .background(color = color)
                        } else {
                            Modifier
                                .testTag(tag = stringResource(id = R.string.test_tag_pager_indicator_unselected))
                                .border(
                                    border = BorderStroke(
                                        width = 2.dp,
                                        color = color
                                    ),
                                    shape = CircleShape
                                )
                        }
                    )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewPagerIndicator() {
    var selectedIndex by remember { mutableStateOf(0) }
    PagerIndicatorComposable(
        selectedIndex = selectedIndex,
        total = 4,
        onClick = { index -> selectedIndex = index }
    )
}