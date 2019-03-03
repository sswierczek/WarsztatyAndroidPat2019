package com.intive.warsztatyandroid.home.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.intive.warsztatyandroid.R
import com.intive.warsztatyandroid.home.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_home.homeError
import kotlinx.android.synthetic.main.activity_home.homeLoading
import kotlinx.android.synthetic.main.activity_home.homeRecyclerView
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val homeViewModel: HomeViewModel by viewModel()
    private val homeAdapter: HomeAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setupRecyclerView()
        observeViewModel()
        homeViewModel.viewCreated()
    }

    /**
     * FIXME This is simplified project -> Databinding should be used instead of directly observing of LiveData
     */
    private fun observeViewModel() {
        homeViewModel.items.observe(this, Observer { newItems ->
            homeAdapter.updateItems(newItems)
        })

        homeViewModel.loading.observe(this, Observer { showLoading ->
            homeLoading.visibility = if (showLoading) View.VISIBLE else View.GONE
        })

        homeViewModel.error.observe(this, Observer { showError ->
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
