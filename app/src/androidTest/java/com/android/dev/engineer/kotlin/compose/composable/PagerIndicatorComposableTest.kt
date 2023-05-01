package com.android.dev.engineer.kotlin.compose.composable

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.dp
import com.android.dev.engineer.kotlin.compose.R
import com.android.dev.engineer.kotlin.compose.ui.composable.PagerIndicatorComposable
import com.android.dev.engineer.kotlin.compose.ui.theme.KotlinComposeAppTheme
import com.android.dev.engineer.kotlin.compose.ui.theme.Purple500
import com.android.dev.engineer.kotlin.compose.utils.assertHasModifier
import org.junit.Rule
import org.junit.Test

class PagerIndicatorComposableTest {
    companion object {
        private const val FOUR_IN_TOTAL = 4
        private val DEFAULT_SIZE = 24.dp
        private val DEFAULT_SPACING = 4.dp
    }

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun testScrollable() {
        with(composeTestRule) {
            setContent {
                KotlinComposeAppTheme {
                    PagerIndicatorComposable(
                        selectedIndex = 0,
                        total = FOUR_IN_TOTAL,
                        onClick = {}
                    )
                }
            }
            with(onNodeWithTag(testTag = activity.getString(R.string.test_tag_pager_indicator))) {
                assertIsDisplayed()
                assert(hasScrollAction())
            }
        }
    }

    @Test
    fun testModifiers() {
        with(composeTestRule) {
            val modifier = Modifier.padding(bottom = DEFAULT_SPACING)
            setContent {
                KotlinComposeAppTheme {
                    PagerIndicatorComposable(
                        modifier = modifier,
                        selectedIndex = 0,
                        total = FOUR_IN_TOTAL,
                        onClick = {}
                    )
                }
            }
            onNodeWithTag(testTag = activity.getString(R.string.test_tag_pager_indicator))
                .fetchSemanticsNode()
                .assertHasModifier(modifier = modifier)
        }
    }

    @Test
    fun testSelectedCircleStyle() {
        with(composeTestRule) {
            val color = Color(color = activity.getColor(R.color.black))

            setContent {
                KotlinComposeAppTheme {
                    PagerIndicatorComposable(
                        selectedIndex = 0,
                        total = FOUR_IN_TOTAL,
                        size = DEFAULT_SIZE,
                        spacing = DEFAULT_SPACING,
                        color = color,
                        onClick = {}
                    )
                }
            }

            with(onNodeWithTag(testTag = activity.getString(R.string.test_tag_pager_indicator_selected))) {
                assertHasClickAction()
                fetchSemanticsNode().assertHasModifier(modifier = Modifier.padding(all = DEFAULT_SPACING))
                fetchSemanticsNode().assertHasModifier(modifier = Modifier.size(size = DEFAULT_SIZE))
                fetchSemanticsNode().assertHasModifier(modifier = Modifier.clip(shape = CircleShape))
                fetchSemanticsNode().assertHasModifier(modifier = Modifier.background(color = color))
            }
        }
    }

    @Test
    fun testDefaultCircleStyle() {
        with(composeTestRule) {
            setContent {
                KotlinComposeAppTheme {
                    PagerIndicatorComposable(
                        selectedIndex = 0,
                        total = FOUR_IN_TOTAL,
                        onClick = {}
                    )
                }
            }
            with(onNodeWithTag(testTag = activity.getString(R.string.test_tag_pager_indicator_selected))) {
                assertHasClickAction()
                fetchSemanticsNode().assertHasModifier(modifier = Modifier.padding(all = 8.dp))
                fetchSemanticsNode().assertHasModifier(modifier = Modifier.size(size = 12.dp))
                fetchSemanticsNode().assertHasModifier(modifier = Modifier.clip(shape = CircleShape))
                fetchSemanticsNode().assertHasModifier(modifier = Modifier.background(color = Purple500))
            }
        }
    }

    @Test
    fun testUnselectedCircleStyle() {
        with(composeTestRule) {
            setContent {
                KotlinComposeAppTheme {
                    PagerIndicatorComposable(
                        selectedIndex = 0,
                        total = FOUR_IN_TOTAL,
                        size = DEFAULT_SIZE,
                        spacing = DEFAULT_SPACING,
                        onClick = {}
                    )
                }
            }

            val unselectedIndicator = onAllNodesWithTag(testTag = activity.getString(R.string.test_tag_pager_indicator_unselected)).onFirst()
            with(unselectedIndicator) {
                assertHasClickAction()
                fetchSemanticsNode().assertHasModifier(modifier = Modifier.padding(all = DEFAULT_SPACING))
                fetchSemanticsNode().assertHasModifier(modifier = Modifier.size(size = DEFAULT_SIZE))
            }
        }
    }

    @Test
    fun testOnClick() {
        with(composeTestRule) {
            var selectedIndex by mutableStateOf(0)
            setContent {
                KotlinComposeAppTheme {
                    PagerIndicatorComposable(
                        selectedIndex = selectedIndex,
                        total = FOUR_IN_TOTAL,
                        onClick = { index -> selectedIndex = index }
                    )
                }
            }

            val selectedIndicators = onAllNodesWithTag(testTag = activity.getString(R.string.test_tag_pager_indicator_selected))
            val unselectedIndicators = onAllNodesWithTag(testTag = activity.getString(R.string.test_tag_pager_indicator_unselected))

            selectedIndicators.assertCountEquals(1)
            unselectedIndicators.assertCountEquals(FOUR_IN_TOTAL - 1)

            unselectedIndicators[1].performClick()
            unselectedIndicators[0].performClick()
            unselectedIndicators[2].performClick()

            selectedIndicators.assertCountEquals(1)
            unselectedIndicators.assertCountEquals(FOUR_IN_TOTAL - 1)
        }
    }
}