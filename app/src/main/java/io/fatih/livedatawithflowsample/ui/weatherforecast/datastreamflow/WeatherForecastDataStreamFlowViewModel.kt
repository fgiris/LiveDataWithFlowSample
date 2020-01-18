package io.fatih.livedatawithflowsample.ui.weatherforecast.datastreamflow

import androidx.lifecycle.ViewModel
import io.fatih.livedatawithflowsample.data.weatherforecast.WeatherForecastRepository
import io.fatih.livedatawithflowsample.shared.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherForecastDataStreamFlowViewModel @Inject constructor(
    weatherForecastRepository: WeatherForecastRepository
) : ViewModel() {

    private val _weatherForecast = weatherForecastRepository
        .fetchWeatherForecastRealTime()

    val weatherForecast: Flow<Result<Int>>
        get() = _weatherForecast
}
