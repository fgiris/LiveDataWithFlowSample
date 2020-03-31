package io.fatih.livedatawithflowsample.common

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * Copied from:
 * https://github.com/android/plaid/blob/master/test_shared/src/main/java/io/plaidapp/test/shared/MainCoroutineRule.kt
 *
 * Sets the main coroutines dispatcher to a [TestCoroutineDispatcher] for unit testing. A
 * [TestCoroutineDispatcher] provides control over the execution of coroutines.
 *
 * Declare it as a JUnit Rule:
 *
 * ```
 * @get:Rule
 * var mainCoroutineRule = MainCoroutineRule()
 * ```
 *
 * Use the test dispatcher variable to modify the execution of coroutines
 *
 * ```
 * // This pauses the execution of coroutines
 * mainCoroutineRule.testDispatcher.pauseDispatcher()
 * ...
 * // This resumes the execution of coroutines
 * mainCoroutineRule.testDispatcher.resumeDispatcher()
 * ...
 * // This executes the coroutines running on testDispatcher synchronously
 * mainCoroutineRule.runBlocking { }
 * ```
 */
@ExperimentalCoroutinesApi
class MainCoroutineRule(
        val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
) : TestWatcher() {

    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}