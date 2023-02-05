package com.example.avdyuhov.di

import com.example.avdyuhov.data.RepositoryImpl
import com.example.avdyuhov.domain.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * Created by Viacheslav Avd on 15.01.2023
 */
@Module
@InstallIn(ViewModelComponent::class)
abstract class DomainModule {
    @Binds
    abstract fun bindMoviesRepository(moviesRepository: RepositoryImpl): Repository
}