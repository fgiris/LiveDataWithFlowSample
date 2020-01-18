package io.fatih.livedatawithflowsample.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.fatih.livedatawithflowsample.ui.main.MainFragment
import io.fatih.livedatawithflowsample.ui.weatherforecast.datastream.WeatherForecastDataStreamFragment
import io.fatih.livedatawithflowsample.ui.weatherforecast.datastreamflow.WeatherForecastDataStreamFlowFragment
import io.fatih.livedatawithflowsample.ui.weatherforecast.oneshot.WeatherForecastOneShotFragment

@Module
abstract class FragmentBuilderModule {
    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): MainFragment

    @ContributesAndroidInjector
    abstract fun contributeWeatherForecastDataStreamFragment(): WeatherForecastDataStreamFragment

    @ContributesAndroidInjector
    abstract fun contributeWeatherForecastDataStreamFlowFragment(): WeatherForecastDataStreamFlowFragment

    @ContributesAndroidInjector
    abstract fun contributeWeatherForecastOneShotFragment(): WeatherForecastOneShotFragment
}