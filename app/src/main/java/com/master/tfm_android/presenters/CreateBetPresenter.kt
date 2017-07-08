package com.master.tfm_android.presenters

import android.util.Log
import android.widget.Toast
import com.master.tfm_android.contracts.CreateBetContract
import com.master.tfm_android.models.BetModel
import com.master.tfm_android.repositories.RetrofitMainRepository
import org.jetbrains.annotations.NotNull
import retrofit2.HttpException
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class CreateBetPresenter() : CreateBetContract.Presenter {
    private var mCreateView: CreateBetContract.View? = null
    private val api = RetrofitMainRepository.instance

    constructor(@NotNull loginView: CreateBetContract.View) : this() {
        mCreateView = checkNotNull(loginView)
        mCreateView?.setPresenter(this)
    }

    override fun start() {

    }

    override fun createBet(bet : BetModel) {
        api.createBet(bet)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { bet ->
                            mCreateView?.closeFragment()
                        },
                        { error ->
                            catchHttpError(error)
                        }
                )

    }

    private fun catchHttpError(error: Throwable?) {
        if( error is HttpException){
            mCreateView?.showError(error)
        }
    }
}