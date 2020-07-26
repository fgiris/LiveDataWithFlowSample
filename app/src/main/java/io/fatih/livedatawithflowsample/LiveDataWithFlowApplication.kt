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

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.components.ApplicationComponent

@HiltAndroidApp
open class LiveDataWithFlowApplication : DaggerApplication() {

    // This is needed to make a sanity check for the classes that uses
    // from Dagger such as DaggerActivity or DaggerFragment.
    // This Entry point will provide the AndroidInjector to those classes.
    @EntryPoint
    @InstallIn(ApplicationComponent::class)
    interface ApplicationInjector : AndroidInjector<LiveDataWithFlowApplication>

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        // Get the application injector from the Entry point
        return EntryPointAccessors.fromApplication(
            this,
            ApplicationInjector::class.java
        )
    }
}