package com.dagger.daggerhilt.di.modules

import androidx.annotation.Nullable
import androidx.lifecycle.ViewModel
import com.dagger.daggerhilt.di.keys.ViewModelKey
import com.dagger.daggerhilt.viewmodel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap


@InstallIn(SingletonComponent::class)
@Module
abstract class AppViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun bindMainViewModel(@Nullable loginViewModel: MainViewModel): ViewModel

}