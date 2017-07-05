package com.master.tfm_android.contracts

import com.master.tfm_android.BasePresenter
import com.master.tfm_android.BaseView


/**
 * Created by hector on 4/7/17.
 */
interface LoginContract {

    interface View : BaseView<Presenter> {
        fun showErrors(msgError: String)

        fun showMainActivity()

    }

    interface Presenter : BasePresenter {

        fun login(username: String, password : String)

    }
}