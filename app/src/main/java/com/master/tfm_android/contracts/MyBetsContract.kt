package com.master.tfm_android.contracts

import com.master.tfm_android.BasePresenter
import com.master.tfm_android.BaseView
import com.master.tfm_android.models.BetModel

interface MyBetsContract {

    interface View : BaseView<Presenter> {
        fun updateMyBets(bets : ArrayList<BetModel>)
    }

    interface Presenter : BasePresenter {
        fun getMyBets()

    }
}