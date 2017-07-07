package com.master.tfm_android.repositories

import com.master.tfm_android.models.BetModel
import com.master.tfm_android.models.StatsModel
import com.master.tfm_android.services.BetsApi
import rx.Observable

class RetrofitMainRepository  : MainRepository{


    val api = BetsApi.betService

    override fun getSubscribedBets(): Observable<List<BetModel>> {
        return api.getSubscribedBets()
    }

    override fun getMyBets(): Observable<List<BetModel>> {
        return api.getMyBets()
    }

    override fun getMyStats(): Observable<StatsModel> {
        return api.getMyStats()
    }

    companion object {
        lateinit var instance: RetrofitMainRepository

        fun initialize(){
            instance = RetrofitMainRepository()
        }
    }



}