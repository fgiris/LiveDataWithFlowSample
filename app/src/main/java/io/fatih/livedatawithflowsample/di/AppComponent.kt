package io.fatih.livedatawithflowsample.di

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import io.fatih.livedatawithflowsample.Application
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        FragmentBuilderModule::class,
        ActivityBuilderModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent : AndroidInjector<Application> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<Application>()
}