package com.dagger.daggerhilt.screens

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.dagger.daggerhilt.BR
import com.dagger.daggerhilt.R
import com.dagger.daggerhilt.databinding.ActivityMainBinding
import com.dagger.daggerhilt.di.factory.bindViewModel
import com.dagger.daggerhilt.model.StatusCountResponse
import com.dagger.daggerhilt.screens.base.BaseActivity
import com.dagger.daggerhilt.screens.base.MainNavigator
import com.dagger.daggerhilt.viewmodel.MainViewModel
import com.dagger.daggerhilt.viewmodel.base.BaseViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainNavigator>(), MainNavigator {


    private var mainBinding: ActivityMainBinding? = null

    lateinit var mainViewModel: MainViewModel

    private val TAG = "MainActivity"


    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getViewModel(): BaseViewModel<MainNavigator> {
        mainViewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        return mainViewModel
    }


    override fun getViewBindingVariable(): Int {
        return BR.mainViewModel
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = getViewDataBinding()
        mainViewModel.navigator = this

        mainViewModel.loadStatusCount("8888", "8888").observe(this) { response ->
            if (response != null) {
                val statusRes = response.data as StatusCountResponse
                Log.d("TAG", "StsResponses: $statusRes")
                Toast.makeText(this,statusRes.toString(),Toast.LENGTH_SHORT).show()
                Log.e(TAG,"NewResponse..."+"TestingValue")
            }
        }

    }



    private fun unInstallApps(){
        val intent = Intent(Intent.ACTION_DELETE)
        Log.v("application.packageName","application.packageName "+ application.packageName);
        intent.data = Uri.parse("package:"+"com.payment.demoapplication")
        startActivityForResult(intent, 101)
    }


    @Deprecated("Deprecated in Java", ReplaceWith(
            "super.onActivityResult(requestCode, resultCode, data)",
            "com.dagger.daggerhilt.screens.base.BaseActivity"))
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {

            when(requestCode){

                101-> {
                    Log.d("TAG", "onActivityResult: "+"RESULT_OK")
                }
            }

        } else if (requestCode == Activity.RESULT_CANCELED){
            Log.d("TAG", "onActivityResult: "+"RESULT_CANCELED")
        }
    }
}