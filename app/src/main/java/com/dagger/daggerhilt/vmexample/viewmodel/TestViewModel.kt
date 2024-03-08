package com.dagger.daggerhilt.vmexample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dagger.daggerhilt.vmexample.data.models.PokeListResponse
import com.dagger.daggerhilt.vmexample.data.repository.TestRepository
import com.dagger.daggerhilt.vmexample.utils.Status
import com.dagger.daggerhilt.vmexample.utils.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class TestViewModel @Inject constructor(private val pokeRepository: TestRepository) : ViewModel() {


    val testState = MutableStateFlow(ViewState(Status.LOADING, PokeListResponse(), ""))


    init {
        // fetchDataByOffset()
    }

    fun fetchDataByOffset(driverId: String, vehicleID: String) {

        testState.value = ViewState.loading()

        viewModelScope.launch {

            pokeRepository.fetchPokemonData(driverId, vehicleID)
                .catch {
                    testState.value = ViewState.error(it.message.toString())
                }
                .collect { pokemonsResponseViewState ->
                    testState.value = ViewState.success(pokemonsResponseViewState.data)
                }
        }
    }


}