package com.master.tfm_android.views.main.principal

import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.master.tfm_android.R
import com.master.tfm_android.contracts.CreateBetContract
import com.master.tfm_android.models.BetModel
import com.master.tfm_android.presenters.CreateBetPresenter
import retrofit2.HttpException


class CreateBetFragment : Fragment(), CreateBetContract.View {


    private var mPresenter: CreateBetContract.Presenter? = null
    private var bet: BetModel? = null
    private var createBetEditTexts: HashMap<String, EditText> = HashMap()

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
        fun newInstance(): CreateBetFragment {
            return CreateBetFragment()
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
        createBetEditTexts["stake"] = root.findViewById(R.id.etStake) as EditText
        createBetEditTexts["odds"] = root.findViewById(R.id.etOdds) as EditText
        createBetEditTexts["amount"] = root.findViewById(R.id.etAmount) as EditText
        createBetEditTexts["status"] = root.findViewById(R.id.etStatus) as EditText
        createBetEditTexts["tipster"] = root.findViewById(R.id.etTipster) as EditText


        val btnCreateBet = root.findViewById(R.id.btnCreateBet) as Button
        btnCreateBet.setOnClickListener { createBet() }
        return root
    }

    private fun createBet() {
        if (validFields()) {
            bet = BetModel(
                    createBetEditTexts["event"]?.text.toString(),
                    createBetEditTexts["type"]?.text.toString(),
                    createBetEditTexts["pick"]?.text.toString(),
                    1,
                    createBetEditTexts["stake"]?.text.toString().toDouble(),
                    createBetEditTexts["amount"]?.text.toString().toDouble(),
                    createBetEditTexts["odds"]?.text.toString().toDouble(),
                    createBetEditTexts["status"]?.text.toString(),
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

    override fun showError(error: HttpException) {
        Toast.makeText(context, error.response().message(), Toast.LENGTH_LONG)
    }


}