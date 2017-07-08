package com.master.tfm_android.views.main.principal

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View

import com.master.tfm_android.R
import com.master.tfm_android.repositories.RetrofitMainRepository
import com.master.tfm_android.utils.ActivityUtils

class MainActivity : AppCompatActivity(), MainBaseFragment.OnCreateBetClickListener,  CreateBetFragment.OnUpdateData {


    var baseFragment: MainBaseFragment? = null
    var mCreateBetFragment: CreateBetFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        RetrofitMainRepository.initialize()

        baseFragment = supportFragmentManager
                .findFragmentById(R.id.contentMainFrame) as? MainBaseFragment
        if (baseFragment == null) {
            baseFragment = MainBaseFragment.newInstance()
            baseFragment?.let { ActivityUtils.addFragmentToActivity(supportFragmentManager, it, R.id.contentMainFrame) }

        }


    }

    override fun onCreateBetClick() {
        mCreateBetFragment = supportFragmentManager
                .findFragmentById(R.id.contentMainFrame) as? CreateBetFragment
        if (mCreateBetFragment == null) {
            mCreateBetFragment = CreateBetFragment.newInstance()
            mCreateBetFragment?.let {

                ActivityUtils.addOnTopFragmentToActivity(supportFragmentManager,
                        it, R.id.contentMainFrame)
            }

        }

    }

    override fun updateData() {
        baseFragment?.updateData()
    }


}
