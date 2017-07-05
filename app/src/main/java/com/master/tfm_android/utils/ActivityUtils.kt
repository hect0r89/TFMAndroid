package com.master.tfm_android.utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager


/**
 * Created by hector on 4/7/17.
 */

object ActivityUtils {
    val PREF_NAME : String = "Preferences"
    val TOKEN_NAME : String = "token"
    /**
     * The `fragment` is added to the container view with id `frameId`. The operation is
     * performed by the `fragmentManager`.

     */
    fun addFragmentToActivity(fragmentManager: FragmentManager,
                              fragment: Fragment, frameId: Int) {
        checkNotNull(fragmentManager)
        checkNotNull(fragment)
        val transaction = fragmentManager.beginTransaction()
        transaction.add(frameId, fragment)
        transaction.commit()
    }

}
