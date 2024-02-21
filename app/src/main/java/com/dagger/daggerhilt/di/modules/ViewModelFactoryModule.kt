package com.dagger.daggerhilt.di.modules

import androidx.lifecycle.ViewModelProvider
import com.dagger.daggerhilt.di.factory.AppViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ViewModelFactoryModule {

    @Binds
    internal abstract fun bindViewModelFactory(factoryApp: AppViewModelFactory): ViewModelProvider.Factory

}