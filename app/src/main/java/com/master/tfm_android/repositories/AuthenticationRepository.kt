package com.master.tfm_android.repositories

import com.master.tfm_android.models.Authenticated
import com.master.tfm_android.models.LoginModel
import com.master.tfm_android.models.RegisterModel
import rx.Observable

/**
 * Created by hector on 6/7/17.
 */
interface AuthenticationRepository {
    fun login(login : LoginModel) : Observable<Authenticated>
    fun register(register : RegisterModel) : Observable<Authenticated>
}