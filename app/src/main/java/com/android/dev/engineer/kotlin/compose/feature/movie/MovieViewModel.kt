package com.android.dev.engineer.kotlin.compose.feature.movie

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    // TODO make it private
    val movieId by lazy { requireNotNull(savedStateHandle.get<Int>("id")) }
}