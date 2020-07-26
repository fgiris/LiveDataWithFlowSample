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

package io.fatih.livedatawithflowsample.ui.weatherforecast.datastreamflow

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import io.fatih.livedatawithflowsample.data.weatherforecast.WeatherForecastRepository
import io.fatih.livedatawithflowsample.di.DispatcherDefault
import io.fatih.livedatawithflowsample.shared.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@ExperimentalCoroutinesApi
class WeatherForecastDataStreamFlowViewModel @ViewModelInject constructor(
    weatherForecastRepository: WeatherForecastRepository,
    @DispatcherDefault defaultDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _weatherForecastOtherDataSource = weatherForecastRepository
        .fetchWeatherForecastRealTimeOtherDataSource()

    private val _weatherForecast = weatherForecastRepository
        .fetchWeatherForecastRealTime()
        .onStart {
            emit(Result.Loading)
        }
        .distinctUntilChanged()
        .filter {
            // There could be millions of data when filtering
            // Do some filtering
            delay(2000)

            // Let's add an additional filtering to take only
            // data which is less than 10
            if (it is Result.Success) {
                it.data < 10
            } else true // Do nothing if result is loading or error
        }
        .buffer()
        .map {
            // Do some heavy mapping
            delay(500)

            // Let's add an additional mapping to convert
            // celsius degree to Fahrenheit
            if (it is Result.Success) {
                val fahrenheitDegree = convertCelsiusToFahrenheit(it.data)
                Result.Success(fahrenheitDegree)
            } else it // Do nothing if result is loading or error
        }
        .transform {
            // Let's send only even numbers
            if (it is Result.Success && it.data % 2 == 0) {
                val evenDegree = it.data
                emit(Result.Success(evenDegree))
            } else emit(it) // Do nothing if result is loading or error
        }
        .onEach {
            // Do something with the modified data. For instance
            // save the modified data to cache
            println("$it has modified and reached until onEach operator")
        }
        .flowOn(defaultDispatcher) // Changes the context of flow
        .catch { throwable ->
            // Catch exceptions in all down stream flow
            // Any error occurs after this catch operator
            // will not be caught here
            println(throwable)
        }

    // Merge flows when consumer gets
    val weatherForecast: Flow<Result<Int>>
        get() = merge(_weatherForecast, _weatherForecastOtherDataSource)

    /**
     * This function converts given [celsius] to Fahrenheit.
     *
     * Fahrenheit degree = Celsius degree * 9 / 5 + 32
     *
     * @return Fahrenheit integer for [celsius]
     */
    private fun convertCelsiusToFahrenheit(celsius: Int) = celsius * 9 / 5 + 32
}
