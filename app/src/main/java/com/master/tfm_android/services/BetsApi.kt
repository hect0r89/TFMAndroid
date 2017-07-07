package com.master.tfm_android.services

import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import android.text.TextUtils
import com.master.tfm_android.interceptors.AuthenticationInterceptor
import com.master.tfm_android.repositories.RetrofitAuthenticationRepository
import okhttp3.OkHttpClient

class BetsApi {

    companion object {
        var httpClient: OkHttpClient.Builder? = null
        var builder: Retrofit.Builder? = null
        var retrofit: Retrofit? = null

        lateinit var betService: BetService

        fun initialize() {
            httpClient = OkHttpClient.Builder()
            builder = Retrofit.Builder()
                    .baseUrl("http://192.168.0.111:8000/api/1.0/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            retrofit = builder?.build()

            betService = retrofit?.create(BetService::class.java) as BetService
        }

        fun addToken(token: String) {
            val interceptor = AuthenticationInterceptor(token)
            httpClient?.interceptors()?.contains(interceptor)?.let {
                if (!it) {
                    (httpClient as OkHttpClient.Builder).addInterceptor(interceptor)
                    builder?.client((httpClient as OkHttpClient.Builder).build())
                    retrofit = builder?.build()
                    betService = retrofit?.create(BetService::class.java) as BetService
                }
            }
        }


    }

}