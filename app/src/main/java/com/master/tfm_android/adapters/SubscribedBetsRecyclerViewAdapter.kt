package com.master.tfm_android.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.master.tfm_android.R
import com.master.tfm_android.models.BetModel
import com.master.tfm_android.views.main.principal.SubscribedBetsFragment

class SubscribedBetsRecyclerViewAdapter(private val bets: List<BetModel>, private val interactionListener: SubscribedBetsFragment.OnListFragmentInteractionListener?) : RecyclerView.Adapter<SubscribedBetsRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_subscribed_bets_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bet = bets.get(position)
        holder.betItem = bet
        holder.eventView.text = bet.event
        holder.pickView.text = bet.pick
        holder.oddsView.text = bet.odds.toString()
        holder.statusView.text = bet.status
        holder.stakeView.text = bet.stake.toString()

        holder.mView.setOnClickListener {
            interactionListener?.onListFragmentInteraction(holder.betItem as BetModel)
        }
    }

    override fun getItemCount(): Int {
        return bets.size
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val eventView: TextView
        val pickView: TextView
        val oddsView: TextView
        val statusView: TextView
        val stakeView: TextView
        var betItem: BetModel? = null

        init {
            eventView = mView.findViewById(R.id.txtEvent) as TextView
            pickView = mView.findViewById(R.id.txtPick) as TextView
            oddsView = mView.findViewById(R.id.txtOdds) as TextView
            statusView = mView.findViewById(R.id.txtStatus) as TextView
            stakeView = mView.findViewById(R.id.txtStake) as TextView

        }
    }
}
