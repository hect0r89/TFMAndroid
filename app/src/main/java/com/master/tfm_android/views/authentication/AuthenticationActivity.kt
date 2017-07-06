package com.master.tfm_android.views.authentication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.master.tfm_android.R
import com.master.tfm_android.presenters.AuthenticationPresenter
import com.master.tfm_android.repositories.SharedPreferenceTokenStorage
import com.master.tfm_android.utils.ActivityUtils
import com.master.tfm_android.utils.ActivityUtils.PREF_NAME
import com.master.tfm_android.views.main.principal.MainActivity

class AuthenticationActivity : AppCompatActivity(), LoginFragment.OnRegisterClickListener, RegisterFragment.OnLoginClickListener, OnCorrectLoginListener {


    var loginFragment: LoginFragment? = null
    var registerFragment: RegisterFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)

        val prefs = SharedPreferenceTokenStorage(context = applicationContext, prefFileName = PREF_NAME)
        val token = prefs.getToken()

        if (token!!.isNotEmpty()) {
            onCorrectLogin()
        } else {
            loginFragment = supportFragmentManager
                    .findFragmentById(R.id.contentFrame) as? LoginFragment
            if (loginFragment == null) {
                loginFragment = LoginFragment().newInstance()
                loginFragment?.let { setFragment(it) }

            }

            registerFragment = supportFragmentManager
                    .findFragmentById(R.id.contentFrame) as? RegisterFragment
            if (registerFragment == null) {
                registerFragment = RegisterFragment().newInstance()
            }



            AuthenticationPresenter(loginFragment as LoginFragment, registerFragment as RegisterFragment, prefs)
        }


    }

    override fun onRegisterClick() {
        if (registerFragment == null) {
            registerFragment = RegisterFragment().newInstance()
        }
        registerFragment?.let {
            ActivityUtils.replaceFragmentToActivity(supportFragmentManager,
                    it, R.id.contentFrame)
        }

    }

    override fun onLoginClick() {
        if (loginFragment == null) {
            loginFragment = LoginFragment().newInstance()
        }
        loginFragment?.let {
            ActivityUtils.replaceFragmentToActivity(supportFragmentManager,
                    it, R.id.contentFrame)
        }

    }

    private fun setFragment(fragment: Fragment) {
        ActivityUtils.addFragmentToActivity(supportFragmentManager,
                fragment, R.id.contentFrame)
    }

    override fun onCorrectLogin() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        finish()
    }


}

interface OnCorrectLoginListener {
    fun onCorrectLogin()
}
