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

package io.fatih.livedatawithflowsample.ui.datastream

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.fatih.livedatawithflowsample.common.MainCoroutineRule
import io.fatih.livedatawithflowsample.data.weatherforecast.WeatherForecastRepository
import io.fatih.livedatawithflowsample.shared.Result
import io.fatih.livedatawithflowsample.ui.weatherforecast.datastream.WeatherForecastDataStreamViewModel
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class WeatherForecastDataStreamViewModelTest {
    lateinit var weatherForecastRepository: WeatherForecastRepository
    lateinit var viewModel: WeatherForecastDataStreamViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        weatherForecastRepository = WeatherForecastRepository()
    }

    @Test
    fun weatherForecastLiveData_ShouldPostLoadingThenSuccess() {
        mainCoroutineRule.testDispatcher.runBlockingTest {
            viewModel = WeatherForecastDataStreamViewModel(
                weatherForecastRepository,
                mainCoroutineRule.testDispatcher
            )

            val actualResultList = mutableListOf<Result<Int>>()

            viewModel.weatherForecast.observeForever {
                actualResultList.add(it)
                // TODO() Remove observer after some time or all the results have been observed
            }

            // Check whether the first value is loading
            assert(actualResultList[0] == Result.Loading)

            // Check whether the response is successful
//            assert(viewModel.weatherForecast.value is Result.Success)
        }
    }
}
