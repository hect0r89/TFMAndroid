package com.master.tfm_android.views.main.principal

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import com.master.tfm_android.R
import com.master.tfm_android.contracts.MyStatsContract
import com.master.tfm_android.models.StatsModel
import com.master.tfm_android.presenters.MyStatsPresenter
import org.jetbrains.annotations.Nullable
import java.util.ArrayList
import java.util.HashMap

class StatsDetailFragment : Fragment() {

    private var stats: StatsModel? = null
    private var statsTextViews: HashMap<String, TextView> = HashMap()
    var graph : GraphView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }

    fun start() {
    }


    companion object {
        fun newInstance(): StatsDetailFragment {
            return StatsDetailFragment()
        }
    }


    @Nullable
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_my_stats, container, false)
        statsTextViews["bet"] = root.findViewById(R.id.txtBet) as TextView
        statsTextViews["earnings"] = root.findViewById(R.id.txtEarnings) as TextView
        statsTextViews["mean_bet"] = root.findViewById(R.id.txtMeanBets) as TextView
        statsTextViews["number_bets"] = root.findViewById(R.id.txtBetNumber) as TextView
        statsTextViews["fails"] = root.findViewById(R.id.txtFails) as TextView
        statsTextViews["benefits"] = root.findViewById(R.id.txtBenefits) as TextView
        statsTextViews["losses"] = root.findViewById(R.id.txtLosses) as TextView
        statsTextViews["mean_odd"] = root.findViewById(R.id.txtMeanOdds) as TextView
        statsTextViews["wins"] = root.findViewById(R.id.txtWins) as TextView
        statsTextViews["nulls"] = root.findViewById(R.id.txtNulls) as TextView
        statsTextViews["yield"] = root.findViewById(R.id.txtYield) as TextView
        statsTextViews["success"] = root.findViewById(R.id.txtSuccess) as TextView

        graph = root.findViewById(R.id.graph) as GraphView



        return root
    }

    fun updateStats(stats: StatsModel) {
        this.stats = stats
        if (statsTextViews.isNotEmpty()) {
            statsTextViews["bet"]?.text = "${stats.money_staked} €"
            statsTextViews["earnings"]?.text = "${stats.earnings.format(2)} €"
            statsTextViews["earnings"]?.setTextColor(Color.parseColor("#64DD17"))
            statsTextViews["mean_bet"]?.text = "${stats.bets_mean.format(2)} €"
            statsTextViews["number_bets"]?.text = "${stats.bets_number}"
            statsTextViews["fails"]?.text = "${stats.lost_bets}"
            statsTextViews["benefits"]?.text = "${stats.benefit.format(2)} €"
            if (stats.benefit > 0) statsTextViews["benefits"]?.setTextColor(Color.parseColor("#64DD17"))
            else if (stats.benefit < 0) statsTextViews["benefits"]?.setTextColor(Color.parseColor("#C62828"))
            statsTextViews["losses"]?.text = "${stats.losses.format(2)} €"
            statsTextViews["losses"]?.setTextColor(Color.parseColor("#C62828"))
            statsTextViews["mean_odd"]?.text = stats.odds_mean.format(2)
            statsTextViews["wins"]?.text = "${stats.win_bets}"
            statsTextViews["nulls"]?.text = "${stats.null_bets}"
            statsTextViews["yield"]?.text = "${stats.yield.format(2)} %"
            if (stats.yield > 0) statsTextViews["yield"]?.setTextColor(Color.parseColor("#64DD17"))
            else if (stats.yield < 0) statsTextViews["yield"]?.setTextColor(Color.parseColor("#C62828"))
            statsTextViews["success"]?.text = "${stats.success_percentage.format(2)} %"
            val evolutionOrdered = stats.evolution.toSortedMap()
            var arrayData = ArrayList<DataPoint>()
            for (elem in evolutionOrdered){
                arrayData.add(DataPoint(elem.key.toDouble(), elem.value))
            }
            val series = LineGraphSeries<DataPoint>(arrayData.toTypedArray())
            graph?.removeAllSeries()
            graph?.addSeries(series)
            graph?.title= "Last 5 months yield"


        }
    }

    fun Double.format(digits: Int) = java.lang.String.format("%.${digits}f", this)




}

