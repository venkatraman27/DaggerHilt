package com.dagger.daggerhilt.screens.base


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dagger.daggerhilt.R
import com.dagger.daggerhilt.databinding.BaseLayoutBinding
import com.dagger.daggerhilt.viewmodel.base.BaseViewModel
import javax.inject.Inject

abstract class BaseFragment<VB : ViewDataBinding, V : Any> : Fragment()  {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        baseBinding = DataBindingUtil.inflate(inflater, R.layout.base_layout, container, false)
        return baseBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDataBinding()
        observeLoadingStatus()
    }

    private fun setDataBinding() {
        viewBinding = DataBindingUtil.inflate(layoutInflater, getLayoutId(), baseBinding!!.frameContent, true)
        this.viewModel = getViewModel()
        viewBinding!!.setVariable(getViewBindingVariable(), viewModel)
        viewBinding!!.executePendingBindings()
    }

    fun showToast( input: String?) {
        Toast.makeText(context, input, Toast.LENGTH_SHORT).show()
    }

    fun showLoading() {
        baseBinding!!.includeProgress.progressBar.visibility = View.VISIBLE
    }

    fun hideLoading() {
        baseBinding!!.includeProgress.progressBar.visibility = View.INVISIBLE
    }

    protected fun openActivity(cls: Class<*>, shouldCloseActivity: Boolean) {
        startActivity(Intent(requireActivity(), cls))
        if (shouldCloseActivity) requireActivity().finish()
    }

    fun observeLoadingStatus() {
        viewModel!!.loadingStatus.observe(viewLifecycleOwner, Observer { isLoading ->
            if (!isLoading!!) {
                hideLoading()
            } else {
                showLoading()
            }
        })
    }



}