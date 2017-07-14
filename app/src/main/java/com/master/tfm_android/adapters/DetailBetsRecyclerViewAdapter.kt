package com.master.tfm_android.adapters

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.master.tfm_android.R
import com.master.tfm_android.models.BetModel
import com.master.tfm_android.utils.ActivityUtils
import com.master.tfm_android.views.main.principal.MyBetsFragment


class DetailBetsRecyclerViewAdapter(private val bets: List<BetModel>) : RecyclerView.Adapter<DetailBetsRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragments_list_bets_detail_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bet = bets.get(position)
        holder.betItem = bet
        holder.eventView.text = bet.event
        holder.pickView.text = bet.pick
        holder.oddsView.text = bet.odds.toString()
        ActivityUtils.setStatus(bet, holder.statusView)
        holder.stakeView.text = "${(bet.stake * 10).toInt() }/10"
        holder.amountView.text = "${bet.amount} €"

    }

    override fun getItemCount(): Int {
        return bets.size
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val eventView: TextView = mView.findViewById(R.id.txtEvent) as TextView
        val pickView: TextView = mView.findViewById(R.id.txtPick) as TextView
        val oddsView: TextView = mView.findViewById(R.id.txtOdds) as TextView
        val statusView: TextView = mView.findViewById(R.id.txtStatus) as TextView
        val stakeView: TextView = mView.findViewById(R.id.txtStake) as TextView
        val amountView: TextView = mView.findViewById(R.id.txtAmount) as TextView
        var betItem: BetModel? = null
    }
}