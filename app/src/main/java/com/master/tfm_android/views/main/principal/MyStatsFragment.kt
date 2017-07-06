package com.master.tfm_android.views.main.principal

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.master.tfm_android.R
import org.jetbrains.annotations.Nullable



class MyStatsFragment : Fragment() {


    fun newInstance(): MyStatsFragment {
        return MyStatsFragment()
    }



    @Nullable
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_my_stats, container, false)

        return root
    }


}



