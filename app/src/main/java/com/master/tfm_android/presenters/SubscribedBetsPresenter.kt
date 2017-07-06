package com.master.tfm_android.presenters

import android.util.Log
import com.master.tfm_android.contracts.SubscribedBetsContract
import com.master.tfm_android.models.BetModel
import com.master.tfm_android.repositories.RetrofitMainRepository
import org.jetbrains.annotations.NotNull
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class SubscribedBetsPresenter(private val api: RetrofitMainRepository) : SubscribedBetsContract.Presenter {


    private var mSubscribedBetsView: SubscribedBetsContract.View? = null

    constructor(@NotNull loginView: SubscribedBetsContract.View) : this(RetrofitMainRepository) {
        mSubscribedBetsView = checkNotNull(loginView)
        mSubscribedBetsView?.let { it.setPresenter(this) }
    }

    override fun start() {
        getSubscribedBets()
    }

    override fun getSubscribedBets(){
        api.getSubscribedBets()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { bets ->
                            mSubscribedBetsView?.let { it.updateSubscribedBets(bets as ArrayList<BetModel>) }
                        },
                        { error ->
                            error.printStackTrace()
                        }
                )

    }


}


