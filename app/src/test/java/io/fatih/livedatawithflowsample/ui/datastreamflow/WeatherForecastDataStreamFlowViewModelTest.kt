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

package io.fatih.livedatawithflowsample.ui.datastreamflow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.fatih.livedatawithflowsample.common.MainCoroutineRule
import io.fatih.livedatawithflowsample.data.weatherforecast.WeatherForecastRepository
import io.fatih.livedatawithflowsample.shared.Result
import io.fatih.livedatawithflowsample.ui.weatherforecast.datastreamflow.WeatherForecastDataStreamFlowViewModel
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class WeatherForecastDataStreamFlowViewModelTest {
    lateinit var weatherForecastRepository: WeatherForecastRepository
    lateinit var viewModel: WeatherForecastDataStreamFlowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        weatherForecastRepository = WeatherForecastRepository()
    }

    @Test
    fun weatherForecastFlow_ShouldReturnLoadingThenSuccess() {
        mainCoroutineRule.testDispatcher.runBlockingTest {
            // Create view model
            viewModel = WeatherForecastDataStreamFlowViewModel(
                weatherForecastRepository
            )

            val resultList = mutableListOf<Result<Int>>()
            viewModel.weatherForecastFlow.toList(resultList)

            // Check whether first value is loading
            assert(resultList.first() == Result.Loading)

            // Check whether first value is loading
            assert(resultList.last() is Result.Success)
        }
    }
}