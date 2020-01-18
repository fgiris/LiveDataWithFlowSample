package io.fatih.livedatawithflowsample.ui.weatherforecast.datastream

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import io.fatih.livedatawithflowsample.data.weatherforecast.WeatherForecastRepository
import io.fatih.livedatawithflowsample.shared.Result
import javax.inject.Inject

class WeatherForecastDataStreamViewModel @Inject constructor(
    weatherForecastRepository: WeatherForecastRepository
) : ViewModel() {

    private val _weatherForecast = weatherForecastRepository
        .fetchWeatherForecastRealTime()
        .asLiveData(viewModelScope.coroutineContext)

    val weatherForecast: LiveData<Result<Int>>
        get() = _weatherForecast
}
