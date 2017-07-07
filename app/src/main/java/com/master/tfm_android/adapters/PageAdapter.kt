package com.master.tfm_android.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.master.tfm_android.views.main.principal.MyBetsFragment
import com.master.tfm_android.views.main.principal.MyStatsFragment
import com.master.tfm_android.views.main.principal.SubscribedBetsFragment

class PagerAdapter(fm: FragmentManager, private val numTabs: Int) : FragmentStatePagerAdapter(fm) {
    var tab1: SubscribedBetsFragment? = null
    var tab2: MyBetsFragment? = null
    var tab3: MyStatsFragment? = null

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                if (tab1 == null) {
                    tab1 = SubscribedBetsFragment.newInstance()
                }
                return tab1 as SubscribedBetsFragment
            }
            1 -> {
                if (tab2 == null) {
                    tab2 = MyBetsFragment.newInstance()
                }

                return tab2 as MyBetsFragment
            }
            2 -> {
                if (tab3 == null) {
                    tab3 = MyStatsFragment.newInstance()
                }

                return tab3 as MyStatsFragment
            }
            else -> throw RuntimeException("Tab position invalid " + position) as Throwable
        }
    }

    override fun getCount(): Int {
        return numTabs
    }


}
