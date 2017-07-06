package com.master.tfm_android.repositories

import com.master.tfm_android.models.BetModel
import com.master.tfm_android.services.BetsApi
import rx.Observable

object RetrofitMainRepository  : MainRepository{

    val api = BetsApi

    override fun getSubscribedBets(): Observable<List<BetModel>> {
        return api.betService.getSubscribedBets()
    }

}