package com.master.tfm_android.contracts

import com.master.tfm_android.BasePresenter
import com.master.tfm_android.BaseView
import com.master.tfm_android.models.BetModel
import com.master.tfm_android.models.StatsModel
import com.master.tfm_android.models.UserModel

/**
 * Created by hector on 14/07/17.
 */
interface UserDetailContract {

    interface View : BaseView<Presenter> {
        fun updateBets(bets : ArrayList<BetModel>)
        fun updateStats(stats : StatsModel)
        fun updateUserInfo(user: UserModel)
        fun  updateButton(s: String, sub : Boolean)
    }

    interface Presenter : BasePresenter {
        fun getStats(user: Int)
        fun getBets(user: Int)
        fun getUser(id : Int)
        fun subscribe(id: Int)
        fun unsubscribe(id: Int)
    }
}