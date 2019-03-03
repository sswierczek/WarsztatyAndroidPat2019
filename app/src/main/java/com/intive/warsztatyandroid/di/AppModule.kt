package com.intive.warsztatyandroid.di

import com.intive.warsztatyandroid.common.config.ApiConfig
import com.intive.warsztatyandroid.home.model.api.retrofit.HomeApiClient
import com.intive.warsztatyandroid.home.view.HomeAdapter
import com.intive.warsztatyandroid.home.viewmodel.HomeViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single { ApiConfig() }
    single { newRetrofit(apiConfig = get()) }
    single { HomeApiClient(retrofit = get()) }

    factory { HomeAdapter() }

    viewModel { HomeViewModel(client = get()) }
}

private fun newRetrofit(apiConfig: ApiConfig) = Retrofit.Builder()
    .baseUrl(apiConfig.baseUrl)
    .addConverterFactory(GsonConverterFactory.create())
    .build()