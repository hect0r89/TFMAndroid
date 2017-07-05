package com.master.tfm_android.presenters


import android.content.SharedPreferences
import com.master.tfm_android.contracts.RegisterContract
import com.master.tfm_android.models.RegisterModel
import com.master.tfm_android.services.BetsApi
import com.master.tfm_android.utils.ActivityUtils.TOKEN_NAME
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


/**
 * Created by hector on 4/7/17.
 */
class RegisterPresenter(private val api: BetsApi = BetsApi()) : RegisterContract.Presenter {

    private var mRegisterView: RegisterContract.View? = null
    private var mPreferences: SharedPreferences? = null

    constructor(registerView: RegisterContract.View, preferences : SharedPreferences) : this() {
        mRegisterView = checkNotNull(registerView)
        mPreferences = checkNotNull(preferences)
        mRegisterView!!.setPresenter(this)
    }

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

    }

    override fun register(username: String, password : String, email : String) {
        val register = RegisterModel(username, password, email)
        api.register(register)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { authenticated ->
                            saveToken(authenticated.token)
                            mRegisterView!!.showMainActivity()
                        },
                        {error ->
                            mRegisterView!!.showErrors("User or password incorrect")
                        }
                )


    }

    private fun saveToken(token: String) {
        mPreferences!!.edit().putString(TOKEN_NAME, token)?.apply()
    }


}