package com.dagger.daggerhilt.di.modules

import android.content.Context
import com.dagger.daggerhilt.data.preferences.AppPreference
import com.dagger.daggerhilt.data.remote.ApiService
import com.dagger.daggerhilt.data.repository.MainRepository
import com.dagger.daggerhilt.util.Constants.BASE_URL
import com.dagger.daggerhilt.util.application.AppController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {


    @Provides
    @Singleton
    internal fun providesContext(): Context {
        return AppController.getInstance()!!.applicationContext
    }

    @Provides
    @Singleton
    internal fun providesAppPreference(context: Context): AppPreference {
        return AppPreference(context)
    }


}