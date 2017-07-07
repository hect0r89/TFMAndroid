package com.master.tfm_android.services
import com.master.tfm_android.models.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
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
    fun createBet(@Body bet : BetModel) : Observable<BetModel>
}
