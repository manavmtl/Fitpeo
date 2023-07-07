package com.example.fitpeo.data.api.netwrokUtils

import android.content.Context
import android.content.Intent
import com.example.fitpeo.R
import okhttp3.Interceptor
import okhttp3.Response

class ConnectivityInterceptor(private val context: Context) : Interceptor {
    private val TAG = ConnectivityInterceptor::class.java.simpleName
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!NetworkUtils.isInternetAvailable(context)) {
           throw NoConnectivityException(context.getString(R.string.no_internet_connection))
        }
        return chain.proceed(chain.request())
    }
}
