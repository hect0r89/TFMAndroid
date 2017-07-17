package com.master.tfm_android.views.main.principal

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.master.tfm_android.R
import com.master.tfm_android.adapters.DetailPageAdapter
import com.master.tfm_android.adapters.PagerAdapter
import com.master.tfm_android.adapters.SubscribedBetsRecyclerViewAdapter
import com.master.tfm_android.contracts.UserDetailContract
import com.master.tfm_android.models.BetModel
import com.master.tfm_android.models.StatsModel
import com.master.tfm_android.models.UserModel
import com.master.tfm_android.presenters.UserDetailPresenter
import java.util.HashMap

class UserDetailFragment : Fragment(), UserDetailContract.View {


    private var mPresenter: UserDetailContract.Presenter? = null
    private var bets: ArrayList<BetModel> = ArrayList()
    private var stats: StatsModel? = null
    private var interactionListener: OnClickUserBetListener? = null
    private var userId: Int? = null
    private var user: UserModel? = null
    private var userTextsView: HashMap<String, TextView> = HashMap()
    var viewPager : ViewPager? = null
    var btnSub : Button? = null
    var progress : ProgressDialog? = null
    var root : View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        UserDetailPresenter(this)
    }

    override fun onResume() {
        super.onResume()
        userId?.let { mPresenter?.getBets(it) }
        userId?.let { mPresenter?.getUser(it) }
        userId?.let { mPresenter?.getStats(it) }
    }

    fun start() {
        userId?.let { mPresenter?.getBets(it) }
        userId?.let { mPresenter?.getUser(it) }
        userId?.let { mPresenter?.getStats(it) }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is UserDetailFragment.OnClickUserBetListener) {
            interactionListener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        interactionListener = null
    }


    companion object {
        fun newInstance(user: Int): UserDetailFragment {
            val frag = UserDetailFragment()
            frag.userId = user
            return frag
        }
    }

    override fun setPresenter(presenter: UserDetailContract.Presenter) {
        mPresenter = checkNotNull(presenter)
    }


    @Nullable
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_user_detail, container, false)

        userTextsView["username"] = (root as View?)?.findViewById(R.id.txtUsernameDetail) as TextView
        userTextsView["email"] = (root as View?)?.findViewById(R.id.txtEmailDetail) as TextView
        userTextsView["yield"] = (root as View?)?.findViewById(R.id.txtYieldDetail) as TextView
        btnSub = (root as View?)?.findViewById(R.id.btnSubs) as Button
        btnSub?.setOnClickListener { btnAction() }
        val tabLayout = (root as View?)?.findViewById(R.id.tab_layout) as TabLayout
        tabLayout.addTab(tabLayout.newTab().setText("BETS"))
        tabLayout.addTab(tabLayout.newTab().setText("STATS"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        viewPager = (root as View?)?.findViewById(R.id.pager) as ViewPager
        val adapter: DetailPageAdapter = DetailPageAdapter(childFragmentManager, tabLayout.tabCount)
        viewPager?.adapter = adapter
        viewPager?.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        viewPager?.let { tabLayout.addOnTabSelectedListener(getOnTabSelectedListener(it)) }
        progress = ProgressDialog.show(activity, "Loading",
                "Wait a moment please...", true)
        (root as View?)?.visibility = View.INVISIBLE
        return root
    }

    private fun  btnAction() {
        user?.let {
            if (!it.subscribed) mPresenter?.subscribe(userId!!) else mPresenter?.unsubscribe(userId!!)
        }
        (activity as MainActivity).updateData()


    }

    private fun getOnTabSelectedListener(viewPager: ViewPager): TabLayout.OnTabSelectedListener {
        return object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                // nothing now
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                // nothing now
            }
        }
    }

    interface OnClickUserBetListener {
        fun onClickBet(item: BetModel)
    }


    override fun updateBets(bets: ArrayList<BetModel>) {
        (viewPager?.adapter as DetailPageAdapter).loadBets(bets)
        progress?.dismiss()
        root?.visibility = View.VISIBLE
    }

    override fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

    override fun updateStats(stats: StatsModel) {
        (viewPager?.adapter as DetailPageAdapter).loadStats(stats)
        userTextsView["yield"]?.text = "${stats.yield.format(2)} %"
        if (stats.yield > 0) userTextsView["yield"]?.setTextColor(Color.parseColor("#64DD17"))
        else if (stats.yield < 0) userTextsView["yield"]?.setTextColor(Color.parseColor("#C62828"))
    }



    fun Double.format(digits: Int) = java.lang.String.format("%.${digits}f", this)

    override fun updateUserInfo(user: UserModel) {
        this.user = user
        userTextsView["username"]?.text = user.username
        userTextsView["email"]?.text = user.email
        if (!user.subscribed) btnSub?.text = "SUBSCRIBE" else btnSub?.text = "UNSUBSCRIBE"

    }

    override fun updateButton(s: String, sub: Boolean) {
        this.user?.subscribed = sub
        btnSub?.text = s
    }


    interface subscriptionUserUpdateListener{
        fun subscriptionClick()
    }




}
