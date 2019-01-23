package com.intive.warsztatyandroid.di

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.intive.warsztatyandroid.home.model.api.retrofit.HomeApiClient
import com.intive.warsztatyandroid.home.view.HomeAdapter
import com.intive.warsztatyandroid.home.viewmodel.HomeViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://jsonplaceholder.typicode.com/"

/**
 * FIXME This is simplified project -> DI framework like Dagger or Koin should be used
 */
object InstancesFactory {

    fun homeAdapter() = HomeAdapter()

    fun homeViewModel(activity: FragmentActivity) =
        ViewModelProviders.of(activity, homeViewModelFactory()).get(HomeViewModel::class.java)

    private fun homeViewModelFactory() = HomeViewModel.Factory(homeApiClient())

    private fun homeApiClient() = HomeApiClient(retrofit())

    private fun retrofit() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}