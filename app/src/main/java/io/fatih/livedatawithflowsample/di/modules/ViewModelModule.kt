/*
 * Copyright (C) 2020 Fatih Giris. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.fatih.livedatawithflowsample.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.multibindings.IntoMap
import io.fatih.livedatawithflowsample.MainViewModel
import io.fatih.livedatawithflowsample.di.ViewModelKey
import io.fatih.livedatawithflowsample.di.ViewModelProviderFactory
import io.fatih.livedatawithflowsample.ui.weatherforecast.datastream.WeatherForecastDataStreamViewModel
import io.fatih.livedatawithflowsample.ui.weatherforecast.datastreamflow.WeatherForecastDataStreamFlowViewModel
import io.fatih.livedatawithflowsample.ui.weatherforecast.oneshot.WeatherForecastOneShotViewModel
import io.fatih.livedatawithflowsample.ui.weatherforecast.searchcity.SearchCityViewModel

@InstallIn(ApplicationComponent::class)
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
    @IntoMap
    @ViewModelKey(SearchCityViewModel::class)
    abstract fun bindSearchCityViewModel(
        viewModel: SearchCityViewModel
    ): ViewModel

    @Binds
    abstract fun bindViewModelFactory(
        factory: ViewModelProviderFactory
    ): ViewModelProvider.Factory
}