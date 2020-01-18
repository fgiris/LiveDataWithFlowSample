package io.fatih.livedatawithflowsample.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import dagger.android.support.DaggerFragment
import io.fatih.livedatawithflowsample.MainViewModel
import io.fatih.livedatawithflowsample.R
import io.fatih.livedatawithflowsample.data.theme.Theme
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject

class MainFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(
            this,
            viewModelFactory
        ).get(MainViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnWeatherForecast.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_weatherForecastOneShotFragment)
        }

        btnWeatherForecastRealTimeLiveData.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_weatherForecastDataStreamFragment)
        }

        btnWeatherForecastRealTimeFlow.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_weatherForecastDataStreamFlowFragment)
        }

        btnDarkMode.setOnClickListener {
            viewModel.setTheme(Theme.DARK)
        }
    }
}