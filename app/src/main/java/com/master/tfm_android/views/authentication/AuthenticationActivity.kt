package com.master.tfm_android.views.authentication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.util.Log
import com.master.tfm_android.R
import com.master.tfm_android.presenters.AuthenticationPresenter
import com.master.tfm_android.repositories.RetrofitAuthenticationRepository
import com.master.tfm_android.repositories.SharedPreferenceTokenStorage
import com.master.tfm_android.services.BetsApi
import com.master.tfm_android.utils.ActivityUtils
import com.master.tfm_android.utils.ActivityUtils.PREF_NAME
import com.master.tfm_android.utils.ActivityUtils.isInternetAvailable
import com.master.tfm_android.views.main.principal.MainActivity

class AuthenticationActivity : AppCompatActivity(), LoginFragment.OnRegisterClickListener, RegisterFragment.OnLoginClickListener, OnCorrectLoginListener {


    var loginFragment: LoginFragment? = null
    var registerFragment: RegisterFragment? = null
    var token : String = ""
    var prefs: SharedPreferenceTokenStorage? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)
        RetrofitAuthenticationRepository.initialize(this)
        BetsApi.initialize()
        prefs = SharedPreferenceTokenStorage(context = applicationContext, prefFileName = PREF_NAME)
        prefs?.getToken()?.let {
            token = it
        }

        if (token.isNotEmpty()) {
            onCorrectLogin()
        } else {
            loginFragment = supportFragmentManager
                    .findFragmentById(R.id.contentFrame) as? LoginFragment
            if (loginFragment == null) {
                loginFragment = LoginFragment.newInstance()
                loginFragment?.let { setFragment(it) }

            }

            registerFragment = supportFragmentManager
                    .findFragmentById(R.id.contentFrame) as? RegisterFragment
            if (registerFragment == null) {
                registerFragment = RegisterFragment.newInstance()
            }



            AuthenticationPresenter(loginFragment as LoginFragment, registerFragment as RegisterFragment, prefs as SharedPreferenceTokenStorage)
        }


    }

    override fun onRegisterClick() {
        if (registerFragment == null) {
            registerFragment = RegisterFragment.newInstance()
        }
        registerFragment?.let {
            ActivityUtils.replaceFragmentToActivity(supportFragmentManager,
                    it, R.id.contentFrame)
        }

    }

    override fun onLoginClick() {
        if (loginFragment == null) {
            loginFragment = LoginFragment.newInstance()
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
        val token = prefs?.getToken()
        if(RetrofitAuthenticationRepository.instance.checkCredentials(token as String)){
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            AlertDialog.Builder(applicationContext).setTitle("Error").setMessage("Token error, delete app data and try again").setPositiveButton("OK", { dialog, id ->  }).show()
        }

    }


}

interface OnCorrectLoginListener {
    fun onCorrectLogin()
}
