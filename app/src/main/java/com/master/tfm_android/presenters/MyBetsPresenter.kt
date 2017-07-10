package com.master.tfm_android.presenters

import com.master.tfm_android.contracts.MyBetsContract
import com.master.tfm_android.models.BetModel
import com.master.tfm_android.repositories.RetrofitMainRepository
import com.master.tfm_android.utils.ActivityUtils
import org.jetbrains.annotations.NotNull
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class MyBetsPresenter() : MyBetsContract.Presenter {
    private var mMyBetsView: MyBetsContract.View? = null
    private val api = RetrofitMainRepository.instance

    constructor(@NotNull loginView: MyBetsContract.View) : this() {
        mMyBetsView = checkNotNull(loginView)
        mMyBetsView?.setPresenter(this)
    }

    override fun start() {
        getMyBets()
    }

    override fun getMyBets(){
            api.getMyBets()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { bets ->
                                mMyBetsView?.updateMyBets(bets as ArrayList<BetModel>)
                            },
                            { error ->
                                error.printStackTrace()
                            }
                    )


    }
}