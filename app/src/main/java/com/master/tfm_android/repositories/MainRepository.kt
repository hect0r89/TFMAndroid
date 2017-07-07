package com.master.tfm_android.repositories

import com.master.tfm_android.models.BetModel
import com.master.tfm_android.models.StatsModel
import rx.Observable

interface MainRepository {
    fun getSubscribedBets() : Observable<List<BetModel>>
    fun getMyBets() : Observable<List<BetModel>>
    fun getMyStats() : Observable<StatsModel>
    fun createBet(bet : BetModel) : Observable<BetModel>
}