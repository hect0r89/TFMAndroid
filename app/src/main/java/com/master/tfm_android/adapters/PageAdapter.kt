package com.master.tfm_android.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.master.tfm_android.views.main.principal.MyBetsFragment
import com.master.tfm_android.views.main.principal.MyStatsFragment
import com.master.tfm_android.views.main.principal.SubscribedBetsFragment

class PagerAdapter(fm: FragmentManager, private val numTabs: Int ) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                val tab1 = SubscribedBetsFragment().newInstance()
                return tab1
            }
            1 -> {
                val tab2 = MyBetsFragment().newInstance()
                return tab2
            }
            2 -> {
                val tab3 = MyStatsFragment().newInstance()
                return tab3
            }
            else -> throw RuntimeException("Tab position invalid " + position) as Throwable
        }
    }

    override fun getCount(): Int {
        return numTabs
    }

}
