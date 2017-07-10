package com.master.tfm_android.presenters

import android.widget.Toast
import com.master.tfm_android.contracts.MyStatsContract
import com.master.tfm_android.models.StatsModel
import com.master.tfm_android.repositories.RetrofitMainRepository
import com.master.tfm_android.utils.ActivityUtils
import org.jetbrains.annotations.NotNull
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MyStatsPresenter() : MyStatsContract.Presenter {
    private var mMyStatsView: MyStatsContract.View? = null
    private val api = RetrofitMainRepository.instance

    constructor(@NotNull loginView: MyStatsContract.View) : this() {
        mMyStatsView = checkNotNull(loginView)
        mMyStatsView?.setPresenter(this)
    }

    override fun start() {
        getMyStats()
    }

    override fun getMyStats() {
            api.getMyStats()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { stats ->
                                mMyStatsView?.updateStats(stats as StatsModel)
                            },
                            { error ->
                                error.printStackTrace()
                            }
                    )
    }
}
