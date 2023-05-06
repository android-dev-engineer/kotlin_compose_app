package com.android.dev.engineer.kotlin.compose.data.di

import com.android.dev.engineer.kotlin.compose.data.api.authentication.AuthenticationRepository
import com.android.dev.engineer.kotlin.compose.data.api.authentication.AuthenticationRepositoryImpl
import com.android.dev.engineer.kotlin.compose.data.api.movie.MovieRepository
import com.android.dev.engineer.kotlin.compose.data.api.movie.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideAuthenticationRepository(authenticationRepositoryImpl: AuthenticationRepositoryImpl): AuthenticationRepository {
        return authenticationRepositoryImpl
    }

    @Singleton
    @Provides
    fun provideMovieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository {
        return movieRepositoryImpl
    }
}