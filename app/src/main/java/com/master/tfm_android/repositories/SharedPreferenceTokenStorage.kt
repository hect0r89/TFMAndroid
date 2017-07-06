package com.master.tfm_android.repositories

import android.content.Context
import android.content.SharedPreferences



class SharedPreferenceTokenStorage(context : Context, prefFileName : String) : TokenStorage {
    val PREF_KEY_TOKEN = "token"

    private var mPrefs: SharedPreferences? = null

    init {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)
    }

    override fun saveToken(token: String) {
        mPrefs?.edit()?.putString(PREF_KEY_TOKEN, token)?.apply()
    }

    override fun getToken() : String?{
        return mPrefs?.getString(PREF_KEY_TOKEN, "")
    }

}