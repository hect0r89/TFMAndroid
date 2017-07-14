package com.master.tfm_android.views.main.principal

import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.master.tfm_android.R
import com.master.tfm_android.contracts.CreateBetContract
import com.master.tfm_android.models.BetModel
import com.master.tfm_android.presenters.CreateBetPresenter
import retrofit2.HttpException


class CreateBetFragment : Fragment(), CreateBetContract.View {


    private var mPresenter: CreateBetContract.Presenter? = null
    private var bet: BetModel? = null
    private var createBetEditTexts: HashMap<String, EditText> = HashMap()
    private var stakeSpinner : Spinner? = null
    private var statusSpinner : Spinner? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CreateBetPresenter(this)
    }

    override fun onResume() {
        super.onResume()
        mPresenter?.start()
    }

    fun start() {
        mPresenter?.start()
    }


    companion object {
        fun newInstance(bet :  BetModel?): CreateBetFragment {
            val fragment = CreateBetFragment()
            bet?.let { fragment.bet = bet }
            return fragment
        }
    }

    override fun setPresenter(presenter: CreateBetContract.Presenter) {
        mPresenter = checkNotNull(presenter)
    }


    @Nullable
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        val root = inflater.inflate(R.layout.fragment_create_bet, container, false)
        createBetEditTexts["event"] = root.findViewById(R.id.etEvent) as EditText
        createBetEditTexts["type"] = root.findViewById(R.id.etType) as EditText
        createBetEditTexts["pick"] = root.findViewById(R.id.etPick) as EditText
        createBetEditTexts["odds"] = root.findViewById(R.id.etOdds) as EditText
        createBetEditTexts["amount"] = root.findViewById(R.id.etAmount) as EditText
        createBetEditTexts["tipster"] = root.findViewById(R.id.etTipster) as EditText

        bet?.let { copyBet(it) }


        val btnCreateBet = root.findViewById(R.id.btnCreateBet) as Button
        btnCreateBet.setOnClickListener { createBet() }

        stakeSpinner = root.findViewById(R.id.spinner_stake) as Spinner
        val stakeSpinnerAdapter = ArrayAdapter.createFromResource(activity, R.array.valores_array, android.R.layout.simple_spinner_item)
        stakeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        stakeSpinner?.adapter = stakeSpinnerAdapter

        statusSpinner = root.findViewById(R.id.spinner_status) as Spinner
        val statusSpinnerAdapter = ArrayAdapter.createFromResource(activity, R.array.status_array, android.R.layout.simple_spinner_item)
        statusSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        statusSpinner?.adapter = statusSpinnerAdapter
        bet?.let { copyBet(it) }
        return root
    }

    private fun  copyBet(bet: BetModel) {
        createBetEditTexts["event"]?.setText(bet.event)
        createBetEditTexts["type"]?.setText(bet.type)
        createBetEditTexts["pick"]?.setText(bet.pick)
        createBetEditTexts["odds"]?.setText("${bet.odds}")
        createBetEditTexts["amount"]?.setText("${bet.amount}")
        createBetEditTexts["tipster"]?.setText(bet.tipster)
        val stakePos = resources.getStringArray(R.array.valores_array).indexOf("${(bet.stake * 10).toInt()}/10")
        stakeSpinner?.setSelection(stakePos)
        val elem = resources.getStringArray(R.array.status_array).filter { it.first() == bet.status }
        val statusPos = resources.getStringArray(R.array.status_array).indexOf(elem[0])
        statusSpinner?.setSelection(statusPos)
        this.view?.visibility = View.VISIBLE
    }


    private fun createBet() {

        if (validFields()) {
            bet = BetModel(
                    null,
                    null,
                    null,
                    createBetEditTexts["event"]?.text.toString(),
                    createBetEditTexts["type"]?.text.toString(),
                    createBetEditTexts["pick"]?.text.toString(),
                    1,
                    "${stakeSpinner?.selectedItem}"[0].toString().toDouble() / 10,
                    createBetEditTexts["amount"]?.text.toString().toDouble(),
                    createBetEditTexts["odds"]?.text.toString().toDouble(),
                    "${statusSpinner?.selectedItem}"[0],
                    createBetEditTexts["tipster"]?.text.toString()
            )
            bet?.let { mPresenter?.createBet(it) }
        }


    }

    private fun validFields(): Boolean {
        var valid = true
        if (!checkEmptyField(createBetEditTexts["event"], "Event is mandatory.")) {
            valid = false
        }
        if (!checkEmptyField(createBetEditTexts["type"], "Type is mandatory.")) {
            valid = false
        }
        if (!checkEmptyField(createBetEditTexts["pick"], "Pick is mandatory.")) {
            valid = false
        }
        if (!checkEmptyField(createBetEditTexts["odds"], "Odds is mandatory.")) {
            valid = false
        }
        if (!checkEmptyField(createBetEditTexts["amount"], "Amount is mandatory.")) {
            valid = false
        }
        if (!checkEmptyField(createBetEditTexts["tipster"], "Tipster is mandatory.")) {
            valid = false
        }
        return valid

    }

    override fun closeFragment(){
        (activity as MainActivity).updateData()
        activity.supportFragmentManager.popBackStack()
    }

    private fun checkEmptyField(editText: EditText?, msg: String): Boolean {
        editText?.let { edit ->
            edit.text?.let {
                if (it.isEmpty()) {
                    edit.error = msg
                    return false
                }
            }
        }
        return true
    }

    override fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

    interface OnUpdateData {
        fun updateData()
    }

}