package com.dagger.daggerhilt.viewmodel.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dagger.daggerhilt.data.repository.MainRepository
import java.lang.ref.WeakReference
import javax.inject.Inject

abstract class BaseViewModel<N> : ViewModel() {

    private lateinit var mNavigator: WeakReference<N>

//    @Inject
//    lateinit var repo: MainRepository


    var navigator: N
        get() = mNavigator.get()!!
        set(navigator) {
            this.mNavigator = WeakReference(navigator)
        }

    val loadingStatus = MutableLiveData<Boolean>()
}