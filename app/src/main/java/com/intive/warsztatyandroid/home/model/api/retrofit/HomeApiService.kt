package com.intive.warsztatyandroid.home.model.api.retrofit

import com.intive.warsztatyandroid.home.model.data.HomeItem
import retrofit2.Call
import retrofit2.http.GET

interface HomeApiService {

    @GET("photos")
    fun listHomeItems(): Call<List<HomeItem>>
}