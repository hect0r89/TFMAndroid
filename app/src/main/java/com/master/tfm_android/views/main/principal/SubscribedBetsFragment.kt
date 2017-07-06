package com.master.tfm_android.views.main.principal

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.master.tfm_android.R
import com.master.tfm_android.contracts.SubscribedBetsContract
import com.master.tfm_android.presenters.SubscribedBetsPresenter
import org.jetbrains.annotations.Nullable
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.master.tfm_android.adapters.SubscribedBetsRecyclerViewAdapter
import com.master.tfm_android.models.BetModel
import android.support.v7.widget.DividerItemDecoration




class SubscribedBetsFragment : Fragment(), SubscribedBetsContract.View {



    private var mPresenter: SubscribedBetsContract.Presenter? = null
    private var bets: ArrayList<BetModel> = ArrayList()
    private var interactionListener: OnListFragmentInteractionListener? = null
    var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SubscribedBetsPresenter(this)
    }

    override fun onResume() {
        super.onResume()
        mPresenter?.start()
    }


    fun newInstance(): SubscribedBetsFragment {
        return SubscribedBetsFragment()
    }

    override fun setPresenter(presenter: SubscribedBetsContract.Presenter) {
        mPresenter = checkNotNull(presenter)
    }


    @Nullable
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_subscribed_bets_item_list, container, false)
        var layoutManager = LinearLayoutManager(context)
        recyclerView = root as RecyclerView?
        recyclerView?.let {
            it.layoutManager = layoutManager
        }
        val dividerItemDecoration = DividerItemDecoration(recyclerView?.context,
                layoutManager.orientation)
        recyclerView?.let {
            it.addItemDecoration(dividerItemDecoration)
        }
        recyclerView?.let {
            it.adapter = SubscribedBetsRecyclerViewAdapter(bets, interactionListener)
        }
        return root
    }

    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(item: BetModel)
    }


    override fun updateSubscribedBets(bets: ArrayList<BetModel>) {
        this.bets.clear()
        this.bets.addAll(bets)
        recyclerView?.let { it.adapter.notifyDataSetChanged() }
    }


}


