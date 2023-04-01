package com.android.dev.engineer.kotlin.compose.feature.intro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.dev.engineer.kotlin.compose.data.domain.MainNavGraph
import com.android.dev.engineer.kotlin.compose.data.use_case.mark_intro.MarkIntroCompleteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IntroViewModel @Inject constructor(
    private val markIntroCompleteUseCase: MarkIntroCompleteUseCase
) : ViewModel() {
    private val _effect by lazy { MutableSharedFlow<MainNavGraph>() }
    val effect = _effect.asSharedFlow()

    fun markIntroComplete() {
        viewModelScope.launch {
            try {
                markIntroCompleteUseCase.invoke()
                _effect.emit(MainNavGraph.SignIn)
            } catch (_: Exception) {
                // TODO add log
            }
        }
    }
}