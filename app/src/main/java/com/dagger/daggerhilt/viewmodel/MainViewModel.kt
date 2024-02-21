package com.dagger.daggerhilt.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dagger.daggerhilt.data.common.AppResponse
import com.dagger.daggerhilt.data.repository.MainRepository
import com.dagger.daggerhilt.model.StatusCountResponse
import com.dagger.daggerhilt.screens.base.MainNavigator
import com.dagger.daggerhilt.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject



class MainViewModel @Inject constructor(private val repo: MainRepository) : BaseViewModel<MainNavigator>() {

//    private val _res = MutableLiveData<Resource<StatusCountResponse>>()
//    val res: LiveData<Resource<StatusCountResponse>>
//        get() = _res

//    fun getVerifyCode(vehicleId:String,driverId:String) = viewModelScope.launch {
//        _res.postValue(Resource.Loading(null))
//        }


     fun loadStatusCount(vehicleID: String, driverID: String): MutableLiveData<AppResponse<Any>> {
        val responseData =  MutableLiveData <AppResponse<Any>>()

         viewModelScope.launch {

             repo.getProviderLogin(vehicleID, driverID)
                 .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .doOnSubscribe { loadingStatus.value = true }
                 .doOnTerminate { loadingStatus.value = false }
                 .subscribe({ response ->
                     if (response != null) {
                         responseData.value = AppResponse.success(response)
                     }
                 }, { throwable ->
                     responseData.value = AppResponse.error(throwable)
                 })
         }
        return responseData
    }


//    fun loadStatusCount(vehicleID: String, driverID: String): MutableLiveData<StatusCountResponse> {
//        val responseData =  MutableLiveData <StatusCountResponse>()
//        viewModelScope.launch {
//            val count = repo.getProviderLogin(vehicleID, driverID)
//            responseData.value = count
//        }
//        return responseData
//    }


}