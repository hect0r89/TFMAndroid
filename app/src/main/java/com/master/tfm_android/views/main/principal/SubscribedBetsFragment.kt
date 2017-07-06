package com.master.tfm_android.views.main.principal

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.master.tfm_android.R
import com.master.tfm_android.contracts.AuthenticationContract
import com.master.tfm_android.views.authentication.OnCorrectLoginListener
import org.jetbrains.annotations.Nullable



class SubscribedBetsFragment : Fragment() {


    fun newInstance(): SubscribedBetsFragment {
        return SubscribedBetsFragment()
    }



    @Nullable
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_subscribed_bets, container, false)

        return root
    }


}


