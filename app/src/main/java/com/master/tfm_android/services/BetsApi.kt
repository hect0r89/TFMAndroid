package com.master.tfm_android.services
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by hector on 5/7/17.
 */
object BetsApi {
    val betService: BetService

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.0.111:8000/api/1.0/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()

        betService = retrofit.create(BetService::class.java)
    }

}