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

package io.fatih.livedatawithflowsample.data.weatherforecast

import io.fatih.livedatawithflowsample.shared.Result
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Test

class WeatherForecastRepositoryTest {
    private val weatherForecastRepository = WeatherForecastRepository()

    @Test
    fun fetchWeatherForecastSuspendCase_ShouldReturnSuccess() {
        runBlocking {
            // Api call
            val weatherForecast = weatherForecastRepository.fetchWeatherForecastSuspendCase()

            // Check whether the result is successful
            assert(weatherForecast is Result.Success)
        }
    }

    @Test
    fun fetchWeatherForecast_ShouldReturnLoadingThenResult_collect() {
        runBlocking {
            // Make api call
            val weatherForecastFlow = weatherForecastRepository.fetchWeatherForecast()
            weatherForecastFlow.collect {
                // Check whether first data is loading

                // Check whether second data is Success
            }
        }
    }

    @Test
    fun fetchWeatherForecast_ShouldReturnLoadingThenResult_collectIndexed() {
        runBlocking {
            // Make api call
            val weatherForecastFlow = weatherForecastRepository.fetchWeatherForecast()
            weatherForecastFlow.collectIndexed { index, value ->
                // Check whether first data is loading
                if (index == 0) assert(value == Result.Loading)

                // Check whether second data is Success
                if (index == 1) assert(value is Result.Success)
            }
        }
    }

    @Test
    fun fetchWeatherForecast_ShouldReturnLoadingThenResult_toList() {
        runBlocking {
            // List to keep weather forecast values
            val weatherForecastList = mutableListOf<Result<Int>>()

            // Make api call
            val weatherForecastFlow = weatherForecastRepository.fetchWeatherForecast()
            weatherForecastFlow.toList(weatherForecastList)

            // Check whether first data is loading
            assert(weatherForecastList[0] == Result.Loading)

            // Check whether second data is Success
            assert(weatherForecastList[1] is Result.Success)
        }
    }
}