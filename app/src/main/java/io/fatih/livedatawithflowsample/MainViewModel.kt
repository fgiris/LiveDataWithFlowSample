package io.fatih.livedatawithflowsample

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
class MainViewModel @Inject constructor(
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