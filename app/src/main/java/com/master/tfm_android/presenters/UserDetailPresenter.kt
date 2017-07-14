package com.master.tfm_android.presenters

import android.util.Log
import com.master.tfm_android.contracts.SubscribedBetsContract
import com.master.tfm_android.contracts.UserDetailContract
import com.master.tfm_android.models.BetModel
import com.master.tfm_android.repositories.RetrofitMainRepository
import org.jetbrains.annotations.NotNull
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class UserDetailPresenter() : UserDetailContract.Presenter {



    private var mUserDetailView: UserDetailContract.View? = null
    private val api = RetrofitMainRepository.instance

    constructor(@NotNull loginView: UserDetailContract.View) : this() {
        mUserDetailView = checkNotNull(loginView)
        mUserDetailView?.let { it.setPresenter(this) }
    }

    override fun start() {

    }

    override fun getBets(user: Int) {
        api.getBets(user)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { bets ->
                           mUserDetailView?.updateBets(bets as ArrayList<BetModel>)
                        },
                        { error ->
                            error.printStackTrace()
                        }
                )
    }

    override fun getUser(id: Int) {
        api.getUser(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { user ->
                            mUserDetailView?.updateUserInfo(user)
                        },
                        { error ->
                            error.printStackTrace()

                        }
                )
    }

    override fun getStats() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
