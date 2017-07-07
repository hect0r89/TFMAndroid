package com.master.tfm_android.contracts

import com.master.tfm_android.BasePresenter
import com.master.tfm_android.BaseView
import com.master.tfm_android.models.BetModel
import retrofit2.HttpException


interface CreateBetContract {
    interface View : BaseView<Presenter> {
        fun showError(error: HttpException)

    }

    interface Presenter : BasePresenter {
        fun createBet(bet: BetModel)

    }
}