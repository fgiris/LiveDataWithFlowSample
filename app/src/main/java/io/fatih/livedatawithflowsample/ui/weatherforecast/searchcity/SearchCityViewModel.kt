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

package io.fatih.livedatawithflowsample.ui.weatherforecast.searchcity

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map

class SearchCityViewModel @ViewModelInject constructor() : ViewModel() {
    val cityList = listOf(
        "Los Angeles", "Chicago", "Indianapolis", "Phoenix", "Houston",
        "Denver", "Las Vegas", "Philadelphia", "Portland", "Seattle"
    )

    // Channel to hold the text value inside search box
    val cityFilterChannel = ConflatedBroadcastChannel<String>()

    // Flow which observes channel and sends filtered list
    // whenever there is a update in the channel. This is
    // observed in UI to get filtered result
    val cityFilterFlow: Flow<List<String>> = cityFilterChannel
        .asFlow()
        .map {
            // Filter cities with new value
            val filteredCities = filterCities(it)

            // Do some heavy work
            delay(500)

            // Return the filtered list
            filteredCities
        }

    override fun onCleared() {
        super.onCleared()

        // Close the channel when ViewModel is destroyed
        cityFilterChannel.close()
    }

    /**
     * This function filters [cityList] if a city contains
     * the given [key]. If key is an empty string then this
     * function does not do any filtering.
     *
     * @param key Key to filter out the list
     *
     * @return List of cities containing the [key]
     */
    private fun filterCities(key: String): List<String> {
        return cityList.filter {
            it.contains(key)
        }
    }
}