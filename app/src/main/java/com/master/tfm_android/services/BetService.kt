package com.master.tfm_android.services

import com.master.tfm_android.models.*
import retrofit2.http.*
import rx.Observable

interface BetService {

    @POST("authentication/login/")
    fun login(@Body login: LoginModel): Observable<Authenticated>

    @POST("authentication/register/")
    fun register(@Body register: RegisterModel): Observable<Authenticated>

    @GET("subscribed_bets/")
    fun getSubscribedBets(): Observable<List<BetModel>>

    @GET("bets/")
    fun getMyBets(): Observable<List<BetModel>>

    @GET("users/my_stats/")
    fun getMyStats(): Observable<StatsModel>

    @POST("bets/")
    fun createBet(@Body bet: BetModel): Observable<BetModel>

    @GET("bets/{id}/")
    fun getBetDetail(@Path("id") id: Int): Observable<BetModel>

    @GET("subscribed_bets/{id}/")
    fun getSubscribedBetDetail(@Path("id") id: Int): Observable<BetModel>

    @PATCH("bets/{id}/")
    fun editBet(@Path("id") id: Int, @Body bet: BetModel): Observable<BetModel>

    @DELETE("bets/{id}/")
    fun deleteBet(@Path("id") id: Int): Observable<Unit>
}
