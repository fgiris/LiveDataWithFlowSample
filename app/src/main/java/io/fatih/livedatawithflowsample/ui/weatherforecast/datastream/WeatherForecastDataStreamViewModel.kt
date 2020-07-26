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

package io.fatih.livedatawithflowsample.ui.weatherforecast.datastream

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import io.fatih.livedatawithflowsample.data.weatherforecast.WeatherForecastRepository
import io.fatih.livedatawithflowsample.di.DispatcherDefault
import io.fatih.livedatawithflowsample.shared.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WeatherForecastDataStreamViewModel @ViewModelInject constructor(
    weatherForecastRepository: WeatherForecastRepository,
    @DispatcherDefault defaultDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _weatherForecast = weatherForecastRepository
        .fetchWeatherForecastRealTime()
        .map {
            // Do some heavy operation. This operation will be done in the
            // scope of this flow collected. In our case it is the scope
            // passed to asLiveData extension function
            // This operation will not block the UI
            delay(1000)
            it
        }
        .asLiveData(
            // Use Default dispatcher for CPU intensive work and
            // viewModel scope for auto cancellation
            viewModelScope.coroutineContext + defaultDispatcher
        )

    val weatherForecast: LiveData<Result<Int>>
        get() = _weatherForecast
}
