package com.intive.warsztatyandroid.home.model.api.retrofit

import com.intive.warsztatyandroid.home.model.data.HomeItem
import retrofit2.Call
import retrofit2.Retrofit

class HomeApiClient(retrofit: Retrofit) {

    private val service = retrofit.create(HomeApiService::class.java)

    fun listHomeItems(): Call<List<HomeItem>> =
        service.listHomeItems()
}