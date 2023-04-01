package com.android.dev.engineer.kotlin.compose.feature.routing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.dev.engineer.kotlin.compose.data.domain.MainNavGraph
import com.android.dev.engineer.kotlin.compose.data.use_case.initial_route.GetInitialRouteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoutingViewModel @Inject constructor(
    private val getInitialRouteUseCase: GetInitialRouteUseCase
) : ViewModel() {
    private val _effect by lazy { MutableSharedFlow<MainNavGraph>() }
    val effect = _effect.asSharedFlow()

    init {
        getInitialRoute()
    }

    private fun getInitialRoute() {
        viewModelScope.launch {
            try {
                val mainNavGraph = getInitialRouteUseCase.invoke()
                _effect.emit(mainNavGraph)
            } catch (ex: Exception) {
                // TODO add log
            }
        }
    }
}