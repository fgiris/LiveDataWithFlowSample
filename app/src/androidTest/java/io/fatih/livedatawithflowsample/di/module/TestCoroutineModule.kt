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

package io.fatih.livedatawithflowsample.di.module

import android.os.AsyncTask
import dagger.Module
import dagger.Provides
import io.fatih.livedatawithflowsample.di.DispatcherDefault
import io.fatih.livedatawithflowsample.di.DispatcherIo
import io.fatih.livedatawithflowsample.di.DispatcherMain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher

/**
 * Uses [AsyncTask.THREAD_POOL_EXECUTOR.asCoroutineDispatcher()] to replace
 * [Dispatchers.IO] and [Dispatchers.Default]. With doing this way, Espresso
 * will respect the background work.
 * See:
 * https://medium.com/androiddevelopers/coroutines-patterns-for-work-that-shouldnt-be-cancelled-e26c40f142ad
 */
@Module
object TestCoroutineModule {

    @DispatcherMain
    @Provides
    fun provideDispatcherMain() = Dispatchers.Main

    @DispatcherIo
    @Provides
    fun provideDispatcherIo() = AsyncTask.THREAD_POOL_EXECUTOR.asCoroutineDispatcher()

    @DispatcherDefault
    @Provides
    fun provideDispatcherDefault() = AsyncTask.THREAD_POOL_EXECUTOR.asCoroutineDispatcher()
}