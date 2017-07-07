package com.master.tfm_android.views.main.principal

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.master.tfm_android.R
import com.master.tfm_android.adapters.PagerAdapter
import com.master.tfm_android.utils.ActivityUtils
import org.jetbrains.annotations.Nullable


class MainBaseFragment : Fragment() {



    companion object {
        fun newInstance(): MainBaseFragment {
            return MainBaseFragment()
        }
    }


    @Nullable
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_main_base, container, false)

        val tabLayout = root.findViewById(R.id.tab_layout) as TabLayout
        tabLayout.addTab(tabLayout.newTab().setText("TIME LINE"))
        tabLayout.addTab(tabLayout.newTab().setText("MY BETS"))
        tabLayout.addTab(tabLayout.newTab().setText("MY STATS"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val viewPager: ViewPager = root.findViewById(R.id.pager) as ViewPager
        val adapter: PagerAdapter = PagerAdapter(activity.supportFragmentManager, tabLayout.tabCount)
        viewPager.adapter = adapter

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(getOnTabSelectedListener(viewPager))

        val fab = root.findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener({ (activity as OnCreateBetClickListener).onCreateBetClick()})
        return root
    }

    private fun getOnTabSelectedListener(viewPager: ViewPager): TabLayout.OnTabSelectedListener {
        return object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
                val adapter = viewPager.adapter as PagerAdapter
                if (tab.position == 0) {
                    (adapter.getItem(tab.position) as SubscribedBetsFragment).start()
                }
                if (tab.position == 1) {
                    (adapter.getItem(tab.position) as MyBetsFragment).start()
                }
                if (tab.position == 2) {
                    (adapter.getItem(tab.position) as MyStatsFragment).start()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                // nothing now
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                // nothing now
            }
        }
    }

    interface OnCreateBetClickListener {
        fun onCreateBetClick()
    }

}


