package com.master.tfm_android.views.main.principal

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
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
import android.widget.Toast


class SubscribedBetsFragment : Fragment(), SubscribedBetsContract.View {

    private var mPresenter: SubscribedBetsContract.Presenter? = null
    private var bets: ArrayList<BetModel> = ArrayList()
    private var interactionListener: OnClickSubscribedBetsListener? = null
    var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SubscribedBetsPresenter(this)
    }

    override fun onResume() {
        super.onResume()
        mPresenter?.start()
    }

    fun start() {
        mPresenter?.start()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SubscribedBetsFragment.OnClickSubscribedBetsListener) {
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
        fun newInstance(): SubscribedBetsFragment {
            return SubscribedBetsFragment()
        }
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
        recyclerView?.let { it.layoutManager = layoutManager }
        val dividerItemDecoration = DividerItemDecoration(recyclerView?.context,
                layoutManager.orientation)
        recyclerView?.let { it.addItemDecoration(dividerItemDecoration) }
        recyclerView?.let { it.adapter = SubscribedBetsRecyclerViewAdapter(bets, interactionListener) }
        recyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            var fm = fragmentManager
            var fragm = fm.findFragmentById(R.id.contentMainFrame)
            var fab: FloatingActionButton? = null
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                if (fragm is MainBaseFragment) {
                    fab = (fragm as MainBaseFragment).getFabButton()
                    fab?.let {
                        if (dy > 0 || dy < 0 && it.isShown) {
                            it.hide()
                        }
                    }
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (fragm is MainBaseFragment) {
                        fab = (fragm as MainBaseFragment).getFabButton()
                        fab?.show()
                    }
                }

                super.onScrollStateChanged(recyclerView, newState)
            }
        })

        return root
    }

    interface OnClickSubscribedBetsListener {
        fun onClickSubscribedBet(item: BetModel)
        fun onClickDetail(id: Int)
    }


    override fun updateSubscribedBets(bets: ArrayList<BetModel>) {
        this.bets.clear()
        this.bets.addAll(bets)
        recyclerView?.let { it.adapter.notifyDataSetChanged() }

    }

    override fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }


}


