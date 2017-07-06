package com.master.tfm_android.repositories

import com.master.tfm_android.models.Authenticated
import com.master.tfm_android.models.LoginModel
import com.master.tfm_android.models.RegisterModel
import com.master.tfm_android.services.BetsApi
import rx.Observable



class RetrofitAuthenticationRepository  : AuthenticationRepository{

    override fun login(login: LoginModel): Observable<Authenticated> {
        return BetsApi.betService.login(login)
    }

    override fun register(register: RegisterModel): Observable<Authenticated> {
        return BetsApi.betService.register(register)
    }

    override fun checkCredentials(token : String) : Boolean {
        if(token.isNotEmpty()){
            BetsApi.addToken(token)
            return true
        }
        return false
    }

    companion object {
        lateinit var instance: RetrofitAuthenticationRepository

        fun initialize(){
            instance = RetrofitAuthenticationRepository()
        }
    }





}
