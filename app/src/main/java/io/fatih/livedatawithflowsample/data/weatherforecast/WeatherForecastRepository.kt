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
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherForecastRepository @Inject constructor() {

    /**
     * This methods is used to make one shot request to get
     * fake weather forecast data
     */
    fun fetchWeatherForecast() = flow {
        emit(Result.Loading)
        // Fake api call
        delay(1000)
        // Send a random fake weather forecast data
        emit(Result.Success((0..20).random()))
    }

    /**
     * This method is used to get data stream of fake weather
     * forecast data in real time with 1000 ms delay
     */
    fun fetchWeatherForecastRealTime() : Flow<Result<Int>> = flow {
        // Fake data stream
        while (true) {
            delay(1000)
            // Send a random fake weather forecast data
            emit(Result.Success((0..20).random()))
        }
    }

    /**
     * This method is used to get data stream of fake weather
     * forecast data in real time from another fake data source
     * with 500 ms delay
     */
    fun fetchWeatherForecastRealTimeOtherDataSource() = flow {
        emit(Result.Loading)
        // Fake data stream
        while (true) {
            delay(500)
            // Send a random fake weather forecast data
            emit(Result.Success((0..20).random()))
        }
    }
}