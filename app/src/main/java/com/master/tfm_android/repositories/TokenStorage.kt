package com.master.tfm_android.repositories

/**
 * Created by hector on 6/7/17.
 */

interface TokenStorage {
    fun saveToken(token : String)

    fun getToken() : String?
}