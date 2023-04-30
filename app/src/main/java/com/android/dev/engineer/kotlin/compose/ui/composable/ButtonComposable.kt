package com.android.dev.engineer.kotlin.compose.ui.composable

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.android.dev.engineer.kotlin.compose.R

@Composable
fun ButtonComposable(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier.testTag(tag = stringResource(id = R.string.test_tag_button)),
        onClick = onClick
    ) {
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewButtonComposable() {
    ButtonComposable(
        text = "Skip",
        onClick = {}
    )
}