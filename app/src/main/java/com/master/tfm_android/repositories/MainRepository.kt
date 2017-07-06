package com.master.tfm_android.repositories

import com.master.tfm_android.models.BetModel
import rx.Observable

interface MainRepository {
    fun getSubscribedBets() : Observable<List<BetModel>>
}