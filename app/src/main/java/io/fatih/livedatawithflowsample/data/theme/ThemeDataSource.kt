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

package io.fatih.livedatawithflowsample.data.theme

import android.content.SharedPreferences
import io.fatih.livedatawithflowsample.Constants
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@ExperimentalCoroutinesApi
class ThemeDataSource @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
    private val themeChannel: ConflatedBroadcastChannel<Theme> by lazy {
        ConflatedBroadcastChannel<Theme>().also { channel ->
            // When there is an access to theme channel
            // get the current theme from shared preferences
            // and send it to consumers
            val theme = sharedPreferences.getString(
                Constants.PREFERENCE_KEY_THEME,
                null
            ) ?: Theme.LIGHT.name // Default theme is light

            channel.offer(Theme.valueOf(theme))
        }
    }

    @FlowPreview
    fun getTheme(): Flow<Theme> {
        return themeChannel.asFlow()
    }

    fun setTheme(theme: Theme) {
        // Save theme to shared preferences
        sharedPreferences
            .edit()
            .putString(Constants.PREFERENCE_KEY_THEME, theme.name)
            .apply()

        // Notify consumers
        themeChannel.offer(theme)
    }
}