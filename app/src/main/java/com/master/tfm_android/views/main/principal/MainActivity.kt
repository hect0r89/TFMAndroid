package com.master.tfm_android.views.main.principal

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View

import com.master.tfm_android.R
import com.master.tfm_android.utils.ActivityUtils

class MainActivity : AppCompatActivity() {

    var baseFragment: MainBaseFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        baseFragment = supportFragmentManager
                .findFragmentById(R.id.contentMainFrame) as? MainBaseFragment
        if (baseFragment == null) {
            baseFragment = MainBaseFragment().newInstance()
            baseFragment?.let{ActivityUtils.addFragmentToActivity(supportFragmentManager, it, R.id.contentMainFrame)}

        }

        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener(View.OnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        })
    }

}
