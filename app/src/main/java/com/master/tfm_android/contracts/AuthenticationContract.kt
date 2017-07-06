package com.master.tfm_android.contracts

import com.master.tfm_android.BasePresenter
import com.master.tfm_android.BaseView



interface AuthenticationContract {

    interface View : BaseView<Presenter> {
        fun showErrors(msgError: String)

        fun showMainActivity()
    }

    interface Presenter : BasePresenter {

        fun login(username: String, password : String)

        fun register(username: String, password : String, email : String)
    }
}