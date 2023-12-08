package com.jimenard.weatherapp.di.repository

import com.jimenard.weatherapp.data.dataSource.WeatherDataSource
import com.jimenard.weatherapp.data.repository.WeatherRepository
import com.jimenard.weatherapp.data.repository.WeatherRepositoryImpl
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
    fun providesRepository(dataSource: WeatherDataSource): WeatherRepository =
        WeatherRepositoryImpl(dataSource)
}