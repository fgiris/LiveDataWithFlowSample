package io.fatih.livedatawithflowsample.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import io.fatih.livedatawithflowsample.Application
import io.fatih.livedatawithflowsample.Constants

@Module
class AppModule {
    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(
                Constants.SHARED_PREFERENCES_NAME,
                Context.MODE_PRIVATE
        )
    }
}