package com.android.dev.engineer.kotlin.compose.data.di

import com.android.dev.engineer.kotlin.compose.data.use_case.initial_route.GetInitialRouteUseCase
import com.android.dev.engineer.kotlin.compose.data.use_case.initial_route.GetInitialRouteUseCaseImpl
import com.android.dev.engineer.kotlin.compose.data.use_case.mark_intro.MarkIntroCompleteUseCase
import com.android.dev.engineer.kotlin.compose.data.use_case.mark_intro.MarkIntroCompleteUseCaseImpl
import com.android.dev.engineer.kotlin.compose.data.use_case.upcoming_movie.GetUpcomingMoviesUseCase
import com.android.dev.engineer.kotlin.compose.data.use_case.upcoming_movie.GetUpcomingMoviesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Singleton
    @Provides
    fun provideGetInitialRouteUseCase(getInitialRouteUseCaseImpl: GetInitialRouteUseCaseImpl): GetInitialRouteUseCase {
        return getInitialRouteUseCaseImpl
    }

    @Singleton
    @Provides
    fun provideMarkIntroCompleteUseCase(markIntroCompleteUseCaseImpl: MarkIntroCompleteUseCaseImpl): MarkIntroCompleteUseCase {
        return markIntroCompleteUseCaseImpl
    }

    @Singleton
    @Provides
    fun provideUpcomingMoviesUseCase(getUpcomingMoviesUseCaseImpl: GetUpcomingMoviesUseCaseImpl): GetUpcomingMoviesUseCase {
        return getUpcomingMoviesUseCaseImpl
    }
}