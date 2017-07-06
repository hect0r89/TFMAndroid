package com.master.tfm_android.services
import com.master.tfm_android.models.Authenticated
import com.master.tfm_android.models.BetModel
import com.master.tfm_android.models.LoginModel
import com.master.tfm_android.models.RegisterModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import rx.Observable


/**
 * Created by hector on 5/7/17.
 */


interface BetService {

    @POST("authentication/login/")
    fun login(@Body login: LoginModel): Observable<Authenticated>

    @POST("authentication/register/")
    fun register(@Body register: RegisterModel): Observable<Authenticated>

    @GET("subscribed_bets/")
    fun getSubscribedBets(): Observable<List<BetModel>>
}
