package com.master.tfm_android.views.main.principal

import android.content.Context
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.master.tfm_android.R
import com.master.tfm_android.adapters.MyBetsRecyclerViewAdapter
import com.master.tfm_android.contracts.MyBetsContract
import com.master.tfm_android.models.BetModel
import com.master.tfm_android.presenters.MyBetsPresenter
import org.jetbrains.annotations.Nullable
import com.master.tfm_android.R.id.fab


class MyBetsFragment : Fragment(), MyBetsContract.View {



    private var mPresenter: MyBetsContract.Presenter? = null
    private var bets: ArrayList<BetModel> = ArrayList()
    private var interactionListener: OnListFragmentInteractionListener? = null
    var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyBetsPresenter(this)
    }

    override fun onResume() {
        super.onResume()
        mPresenter?.start()
    }

    fun start() {
        mPresenter?.start()
    }


    companion object {
        fun newInstance(): MyBetsFragment {
            return MyBetsFragment()
        }
    }

    override fun setPresenter(presenter: MyBetsContract.Presenter) {
        mPresenter = checkNotNull(presenter)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            interactionListener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        interactionListener = null
    }


    @Nullable
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_my_bets, container, false)
        var layoutManager = LinearLayoutManager(context)
        recyclerView = root as RecyclerView?
        recyclerView?.let { it.layoutManager = layoutManager }
        val dividerItemDecoration = DividerItemDecoration(recyclerView?.context, layoutManager.orientation)
        recyclerView?.let { it.addItemDecoration(dividerItemDecoration) }
        recyclerView?.let { it.adapter = MyBetsRecyclerViewAdapter(bets, interactionListener) }
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

    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(item: BetModel)
    }


    override fun updateMyBets(bets: ArrayList<BetModel>) {
        this.bets.clear()
        this.bets.addAll(bets)
        recyclerView?.adapter?.notifyDataSetChanged()
    }

    override fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

    interface OnScrolledRecyclerView {
        fun getFabButton(): FloatingActionButton?
    }


}



