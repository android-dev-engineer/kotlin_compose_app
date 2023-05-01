package com.android.dev.engineer.kotlin.compose.feature.sign_in

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.android.dev.engineer.kotlin.compose.ui.theme.KotlinComposeAppTheme
import com.android.dev.engineer.kotlin.compose.util.ExcludeFromJacocoGeneratedReport

@Composable
fun SignInScreen() {
    SignInScreenComposable()
}

// TODO implement sign in screen
@Composable
fun SignInScreenComposable() {
    KotlinComposeAppTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Coming soon"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
@ExcludeFromJacocoGeneratedReport
private fun PreviewSignInScreenComposable() {
    SignInScreenComposable()
}