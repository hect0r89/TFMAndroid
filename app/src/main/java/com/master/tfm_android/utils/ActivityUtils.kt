package com.master.tfm_android.utils

import android.content.Context
import android.graphics.Color
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.widget.TextView
import com.master.tfm_android.models.BetModel
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import java.net.InetAddress
import android.net.NetworkInfo




/**
 * Created by hector on 4/7/17.
 */

object ActivityUtils {
    val PREF_NAME: String = "Preferences"
    val TOKEN_NAME: String = "token"
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

    fun addOnTopFragmentToActivity(fragmentManager: FragmentManager,
                                   fragment: Fragment, frameId: Int) {
        checkNotNull(fragmentManager)
        checkNotNull(fragment)
        val transaction = fragmentManager.beginTransaction()
        transaction.add(frameId, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun replaceFragmentToActivity(fragmentManager: FragmentManager,
                                  fragment: Fragment, frameId: Int) {
        checkNotNull(fragmentManager)
        checkNotNull(fragment)
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(frameId, fragment)
        transaction.commit()
    }

     fun setStatus(bet: BetModel, view : TextView) {
        when (bet.status) {
            'P'-> {
                view.text = "Pending"
                view.setTextColor(Color.parseColor("#F57F17"))
            }
            'W' -> {
                view.text = "+${(bet.amount * bet.odds - bet.amount).format(2)} €"
                view.setTextColor(Color.parseColor("#64DD17"))
            }
            'L' -> {
                view.text = "-${bet.amount} €"
                view.setTextColor(Color.parseColor("#C62828"))
            }
            'N' -> {
                view.text = "0.0 €"
                view.setTextColor(Color.parseColor("#2196F3"))
            }
        }
    }

    fun setStatusUnits(bet: BetModel, view : TextView) {
        when (bet.status) {
            'P'-> {
                view.text = "Pending"
                view.setTextColor(Color.parseColor("#F57F17"))
            }
            'W' -> {
                view.text = "+${((bet.stake * 10).toInt() * bet.odds - (bet.stake * 10).toInt()).format(2)} u"
                view.setTextColor(Color.parseColor("#64DD17"))
            }
            'L' -> {
                view.text = "-${(bet.stake * 10).toInt()} u"
                view.setTextColor(Color.parseColor("#C62828"))
            }
            'N' -> {
                view.text = "0.0 u"
                view.setTextColor(Color.parseColor("#2196F3"))
            }
        }
    }

    fun isInternetAvailable(context : Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager// 1
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected

    }

    fun Double.format(digits: Int) = java.lang.String.format("%.${digits}f", this)

}
