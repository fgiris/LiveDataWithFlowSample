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

package io.fatih.livedatawithflowsample

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import io.fatih.livedatawithflowsample.data.theme.Theme
import io.fatih.livedatawithflowsample.data.theme.ThemeDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
class MainViewModel @ViewModelInject constructor(
    private val themeDataSource: ThemeDataSource
) : ViewModel() {
    // Whenever there is a change in theme, it will be
    // converted to live data
    private val _theme: LiveData<Theme> = themeDataSource
        .getTheme()
        .asLiveData(viewModelScope.coroutineContext)

    val theme: LiveData<Theme>
        get() = _theme

    fun setTheme(theme: Theme) {
        themeDataSource.setTheme(theme)
    }
}