package com.master.tfm_android.presenters

import com.master.tfm_android.contracts.BetDetailContract
import com.master.tfm_android.models.BetModel
import com.master.tfm_android.repositories.RetrofitMainRepository
import org.jetbrains.annotations.NotNull
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class ManageBetPresenter() : BetDetailContract.Presenter {



    private var mBetDetailView: BetDetailContract.DetailView? = null
    private var mEditBetView: BetDetailContract.EditView? = null
    private val api = RetrofitMainRepository.instance

    constructor(@NotNull betDetailView: BetDetailContract.DetailView) : this() {
        mBetDetailView = checkNotNull(betDetailView)
        mBetDetailView?.setPresenter(this)

    }

    constructor(@NotNull editBetView: BetDetailContract.EditView) : this() {
        mEditBetView = checkNotNull(editBetView)
        mEditBetView?.setPresenter(this)
    }

    override fun start() {

    }

    override fun getBetDetail(id: Int) {
        api.getBetDetail(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { bet ->
                            mBetDetailView?.updateBetDetail(bet as BetModel)
                        },
                        { error ->
                            error.printStackTrace()
                        }
                )
    }

    override fun getSubscribedBetDetail(id: Int) {
        api.getSubscribedBetDetail(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { bet ->
                            mBetDetailView?.updateBetDetail(bet as BetModel)
                        },
                        { error ->
                            error.printStackTrace()
                        }
                )
    }

    override fun editBet(id : Int, bet: BetModel) {
        api.editBet(id, bet)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { bet ->
                            mEditBetView?.closeEdit(bet)
                        },
                        { error ->
                            error.printStackTrace()
                        }
                )
    }

    override fun deleteBet(id: Int) {
        api.deleteBet(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            mBetDetailView?.closeFragment()
                        },
                        { error ->
                            error.printStackTrace()
                        }
                )
    }


}
