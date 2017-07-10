package com.master.tfm_android.views.main.principal

import android.content.DialogInterface
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.*
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.master.tfm_android.R
import com.master.tfm_android.contracts.BetDetailContract
import com.master.tfm_android.models.BetModel
import com.master.tfm_android.presenters.ManageBetPresenter
import com.master.tfm_android.utils.ActivityUtils


class BetDetailFragment : Fragment(), BetDetailContract.DetailView {


    private var mPresenter: BetDetailContract.Presenter? = null
    private var id: Int? = null
    private var owner: Boolean? = null
    private var bet: BetModel? = null
    private var betTextsViews: HashMap<String, TextView> = HashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ManageBetPresenter(this)
        owner?.let { if (it) setHasOptionsMenu(true) }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_bet_detail, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_edit) {
            this.bet?.let { (activity as MainActivity).onEditBetClick(it) }
            return true
        }
        if (id == R.id.action_delete) {
            AlertDialog.Builder(context)
                    .setMessage("Are you sure you want to delete this bet?")
                    .setPositiveButton("Yes") { _, _ ->
                        this.bet?.id?.let { mPresenter?.deleteBet(it) }

                    }
                    .setNegativeButton("No"
                    ) { _, _ -> }
                    .show()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        mPresenter?.start()
    }

    fun start() {
        mPresenter?.start()
    }


    companion object {
        fun newInstance(id: Int, owner: Boolean): BetDetailFragment {
            val fragment = BetDetailFragment()
            fragment.id = id
            fragment.owner = owner
            return fragment
        }
    }

    override fun setPresenter(presenter: BetDetailContract.Presenter) {
        mPresenter = checkNotNull(presenter)
    }

    @Nullable
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_bet_detail, container, false)
        betTextsViews["event"] = root.findViewById(R.id.textDetailEvent) as TextView
        betTextsViews["type"] = root.findViewById(R.id.txtDetailType) as TextView
        betTextsViews["pick"] = root.findViewById(R.id.txtDetailPick) as TextView
        betTextsViews["stake"] = root.findViewById(R.id.txtDetailStake) as TextView
        betTextsViews["odds"] = root.findViewById(R.id.txtDetailOdds) as TextView
        betTextsViews["amount"] = root.findViewById(R.id.txtDetailAmount) as TextView
        betTextsViews["status"] = root.findViewById(R.id.txtDetailStatus) as TextView
        betTextsViews["tipster"] = root.findViewById(R.id.txtDetailTipster) as TextView
        val btnCopy = root.findViewById(R.id.btn_copy_bet) as Button
        owner?.let { if (!it) btnCopy.visibility = View.VISIBLE }
        id?.let {
            if (owner as Boolean) mPresenter?.getBetDetail(it) else mPresenter?.getSubscribedBetDetail(it)
        }
        btnCopy.setOnClickListener { this.bet?.let { it1 -> (activity as OnCopyBetClickListener).onCopyBetClick(it1) } }

        root.visibility = View.INVISIBLE
        return root
    }



    override fun updateBetDetail(bet: BetModel) {
        this.bet = bet
        betTextsViews["event"]?.text = bet.event
        betTextsViews["type"]?.text = bet.type
        betTextsViews["pick"]?.text = bet.pick
        betTextsViews["stake"]?.text = "${bet.stake.times(10).toInt()}/10"
        betTextsViews["odds"]?.text = "${bet.odds}"
        betTextsViews["amount"]?.text = "${bet.amount} €"
        betTextsViews["status"]?.let { ActivityUtils.setStatus(bet, it) }
        betTextsViews["tipster"]?.text = bet.tipster
        this.view?.visibility = View.VISIBLE
    }

    override fun closeFragment() {
        (activity as MainActivity).updateData()
        activity.supportFragmentManager.popBackStack()
    }

    override fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }


    interface OnEditBetClickListener {
        fun onEditBetClick(bet: BetModel)
    }

    interface OnCopyBetClickListener {
        fun onCopyBetClick(bet: BetModel)
    }



}