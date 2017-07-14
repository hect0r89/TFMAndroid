package com.master.tfm_android.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.master.tfm_android.models.BetModel
import com.master.tfm_android.models.StatsModel
import com.master.tfm_android.views.main.principal.*

class DetailPageAdapter(fm: FragmentManager, private val numTabs: Int) : FragmentStatePagerAdapter(fm){
    var tab1: BetsListDetailFragment? = null
    var tab2: MyBetsFragment? = null
    var bets : List<BetModel>? = null

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                if (tab1 == null) {
                    tab1 = BetsListDetailFragment.newInstance()
                }
                return tab1 as BetsListDetailFragment
            }
            1 -> {
                if (tab2 == null) {
                    tab2 = MyBetsFragment.newInstance()
                }

                return tab2 as MyBetsFragment
            }
            else -> throw RuntimeException("Tab position invalid " + position) as Throwable
        }
    }

    override fun getCount(): Int {
        return numTabs
    }

    fun loadBets(bets : List<BetModel>){
        this.bets = bets
        tab1?.updateBets(bets)
    }




}

