package com.android.dev.engineer.kotlin.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.android.dev.engineer.kotlin.compose.intro.IntroScreen
import com.android.dev.engineer.kotlin.compose.ui.theme.KotilinComposeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotilinComposeAppTheme {
                IntroScreen(
                    onClickSkip = {
                        // TODO Implement skip functionality, this is gonna be included with navigation compose
                    }
                )
            }
        }
    }
}