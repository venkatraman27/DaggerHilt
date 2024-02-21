package com.dagger.daggerhilt.di.modules

import com.dagger.daggerhilt.data.remote.ApiService
import com.dagger.daggerhilt.data.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

//@InstallIn(SingletonComponent::class)
//@Module(includes = [NetworkModule::class])
//class ApiModule {
//
//
//    @Provides
//    @Singleton
//    fun bindAppApi(retrofit: Retrofit): ApiService {
//        return retrofit.create(ApiService::class.java)
//    }
//
//    @Singleton
//    @Provides
//    fun provideMainRepository(api: ApiService) = MainRepository(api = api)
//
//
//
//}