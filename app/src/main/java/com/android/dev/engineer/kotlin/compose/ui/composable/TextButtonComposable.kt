package com.android.dev.engineer.kotlin.compose.ui.composable

import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.android.dev.engineer.kotlin.compose.util.ExcludeFromJacocoGeneratedReport

@Composable
fun TextButtonComposable(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    TextButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
@ExcludeFromJacocoGeneratedReport
private fun PreviewButtonComposable() {
    TextButtonComposable(
        text = "Skip",
        onClick = {}
    )
}