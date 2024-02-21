package com.dagger.daggerhilt.screens.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.dagger.daggerhilt.R
import com.dagger.daggerhilt.data.preferences.AppPreference
import com.dagger.daggerhilt.databinding.BaseLayoutBinding
import com.dagger.daggerhilt.viewmodel.base.BaseViewModel
import javax.inject.Inject

abstract class BaseActivity<VB : ViewDataBinding, V : Any> : AppCompatActivity() {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appPreference: AppPreference


    private var dialog: AlertDialog? = null
    private var viewBinding: VB? = null
    private var viewModel: BaseViewModel<V>? = null
    private var baseBinding: BaseLayoutBinding? = null


    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getViewModel(): BaseViewModel<V>

    abstract fun getViewBindingVariable(): Int


    fun getViewDataBinding(): VB {
        return viewBinding!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseBinding = DataBindingUtil.setContentView(this, R.layout.base_layout)
        setDataBinding()
        observeLoadingStatus()
    }

    private fun setDataBinding() {
        viewBinding =
            DataBindingUtil.inflate(layoutInflater, getLayoutId(), baseBinding!!.frameContent, true)
        viewModel = getViewModel()
        viewBinding!!.setVariable(getViewBindingVariable(), viewModel)
        viewBinding!!.executePendingBindings()
    }


    private fun observeLoadingStatus() {
        viewModel!!.loadingStatus.observe(this) { isLoading ->
            if (!isLoading!!) {
                hideLoading()
            } else {
                showLoading()
            }
        }
    }

    fun showToast(input: String) {
        Toast.makeText(this, input, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading() {
       baseBinding!!.includeProgress.progressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
       baseBinding!!.includeProgress.progressBar.visibility = View.INVISIBLE
    }

    fun setHeaderTitle(title: String) {
        if (supportActionBar != null) supportActionBar!!.title = title
    }

    fun showBackButton(status: Boolean) {
        if (supportActionBar != null) supportActionBar!!.setDisplayHomeAsUpEnabled(status)
        if (supportActionBar != null)
            supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_white_back)
    }
}