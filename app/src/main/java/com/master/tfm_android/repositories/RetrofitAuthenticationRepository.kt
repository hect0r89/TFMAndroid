package com.master.tfm_android.repositories

import com.master.tfm_android.models.Authenticated
import com.master.tfm_android.models.LoginModel
import com.master.tfm_android.models.RegisterModel
import com.master.tfm_android.services.BetsApi
import rx.Observable

/**
 * Created by hector on 6/7/17.
 */

object RetrofitAuthenticationRepository  : AuthenticationRepository{

    val api = BetsApi

    override fun login(login: LoginModel): Observable<Authenticated> {
        return api.betService.login(login)
    }

    override fun register(register: RegisterModel): Observable<Authenticated> {
        return api.betService.register(register)
    }




}
