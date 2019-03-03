package com.intive.warsztatyandroid.home.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.intive.warsztatyandroid.createEmptyHomeItem
import com.intive.warsztatyandroid.home.model.api.retrofit.HomeApiClient
import com.intive.warsztatyandroid.home.model.data.HomeItem
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.amshove.kluent.`should equal`
import org.junit.Rule
import org.junit.Test
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModelTest {

    @Rule
    @JvmField
    var rule = InstantTaskExecutorRule()

    val validList = listOf(
        createEmptyHomeItem(id = 0),
        createEmptyHomeItem(id = 1),
        createEmptyHomeItem(id = 2)
    )
    val fetchCall = mock<Call<List<HomeItem>>>()
    val apiClient = mock<HomeApiClient> {
        on { fetchHomeItems() } doReturn fetchCall
    }
    val tested by lazy { HomeViewModel(apiClient) }

    @Test
    fun `When view created should start fetching data`() {
        tested.viewCreated()

        fetchCall.captureListener().onResponse(mock(), Response.success(validList))

        tested.items.value `should equal` validList
    }

    @Test
    fun `When fetching data should show progress`() {
        tested.viewCreated()

        tested.loading.value `should equal` true
    }

    @Test
    fun `When fetching data should hide progress`() {
        tested.viewCreated()

        fetchCall.captureListener().onResponse(mock(), Response.success(validList))

        tested.loading.value `should equal` false
    }

    @Test
    fun `When data fetching error should display error`() {
        tested.viewCreated()

        fetchCall.captureListener().onFailure(mock(), Exception("test error fetching data"))

        tested.error.value `should equal` true
    }

    @Test
    fun `When data fetching error should hide progress`() {
        tested.viewCreated()

        fetchCall.captureListener().onFailure(mock(), Exception("test error fetching data"))

        tested.loading.value `should equal` false
    }

    private fun Call<List<HomeItem>>.captureListener() =
        argumentCaptor<Callback<List<HomeItem>>> {
            verify(this@captureListener).enqueue(capture())
        }.firstValue
}