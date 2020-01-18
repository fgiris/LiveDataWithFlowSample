package io.fatih.livedatawithflowsample

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.fatih.livedatawithflowsample.di.DaggerAppComponent

class Application : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }
}