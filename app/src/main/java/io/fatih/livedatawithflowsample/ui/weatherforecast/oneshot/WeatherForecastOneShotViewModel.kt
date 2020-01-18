package io.fatih.livedatawithflowsample.ui.weatherforecast.oneshot

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import io.fatih.livedatawithflowsample.data.weatherforecast.WeatherForecastRepository
import io.fatih.livedatawithflowsample.shared.Result
import javax.inject.Inject

class WeatherForecastOneShotViewModel @Inject constructor(
    weatherForecastRepository: WeatherForecastRepository
) : ViewModel() {

    private val _weatherForecast = weatherForecastRepository
        .fetchWeatherForecast()
        .asLiveData(viewModelScope.coroutineContext)

    val weatherForecast: LiveData<Result<Int>>
        get() = _weatherForecast
}
