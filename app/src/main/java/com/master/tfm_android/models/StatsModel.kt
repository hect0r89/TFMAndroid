package com.master.tfm_android.models

data class StatsModel(
        val money_staked: Double,
        val earnings: Double,
        val losses: Double,
        val bets_number: Int,
        val win_bets: Int,
        val lost_bets: Int,
        val null_bets: Int,
        val pending_bets: Double,
        val benefit: Double,
        val success_percentage: Double,
        val yield: Double,
        val odds_mean: Double,
        val bets_mean: Double,
        val evolution : HashMap<String, Double>

)
