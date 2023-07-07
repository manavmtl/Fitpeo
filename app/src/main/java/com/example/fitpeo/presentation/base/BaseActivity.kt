package com.example.fitpeo.presentation.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.fitpeo.data.api.netwrokUtils.NetworkUtils
import com.example.fitpeo.data.api.netwrokUtils.NoConnectivityException
import com.example.fitpeo.presentation.utils.CommonUtils
import java.lang.Exception

abstract class BaseActivity<MyDataBinding : ViewDataBinding>:AppCompatActivity() {
    private lateinit var baseActivityBinding: MyDataBinding

    @LayoutRes
    abstract fun getLayoutId(): Int
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
    }

    private fun performDataBinding() {
        baseActivityBinding = DataBindingUtil.setContentView(this, getLayoutId())
    }
    open fun getViewDataBinding(): MyDataBinding {
        return baseActivityBinding
    }

    fun showToast(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }

    fun noInternet(exception: Exception){
        if (exception is NoConnectivityException) {
            CommonUtils.showNoInternetDialog(this)
        }
    }
}