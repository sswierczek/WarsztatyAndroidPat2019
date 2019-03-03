package com.intive.warsztatyandroid.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.intive.warsztatyandroid.home.model.api.retrofit.HomeApiClient
import com.intive.warsztatyandroid.home.model.data.HomeItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * FIXME This is simplified project -> Use cases should be injected instead of API
 */
class HomeViewModel(private val client: HomeApiClient) : ViewModel() {

    val items = MutableLiveData<List<HomeItem>>()
    val error = MutableLiveData<Boolean>().apply { value = false }
    val loading = MutableLiveData<Boolean>().apply { value = false }

    private var itemsCall: Call<List<HomeItem>>? = null

    fun viewCreated() {
        if (items.value.isNullOrEmpty()) fetchItems()
    }

    override fun onCleared() {
        super.onCleared()
        itemsCall?.cancel()
    }

    private fun fetchItems() {
        loading.value = true
        itemsCall = client.listHomeItems().apply { enqueue(itemsResponseCallback) }
    }

    private fun handleSuccess(homeItems: List<HomeItem>?) {
        loading.value = false
        items.value = homeItems
    }

    private fun handleError() {
        loading.value = false
        error.value = true
    }

    /**
     * FIXME This is simplified project ->  RxJava or Coroutines should be used instead of callbacks
     */
    private val itemsResponseCallback = object : Callback<List<HomeItem>> {
        override fun onFailure(call: Call<List<HomeItem>>, throwable: Throwable) {
            handleError()
        }

        override fun onResponse(call: Call<List<HomeItem>>, response: Response<List<HomeItem>>) {
            handleSuccess(response.body())
        }
    }
}