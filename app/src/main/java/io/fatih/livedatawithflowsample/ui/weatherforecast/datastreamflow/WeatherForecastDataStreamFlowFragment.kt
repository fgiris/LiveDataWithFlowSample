package io.fatih.livedatawithflowsample.ui.weatherforecast.datastreamflow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import dagger.android.support.DaggerFragment
import io.fatih.livedatawithflowsample.R
import io.fatih.livedatawithflowsample.shared.Result
import kotlinx.android.synthetic.main.fragment_weather_forecast_one_shot.*
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class WeatherForecastDataStreamFlowFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: WeatherForecastDataStreamFlowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_weather_forecast_data_stream, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(
            this,
            viewModelFactory
        ).get(WeatherForecastDataStreamFlowViewModel::class.java)

        lifecycleScope.launchWhenStarted {
            // Since collect is a suspend function it needs to be called
            // from a coroutine scope
            viewModel.weatherForecast.collect {
                when (it) {
                    Result.Loading -> {
                        Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
                    }
                    is Result.Success -> {
                        tvDegree.text = it.data.toString()
                    }
                    Result.Error -> {
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
