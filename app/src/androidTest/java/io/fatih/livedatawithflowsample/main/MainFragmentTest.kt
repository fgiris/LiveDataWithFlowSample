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

package io.fatih.livedatawithflowsample.main

import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import io.fatih.livedatawithflowsample.R
import io.fatih.livedatawithflowsample.di.modules.CoroutineModule
import io.fatih.livedatawithflowsample.ui.main.MainFragment
import io.fatih.livedatawithflowsample.util.launchFragmentInHiltContainer
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@UninstallModules(CoroutineModule::class)
@HiltAndroidTest
class MainFragmentTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    // Test navigation controller for MainFragment
    lateinit var testNavController: TestNavHostController

    @Before
    fun setup() {
        setupTestNavigationController()
        launchFragmentInHiltContainer<MainFragment> {
            // Setup MainFragment with test navigation controller
            Navigation.setViewNavController(requireView(), testNavController)
        }
    }

    @Test
    fun clickCheckWeatherForecastOneShot_ShouldNavigateToWeatherForecastOneShotFragment() {
        onView(withId(R.id.btnWeatherForecast)).perform(click())

        // Verify if the nav controller has WeatherForecastOneShotFragment as a current destination
        assert(testNavController.currentDestination?.id == R.id.weatherForecastOneShotFragment)
    }

    private fun setupTestNavigationController() {
        testNavController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        ).also {
            // Set the graph and the current destination for MainFragment
            it.setGraph(R.navigation.main_nav_graph)
            it.setCurrentDestination(R.id.mainFragment)
        }
    }
}