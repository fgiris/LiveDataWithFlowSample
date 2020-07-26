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

package io.fatih.livedatawithflowsample.di.modules

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import io.fatih.livedatawithflowsample.LiveDataWithFlowApplication
import io.fatih.livedatawithflowsample.Constants

@Module
class AppModule {
    @Provides
    fun provideContext(application: LiveDataWithFlowApplication): Context {
        return application.applicationContext
    }

    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(
                Constants.SHARED_PREFERENCES_NAME,
                Context.MODE_PRIVATE
        )
    }
}