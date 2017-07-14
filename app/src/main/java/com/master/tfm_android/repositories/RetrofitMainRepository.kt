package com.master.tfm_android.repositories

import android.content.Context
import android.support.v7.app.AlertDialog
import android.widget.Toast
import com.master.tfm_android.models.BetModel
import com.master.tfm_android.models.StatsModel
import com.master.tfm_android.models.UserModel
import com.master.tfm_android.services.BetsApi
import com.master.tfm_android.utils.ActivityUtils.isInternetAvailable
import com.master.tfm_android.views.main.principal.MainActivity
import rx.Observable

class RetrofitMainRepository(val context : Context)  : MainRepository{



    val api = BetsApi.betService

    override fun getSubscribedBets(): Observable<List<BetModel>> {
        checkInternetConnection()
        return api.getSubscribedBets()
    }

    override fun getMyBets(): Observable<List<BetModel>> {
        checkInternetConnection()
        return api.getMyBets()
    }

    override fun getMyStats(): Observable<StatsModel> {
        checkInternetConnection()
        return api.getMyStats()
    }

    override fun createBet(bet: BetModel): Observable<BetModel> {
        checkInternetConnection()
        return api.createBet(bet)
    }

    override fun getBetDetail(id: Int): Observable<BetModel> {
        checkInternetConnection()
        return api.getBetDetail(id)
    }

    override fun getSubscribedBetDetail(id: Int): Observable<BetModel> {
        checkInternetConnection()
        return api.getSubscribedBetDetail(id)
    }

    override fun editBet(id : Int, bet: BetModel): Observable<BetModel> {
        checkInternetConnection()
        return api.editBet(id, bet)
    }

    override fun deleteBet(id: Int): Observable<Unit> {
        checkInternetConnection()
        return api.deleteBet(id)
    }

    override fun getUser(id: Int): Observable<UserModel> {
        checkInternetConnection()
        return api.getUser(id)
    }

    fun checkInternetConnection(){
        if(!isInternetAvailable(context)){
            val builder = AlertDialog.Builder(context)
            builder.setCancelable(false)
            builder.setTitle("Error").setMessage("Internet connection is not avalaible").setPositiveButton("OK", { dialog, id -> (context as MainActivity).finish() }).show()
        }
    }

    override fun getBets(user: Int): Observable<List<BetModel>> {
        checkInternetConnection()
        return api.getBets(user)
    }

    companion object {
        lateinit var instance: RetrofitMainRepository

        fun initialize(context : Context){
            instance = RetrofitMainRepository(context)
        }
    }



}