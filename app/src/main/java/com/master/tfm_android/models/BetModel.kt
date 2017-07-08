package com.master.tfm_android.models

data class BetModel(
        val id : Int?,
        val event: String,
        val type: String,
        val pick: String,
        val account: Int?,
        val stake: Double,
        val amount: Double,
        val odds: Double,
        val status: Char,
        val tipster: String
)
