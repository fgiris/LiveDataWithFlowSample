package io.fatih.livedatawithflowsample.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.fatih.livedatawithflowsample.MainViewModel
import io.fatih.livedatawithflowsample.ui.weatherforecast.datastream.WeatherForecastDataStreamViewModel
import io.fatih.livedatawithflowsample.ui.weatherforecast.datastreamflow.WeatherForecastDataStreamFlowViewModel
import io.fatih.livedatawithflowsample.ui.weatherforecast.oneshot.WeatherForecastOneShotViewModel

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(WeatherForecastOneShotViewModel::class)
    abstract fun bindWeatherForecastOneShotViewModel(
        viewModel: WeatherForecastOneShotViewModel
    ): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WeatherForecastDataStreamViewModel::class)
    abstract fun bindWeatherForecastDataStreamViewModel(
        viewModel: WeatherForecastDataStreamViewModel
    ): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WeatherForecastDataStreamFlowViewModel::class)
    abstract fun bindWeatherForecastDataStreamFlowViewModel(
        viewModel: WeatherForecastDataStreamFlowViewModel
    ): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(
        viewModel: MainViewModel
    ): ViewModel

    @Binds
    abstract fun bindViewModelFactory(
        factory: ViewModelProviderFactory
    ): ViewModelProvider.Factory
}