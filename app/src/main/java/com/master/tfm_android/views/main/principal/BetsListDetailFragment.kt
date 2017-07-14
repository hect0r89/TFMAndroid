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
import com.master.tfm_android.adapters.DetailBetsRecyclerViewAdapter


class BetsListDetailFragment : Fragment(){
    private var bets: ArrayList<BetModel> = ArrayList()
    var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }


    companion object {
        fun newInstance(): BetsListDetailFragment {
           return  BetsListDetailFragment()
        }
    }



    @Nullable
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_list_bets_detail, container, false)
        var layoutManager = LinearLayoutManager(context)
//        recyclerView = root as RecyclerView?
//        recyclerView?.let { it.layoutManager = layoutManager }
//        val dividerItemDecoration = DividerItemDecoration(recyclerView?.context, layoutManager.orientation)
//        recyclerView?.let { it.addItemDecoration(dividerItemDecoration) }
//        recyclerView?.let {
//            it.adapter = DetailBetsRecyclerViewAdapter(bets)
//        }
        return root
    }

    fun updateBets(bets : List<BetModel>){
        this.bets = bets as ArrayList<BetModel>
        recyclerView?.adapter?.notifyDataSetChanged()
    }
}



