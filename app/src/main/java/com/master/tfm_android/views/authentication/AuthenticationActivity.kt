package com.master.tfm_android.views.authentication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.master.tfm_android.R
import com.master.tfm_android.utils.ActivityUtils

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

    }
}
