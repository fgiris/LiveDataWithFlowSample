package io.fatih.livedatawithflowsample.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class ViewModelProviderFactory @Inject constructor(
    private val creators: @JvmSuppressWildcards Map<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val entry = creators.entries.find {
            modelClass.isAssignableFrom(it.key)
        } ?: throw IllegalArgumentException("Unknown model class $modelClass")

        try {
            return entry.value.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}