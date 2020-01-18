package io.fatih.livedatawithflowsample.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.fatih.livedatawithflowsample.MainActivity

@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}