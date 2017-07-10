package com.master.tfm_android.views.main.principal

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar

import com.master.tfm_android.R
import com.master.tfm_android.models.BetModel
import com.master.tfm_android.presenters.ManageBetPresenter
import com.master.tfm_android.repositories.RetrofitMainRepository
import com.master.tfm_android.utils.ActivityUtils

class MainActivity : AppCompatActivity(), MainBaseFragment.OnCreateBetClickListener, CreateBetFragment.OnUpdateData,
        MyBetsFragment.OnListFragmentInteractionListener, BetDetailFragment.OnEditBetClickListener, EditBetFragment.OnUpdateBetDataListener,
        SubscribedBetsFragment.OnClickSubscribedBetsListener, BetDetailFragment.OnCopyBetClickListener {



    var baseFragment: MainBaseFragment? = null
    var mCreateBetFragment: CreateBetFragment? = null
    var mBetDetailFragment: BetDetailFragment? = null
    var mEditBetFragment: EditBetFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        RetrofitMainRepository.initialize(context = this)

        baseFragment = supportFragmentManager
                .findFragmentById(R.id.contentMainFrame) as? MainBaseFragment
        if (baseFragment == null) {
            baseFragment = MainBaseFragment.newInstance()
            baseFragment?.let { ActivityUtils.addFragmentToActivity(supportFragmentManager, it, R.id.contentMainFrame) }

        }


    }

    override fun onCreateBetClick() {
        invalidateOptionsMenu()
        mCreateBetFragment = supportFragmentManager
                .findFragmentById(R.id.contentMainFrame) as? CreateBetFragment
        if (mCreateBetFragment == null) {
            mCreateBetFragment = CreateBetFragment.newInstance(null)
            mCreateBetFragment?.let {

                ActivityUtils.addOnTopFragmentToActivity(supportFragmentManager,
                        it, R.id.contentMainFrame)
            }
        }
    }

    override fun onCopyBetClick(bet: BetModel) {
        mCreateBetFragment = supportFragmentManager
                .findFragmentById(R.id.contentMainFrame) as? CreateBetFragment
        if (mCreateBetFragment == null) {
            mCreateBetFragment = CreateBetFragment.newInstance(bet)
            mCreateBetFragment?.let {

                ActivityUtils.addOnTopFragmentToActivity(supportFragmentManager,
                        it, R.id.contentMainFrame)
            }
        }
    }

    override fun onEditBetClick(bet: BetModel) {
        invalidateOptionsMenu()
        mEditBetFragment = supportFragmentManager
                .findFragmentById(R.id.contentMainFrame) as? EditBetFragment
        if (mEditBetFragment == null) {
            mEditBetFragment = EditBetFragment.newInstance(bet)
        }
        mEditBetFragment?.let {

            ActivityUtils.addOnTopFragmentToActivity(supportFragmentManager,
                    it, R.id.contentMainFrame)
        }

    }

    override fun updateData() {
        baseFragment?.updateData()

    }

    override fun updateBetData(bet: BetModel) {
        mBetDetailFragment?.updateBetDetail(bet)
    }

    override fun onListFragmentInteraction(item: BetModel) {
        mBetDetailFragment = supportFragmentManager
                .findFragmentById(R.id.contentMainFrame) as? BetDetailFragment

        mBetDetailFragment = item.id?.let { BetDetailFragment.newInstance(it, true) }
        mBetDetailFragment?.let {

            ActivityUtils.addOnTopFragmentToActivity(supportFragmentManager,
                    it, R.id.contentMainFrame)
        }

    }

    override fun onClickSubscribedBet(item: BetModel) {
        mBetDetailFragment = supportFragmentManager
                .findFragmentById(R.id.contentMainFrame) as? BetDetailFragment

        mBetDetailFragment = item.id?.let { BetDetailFragment.newInstance(it, false) }
        mBetDetailFragment?.let {

            ActivityUtils.addOnTopFragmentToActivity(supportFragmentManager,
                    it, R.id.contentMainFrame)
        }
    }

}
