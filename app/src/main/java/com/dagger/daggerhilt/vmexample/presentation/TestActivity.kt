package com.dagger.daggerhilt.vmexample.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.dagger.daggerhilt.R


import com.dagger.daggerhilt.databinding.ActivityTestBinding
import com.dagger.daggerhilt.model.StatusCountResponse
import com.dagger.daggerhilt.vmexample.utils.Status
import com.dagger.daggerhilt.vmexample.viewmodel.TestViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class TestActivity : AppCompatActivity() {

    lateinit var testBinding: ActivityTestBinding

    val listFragmentViewModel: TestViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        listFragmentViewModel.fetchDataByOffset("206498","22817")

        observeData()
    }


    private fun observeData() {

        lifecycleScope.launch {

            listFragmentViewModel.testState.collect { response ->

                when (response.status) {

                    Status.LOADING -> {
                        Log.i("PokeListFragment", "Loading...")
                    }

                    Status.SUCCESS -> {
                        val newData = response.data
                        newData?.let {
                            Log.d("TAG", "observeData: $newData")
                        }
                    }

                    Status.ERROR -> {
                        Log.i("PokeListFragment", "Loading...")
                    }
                }
            }
        }
    }
}