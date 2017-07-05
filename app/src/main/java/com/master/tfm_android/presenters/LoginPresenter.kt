package com.master.tfm_android.presenters


import android.content.SharedPreferences
import android.util.Log
import com.master.tfm_android.contracts.LoginContract
import com.master.tfm_android.models.LoginModel
import com.master.tfm_android.services.BetsApi
import com.master.tfm_android.utils.ActivityUtils.TOKEN_NAME
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


/**
 * Created by hector on 4/7/17.
 */
class LoginPresenter(private val api: BetsApi = BetsApi()) : LoginContract.Presenter {

    private var mLoginView: LoginContract.View? = null
    private var mPreferences: SharedPreferences? = null

    constructor(loginView: LoginContract.View, preferences: SharedPreferences) : this() {
        mLoginView = checkNotNull(loginView)
        mPreferences = checkNotNull(preferences)
        mLoginView!!.setPresenter(this)
    }

    override fun start() {
        TODO("not implemented")

    }

    override fun login(username: String, password: String) {
        val login = LoginModel(username, password)
        api.login(login)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { authenticated ->
                            saveToken(authenticated.token)
                            mLoginView!!.showMainActivity()
                        },
                        { e ->
                            e.printStackTrace()
                            mLoginView!!.showErrors("User or password incorrect")
                        }, {
                    Log.d("sad", "dsf");
                }
                )


    }

    private fun saveToken(token: String) {
        mPreferences!!.edit().putString(TOKEN_NAME, token)?.apply()
    }


}