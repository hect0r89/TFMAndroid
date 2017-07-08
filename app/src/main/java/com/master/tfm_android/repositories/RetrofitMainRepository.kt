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

    override fun createBet(bet: BetModel): Observable<BetModel> {
        return api.createBet(bet)
    }

    override fun getBetDetail(id: Int): Observable<BetModel> {
        return api.getBetDetail(id)
    }

    override fun getSubscribedBetDetail(id: Int): Observable<BetModel> {
        return api.getSubscribedBetDetail(id)
    }

    override fun editBet(id : Int, bet: BetModel): Observable<BetModel> {
        return api.editBet(id, bet)
    }

    override fun deleteBet(id: Int): Observable<Unit> {
        return api.deleteBet(id)
    }

    companion object {
        lateinit var instance: RetrofitMainRepository

        fun initialize(){
            instance = RetrofitMainRepository()
        }
    }



}