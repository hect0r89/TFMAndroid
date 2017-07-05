package com.master.tfm_android.views.authentication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.master.tfm_android.R
import com.master.tfm_android.presenters.LoginPresenter
import com.master.tfm_android.utils.ActivityUtils
import com.master.tfm_android.utils.ActivityUtils.PREF_NAME
import com.master.tfm_android.utils.ActivityUtils.TOKEN_NAME

class AuthenticationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)

        var loginFragment: LoginFragment? = supportFragmentManager
                .findFragmentById(R.id.contentFrame) as? LoginFragment
        if (loginFragment == null) {
            loginFragment = LoginFragment().newInstance()
                ActivityUtils.addFragmentToActivity(supportFragmentManager,
                        loginFragment, R.id.contentFrame)
        }
        val prefs = this.getSharedPreferences(PREF_NAME, 0)
        val token = prefs!!.getString(TOKEN_NAME, "")
        LoginPresenter(loginFragment, prefs)

    }
}
