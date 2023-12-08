package com.jimenard.weatherapp.di.dataSource

import com.jimenard.weatherapp.data.dataSource.WeatherDataSource
import com.jimenard.weatherapp.data.dataSource.WeatherDataSourceImpl
import com.jimenard.weatherapp.data.network.api.WeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun providesDataSource(weatherApi: WeatherApi): WeatherDataSource =
        WeatherDataSourceImpl(weatherApi)
}