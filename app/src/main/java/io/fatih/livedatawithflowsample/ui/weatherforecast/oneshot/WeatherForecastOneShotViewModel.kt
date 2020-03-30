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

package io.fatih.livedatawithflowsample.ui.weatherforecast.oneshot

import androidx.lifecycle.*
import io.fatih.livedatawithflowsample.data.weatherforecast.WeatherForecastRepository
import io.fatih.livedatawithflowsample.shared.Result
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherForecastOneShotViewModel @Inject constructor(
    private val weatherForecastRepository: WeatherForecastRepository
) : ViewModel() {

    private var _weatherForecast = MutableLiveData<Result<Int>>()
    val weatherForecast: LiveData<Result<Int>>
        get() = _weatherForecast

    init {
        fetchWeatherForecast()
    }

    private fun fetchWeatherForecast() {
        // Set value as loading
        _weatherForecast.value = Result.Loading

        viewModelScope.launch {
            // Fetch and update weather forecast LiveData
            _weatherForecast.value = weatherForecastRepository.fetchWeatherForecastSuspendCase()
        }
    }
}
