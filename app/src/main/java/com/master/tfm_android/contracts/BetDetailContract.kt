package com.master.tfm_android.contracts

import com.master.tfm_android.BasePresenter
import com.master.tfm_android.BaseView
import com.master.tfm_android.models.BetModel
import retrofit2.HttpException

interface BetDetailContract {
    interface DetailView : BaseView<Presenter> {
        fun updateBetDetail(bet: BetModel)
        fun closeFragment()

    }

    interface EditView : BaseView<Presenter> {
        fun closeEdit(bet : BetModel)
        fun showError(error: HttpException)

    }

    interface Presenter : BasePresenter {
        fun getBetDetail(id: Int)
        fun getSubscribedBetDetail(id: Int)
        fun editBet(id: Int, bet: BetModel)
        fun deleteBet(id : Int)


    }
}