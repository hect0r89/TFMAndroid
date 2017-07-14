package com.master.tfm_android.repositories

import com.master.tfm_android.models.BetModel
import com.master.tfm_android.models.StatsModel
import com.master.tfm_android.models.UserModel
import rx.Observable

interface MainRepository {
    fun getSubscribedBets(): Observable<List<BetModel>>
    fun getMyBets(): Observable<List<BetModel>>
    fun getMyStats(): Observable<StatsModel>
    fun createBet(bet: BetModel): Observable<BetModel>
    fun getBetDetail(id: Int): Observable<BetModel>
    fun getSubscribedBetDetail(id: Int): Observable<BetModel>
    fun editBet(id: Int, bet: BetModel): Observable<BetModel>
    fun deleteBet(id: Int): Observable<Unit>
    fun getBets(user: Int): Observable<List<BetModel>>
    fun getUser(id: Int): Observable<UserModel>
}