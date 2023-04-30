package com.android.dev.engineer.kotlin.compose.composable

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.dp
import com.android.dev.engineer.kotlin.compose.R
import com.android.dev.engineer.kotlin.compose.ui.composable.ButtonComposable
import com.android.dev.engineer.kotlin.compose.ui.theme.KotlinComposeAppTheme
import com.android.dev.engineer.kotlin.compose.utils.assertHasModifier
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class ButtonComposableTest {
    companion object {
        private const val TITLE = "Skip"
    }

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun testTitle() {
        with(composeTestRule) {
            setContent {
                KotlinComposeAppTheme {
                    ButtonComposable(
                        text = TITLE,
                        onClick = {}
                    )
                }
            }
            onNodeWithTag(testTag = activity.getString(R.string.test_tag_button)).assertTextEquals(TITLE)
        }
    }

    @Test
    fun testModifiers() {
        with(composeTestRule) {
            val modifier = Modifier.padding(bottom = 8.dp)
            setContent {
                KotlinComposeAppTheme {
                    ButtonComposable(
                        modifier = modifier,
                        text = TITLE,
                        onClick = {}
                    )
                }
            }
            onNodeWithTag(testTag = activity.getString(R.string.test_tag_button))
                .fetchSemanticsNode()
                .assertHasModifier(modifier = modifier)
        }
    }

    @Test
    fun testClick() {
        var clicked = false
        with(composeTestRule) {
            setContent {
                KotlinComposeAppTheme {
                    ButtonComposable(
                        text = TITLE,
                        onClick = { clicked = true }
                    )
                }
            }
            assertEquals(false, clicked)
            onNodeWithTag(testTag = activity.getString(R.string.test_tag_button)).performClick()
            assertEquals(true, clicked)
        }
    }
}