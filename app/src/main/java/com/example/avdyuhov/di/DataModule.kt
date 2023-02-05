package com.example.avdyuhov.di

import com.example.avdyuhov.data.RepositoryImpl
import com.example.avdyuhov.data.remote.RemoteDataSource
import com.example.avdyuhov.data.remote.RetrofitDataSource
import com.example.avdyuhov.domain.Repository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

/**
 * Created by Viacheslav Avd on 15.01.2023
 */
@Module
@InstallIn(ViewModelComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindRemoteDataSource(remoteDataSource: RetrofitDataSource): RemoteDataSource

}