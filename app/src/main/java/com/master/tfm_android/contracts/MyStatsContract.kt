package com.master.tfm_android.contracts

import com.master.tfm_android.BasePresenter
import com.master.tfm_android.BaseView
import com.master.tfm_android.models.StatsModel

interface MyStatsContract {

    interface View : BaseView<Presenter> {
        fun updateStats(stats : StatsModel)
    }

    interface Presenter : BasePresenter {
        fun getMyStats()

    }
}
