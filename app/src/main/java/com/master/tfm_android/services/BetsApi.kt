package com.master.tfm_android.services

import com.master.tfm_android.models.Authenticated
import com.master.tfm_android.models.LoginModel
import com.master.tfm_android.models.RegisterModel
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable

/**
 * Created by hector on 5/7/17.
 */
class BetsApi {
    private val betService: BetService

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.0.111:8000/api/1.0/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()

        betService = retrofit.create(BetService::class.java)
    }

    fun login(login : LoginModel): Observable<Authenticated> {
        return betService.login(login)
    }

    fun register(register : RegisterModel): Observable<Authenticated> {
        return betService.register(register)
    }
}