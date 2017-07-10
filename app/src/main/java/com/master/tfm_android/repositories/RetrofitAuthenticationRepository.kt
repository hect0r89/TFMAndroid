package com.master.tfm_android.repositories

import android.content.Context
import android.support.v7.app.AlertDialog
import com.master.tfm_android.models.Authenticated
import com.master.tfm_android.models.LoginModel
import com.master.tfm_android.models.RegisterModel
import com.master.tfm_android.services.BetsApi
import com.master.tfm_android.utils.ActivityUtils
import com.master.tfm_android.views.main.principal.MainActivity
import rx.Observable



class RetrofitAuthenticationRepository(val context : Context)  : AuthenticationRepository{

    override fun login(login: LoginModel): Observable<Authenticated> {
        checkInternetConnection()
        return BetsApi.betService.login(login)
    }

    override fun register(register: RegisterModel): Observable<Authenticated> {
        checkInternetConnection()
        return BetsApi.betService.register(register)
    }

    override fun checkCredentials(token : String) : Boolean {
        if(token.isNotEmpty()){
            BetsApi.addToken(token)
            return true
        }
        return false
    }

    fun checkInternetConnection(){
        if(!ActivityUtils.isInternetAvailable(context)){
            val builder = AlertDialog.Builder(context)
            builder.setCancelable(false)
            builder.setTitle("Error").setMessage("Internet connection is not avalaible").setPositiveButton("OK", { dialog, id -> (context as MainActivity).finish() }).show()
        }
    }

    companion object {
        lateinit var instance: RetrofitAuthenticationRepository

        fun initialize(context : Context){
            instance = RetrofitAuthenticationRepository(context)
        }
    }





}
