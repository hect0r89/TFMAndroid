package com.master.tfm_android.presenters


import com.master.tfm_android.contracts.AuthenticationContract
import com.master.tfm_android.models.LoginModel
import com.master.tfm_android.models.RegisterModel
import com.master.tfm_android.repositories.RetrofitAuthenticationRepository
import com.master.tfm_android.repositories.SharedPreferenceTokenStorage
import org.jetbrains.annotations.NotNull
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class AuthenticationPresenter(private val api: RetrofitAuthenticationRepository) : AuthenticationContract.Presenter {

    private var mLoginView: AuthenticationContract.View? = null
    private var mRegisterView: AuthenticationContract.View? = null
    private var mPreferences: SharedPreferenceTokenStorage? = null

    constructor(@NotNull loginView: AuthenticationContract.View, @NotNull registerView: AuthenticationContract.View, preferences: SharedPreferenceTokenStorage) : this(RetrofitAuthenticationRepository) {
        mLoginView = checkNotNull(loginView)
        mRegisterView = checkNotNull(registerView)
        mPreferences = checkNotNull(preferences)
        mLoginView?.let { it.setPresenter(this) }
        mRegisterView?.let { it.setPresenter(this) }

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
                            mLoginView?.let { it.showMainActivity() }
                        },
                        { error ->
                            mLoginView?.let {  it.showErrors("User or password incorrect") }
                        }
                )


    }

    override fun register(username: String, password : String, email : String) {
        val register = RegisterModel(username, password, email)
        api.register(register)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { authenticated ->
                            saveToken(authenticated.token)
                            mRegisterView?.let { it.showMainActivity() }
                        },
                        { error ->
                            mRegisterView?.let {  it.showErrors("User or password incorrect") }
                        }
                )
    }

    private fun saveToken(token: String) {
        mPreferences?.saveToken(token)

    }
}