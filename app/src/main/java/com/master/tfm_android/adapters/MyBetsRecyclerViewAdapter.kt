package com.master.tfm_android.adapters

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.master.tfm_android.R
import com.master.tfm_android.models.BetModel
import com.master.tfm_android.views.main.principal.MyBetsFragment


class MyBetsRecyclerViewAdapter(private val bets: List<BetModel>, private val interactionListener: MyBetsFragment.OnListFragmentInteractionListener?) : RecyclerView.Adapter<MyBetsRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_my_bets_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bet = bets.get(position)
        holder.betItem = bet
        holder.eventView.text = bet.event
        holder.pickView.text = bet.pick
        holder.oddsView.text = bet.odds.toString()
        showStatus(bet, holder.statusView)
        holder.stakeView.text = "${(bet.stake * 10).toInt() }/10"
        holder.amountView.text = "${bet.amount} €"

        holder.mView.setOnClickListener {
            interactionListener?.onListFragmentInteraction(holder.betItem as BetModel)
        }
    }

    private fun showStatus(bet: BetModel, view : TextView) {
        when (bet.status) {
            'P'-> {
                view.text = "Pending"
                view.setTextColor(Color.parseColor("#F57F17"))
            }
            'W' -> {
                view.text = "+${(bet.amount * bet.odds - bet.amount).format(2)} €"
                view.setTextColor(Color.parseColor("#64DD17"))
            }
            'L' -> {
                view.text = "-${bet.amount} €"
                view.setTextColor(Color.parseColor("#C62828"))
            }
            'N' -> {
                view.text = "0.0 €"
                view.setTextColor(Color.parseColor("#2196F3"))
            }
        }
    }

    fun Double.format(digits: Int) = java.lang.String.format("%.${digits}f", this)

    override fun getItemCount(): Int {
        return bets.size
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val eventView: TextView
        val pickView: TextView
        val oddsView: TextView
        val statusView: TextView
        val stakeView: TextView
        val amountView: TextView
        var betItem: BetModel? = null

        init {
            eventView = mView.findViewById(R.id.txtEvent) as TextView
            pickView = mView.findViewById(R.id.txtPick) as TextView
            oddsView = mView.findViewById(R.id.txtOdds) as TextView
            statusView = mView.findViewById(R.id.txtStatus) as TextView
            stakeView = mView.findViewById(R.id.txtStake) as TextView
            amountView = mView.findViewById(R.id.txtAmount) as TextView

        }
    }
}