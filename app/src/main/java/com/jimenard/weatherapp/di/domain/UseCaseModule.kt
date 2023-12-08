package com.jimenard.weatherapp.di.domain

import com.jimenard.weatherapp.data.repository.WeatherRepository
import com.jimenard.weatherapp.domain.LocationDetailUseCase
import com.jimenard.weatherapp.domain.LocationDetailUseCaseImpl
import com.jimenard.weatherapp.domain.SearchWeatherUseCase
import com.jimenard.weatherapp.domain.SearchWeatherUseCaseImpl
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
    fun providesWeatherUseCase(repository: WeatherRepository): SearchWeatherUseCase =
        SearchWeatherUseCaseImpl(repository)

    @Singleton
    @Provides
    fun providesLocationUseCase(repository: WeatherRepository): LocationDetailUseCase =
        LocationDetailUseCaseImpl(repository)
}