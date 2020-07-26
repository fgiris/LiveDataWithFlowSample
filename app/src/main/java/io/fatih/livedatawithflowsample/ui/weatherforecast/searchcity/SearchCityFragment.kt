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

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import io.fatih.livedatawithflowsample.R
import kotlinx.android.synthetic.main.fragment_search_city.*
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class SearchCityFragment : Fragment() {

    private val viewModel: SearchCityViewModel by viewModels()
    private lateinit var adapter: SearchCityAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_search_city, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initView()
        observeFilteredCities()
    }

    private fun initView() {
        etCity.doAfterTextChanged {
            val key = it.toString()

            // Set loading indicator
            pbLoading.show()

            // Offer the current text to channel
            viewModel.cityFilterChannel.offer(key)
        }

        adapter = SearchCityAdapter(viewModel.cityList)
        rvCity.adapter = adapter
    }

    private fun observeFilteredCities() {
        lifecycleScope.launchWhenStarted {
            viewModel.cityFilterFlow.collect { filteredCities ->
                // Hide the progress bar
                pbLoading.hide()

                // Set filtered items
                adapter.setItems(filteredCities)
            }
        }
    }
}