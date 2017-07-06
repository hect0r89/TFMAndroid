package com.master.tfm_android.models

data class BetModel(
        val event: String,
        val type: String,
        val pick: String,
        val account: Int = 1,
        val stake: Double = 0.1,
        val amount: Double,
        val odds: Double,
        val status: String,
        val user: Int = 1,
        val tipster: String
)
