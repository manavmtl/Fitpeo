package com.example.fitpeo.di

import android.content.Context
import com.example.fitpeo.data.api.ApiInterface
import com.example.fitpeo.data.api.netwrokUtils.ConnectivityInterceptor
import com.example.fitpeo.presentation.utils.ApiConstants
import com.squareup.picasso.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RetrofitModule {
    @Provides
    @Singleton
    fun provideAPIInterface(context: Context): ApiInterface {
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient
            .readTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(ConnectivityInterceptor(context))
        if (BuildConfig.DEBUG) {
            okHttpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }

        return Retrofit.Builder().baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient.build()).build()
            .create(ApiInterface::class.java)
    }
}