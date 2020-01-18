package io.fatih.livedatawithflowsample.ui.weatherforecast.oneshot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerFragment
import io.fatih.livedatawithflowsample.R
import io.fatih.livedatawithflowsample.shared.Result
import kotlinx.android.synthetic.main.fragment_weather_forecast_one_shot.*
import javax.inject.Inject

class WeatherForecastOneShotFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: WeatherForecastOneShotViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_weather_forecast_one_shot, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(
            this,
            viewModelFactory
        ).get(WeatherForecastOneShotViewModel::class.java)

        // It is always a good practice to use viewLifecycleOwner since
        // lifecycle of fragment might be longer than its view in some cases
        viewModel.weatherForecast.observe(viewLifecycleOwner, Observer {
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
        })
    }
}
