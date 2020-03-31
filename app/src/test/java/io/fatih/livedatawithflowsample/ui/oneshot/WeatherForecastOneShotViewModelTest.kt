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

package io.fatih.livedatawithflowsample.ui.oneshot

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.fatih.livedatawithflowsample.common.MainCoroutineRule
import io.fatih.livedatawithflowsample.data.weatherforecast.WeatherForecastRepository
import io.fatih.livedatawithflowsample.shared.Result
import io.fatih.livedatawithflowsample.ui.weatherforecast.oneshot.WeatherForecastOneShotViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class WeatherForecastOneShotViewModelTest {
    lateinit var weatherForecastRepository: WeatherForecastRepository
    lateinit var viewModel: WeatherForecastOneShotViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        weatherForecastRepository = WeatherForecastRepository()
        viewModel = WeatherForecastOneShotViewModel(
            weatherForecastRepository,
            mainCoroutineRule.testDispatcher
        )
    }

    @Test
    fun weatherForecastLiveData_ShouldPostLoadingThenSuccess() {
        mainCoroutineRule.testDispatcher.runBlockingTest {
            // Check whether the first value is loading
            assert(viewModel.weatherForecast.value == Result.Loading)

            // Wait for the response
            delay(1000)

            // Check whether the response is successful
            assert(viewModel.weatherForecast.value is Result.Success)
        }
    }

    @Test
    fun weatherForecastLiveData_ShouldPostLoadingThenSuccessWithoutDelay() {
        mainCoroutineRule.testDispatcher.runBlockingTest {
            // Pause dispatcher so that no new coroutines will be started
            mainCoroutineRule.testDispatcher.pauseDispatcher()

            // Initialization of view model includes api call with creating
            // new coroutine but it will not be invoked since we paused dispatcher
            viewModel = WeatherForecastOneShotViewModel(
                weatherForecastRepository,
                mainCoroutineRule.testDispatcher
            )

            // Check whether the first value is loading
            assert(viewModel.weatherForecast.value == Result.Loading)

            // Resume dispatcher
            mainCoroutineRule.testDispatcher.resumeDispatcher()

            // Check whether the response is successful
            assert(viewModel.weatherForecast.value is Result.Success)
        }
    }

    @Test
    fun weatherForecastLiveData_ShouldPostSuccessAfterMapping() {
        mainCoroutineRule.testDispatcher.runBlockingTest {
            // Check whether the first value is loading
            assert(viewModel.weatherForecast.value == Result.Loading)

            // Wait for the response
            delay(1000)

            // Check whether the response is successful
            assert(viewModel.weatherForecast.value is Result.Success)
        }
    }
}