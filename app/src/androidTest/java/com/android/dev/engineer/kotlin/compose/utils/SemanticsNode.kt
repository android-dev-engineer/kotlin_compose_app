package com.android.dev.engineer.kotlin.compose.utils

import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.SemanticsNode
import com.google.common.truth.Truth.assertThat

fun SemanticsNode.assertHasModifier(modifier: Modifier) {
    val isModifierFound = layoutInfo.getModifierInfo().any { modifierInfo ->
        modifierInfo.modifier == modifier
    }
    assertThat(isModifierFound).isTrue()
}