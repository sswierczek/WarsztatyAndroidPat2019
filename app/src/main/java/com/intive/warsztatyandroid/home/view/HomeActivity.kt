package com.intive.warsztatyandroid.home.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.intive.warsztatyandroid.R
import com.intive.warsztatyandroid.di.InstancesFactory
import kotlinx.android.synthetic.main.activity_home.homeError
import kotlinx.android.synthetic.main.activity_home.homeLoading
import kotlinx.android.synthetic.main.activity_home.homeRecyclerView

class HomeActivity : AppCompatActivity() {

    private val viewModel by lazy {
        InstancesFactory.homeViewModel(this)
    }

    private val homeAdapter = InstancesFactory.homeAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setupRecyclerView()
        observeViewModel()
        viewModel.viewCreated()
    }

    /**
     * FIXME This is simplified project -> Databinding should be used instead of directly observing of LiveData
     */
    private fun observeViewModel() {
        viewModel.items.observe(this, Observer { newItems ->
            homeAdapter.updateItems(newItems)
        })

        viewModel.loading.observe(this, Observer { showLoading ->
            homeLoading.visibility = if (showLoading) View.VISIBLE else View.GONE
        })

        viewModel.error.observe(this, Observer { showError ->
            homeError.visibility = if (showError) View.VISIBLE else View.GONE
        })
    }

    private fun setupRecyclerView() {
        homeRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = homeAdapter
        }
    }
}
