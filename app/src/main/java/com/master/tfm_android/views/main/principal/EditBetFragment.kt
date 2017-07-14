package com.master.tfm_android.views.main.principal

import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.view.*
import android.widget.*
import com.master.tfm_android.R
import com.master.tfm_android.contracts.BetDetailContract
import com.master.tfm_android.models.BetModel
import com.master.tfm_android.presenters.ManageBetPresenter
import retrofit2.HttpException

class EditBetFragment : Fragment(), BetDetailContract.EditView {


    private var mPresenter: BetDetailContract.Presenter? = null
    private var bet: BetModel? = null
    private var betEditTexts: HashMap<String, EditText> = HashMap()
    private var stakeSpinner: Spinner? = null
    private var statusSpinner: Spinner? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        ManageBetPresenter(this)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear()
    }

    override fun onResume() {
        super.onResume()
        mPresenter?.start()
    }

    fun start() {
        mPresenter?.start()
    }


    companion object {
        fun newInstance(bet: BetModel): EditBetFragment {
            val fragment = EditBetFragment()
            fragment.bet = bet
            return fragment
        }
    }

    override fun setPresenter(presenter: BetDetailContract.Presenter) {
        mPresenter = checkNotNull(presenter)
    }


    @Nullable
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_create_bet, container, false)
        betEditTexts["event"] = root.findViewById(R.id.etEvent) as EditText
        betEditTexts["type"] = root.findViewById(R.id.etType) as EditText
        betEditTexts["pick"] = root.findViewById(R.id.etPick) as EditText
        betEditTexts["odds"] = root.findViewById(R.id.etOdds) as EditText
        betEditTexts["amount"] = root.findViewById(R.id.etAmount) as EditText
        betEditTexts["tipster"] = root.findViewById(R.id.etTipster) as EditText

        val btnCreateBet = root.findViewById(R.id.btnCreateBet) as Button
        btnCreateBet.text = "Edit"
        btnCreateBet.setOnClickListener { editBet() }

        stakeSpinner = root.findViewById(R.id.spinner_stake) as Spinner
        val stakeSpinnerAdapter = ArrayAdapter.createFromResource(activity, R.array.valores_array, android.R.layout.simple_spinner_item)
        stakeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        stakeSpinner?.adapter = stakeSpinnerAdapter

        statusSpinner = root.findViewById(R.id.spinner_status) as Spinner
        val statusSpinnerAdapter = ArrayAdapter.createFromResource(activity, R.array.status_array, android.R.layout.simple_spinner_item)
        statusSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        statusSpinner?.adapter = statusSpinnerAdapter
        this.bet?.let { updateBetDetail(it) }
        return root
    }

    fun updateBetDetail(bet: BetModel) {
        betEditTexts["event"]?.setText(bet.event)
        betEditTexts["type"]?.setText(bet.type)
        betEditTexts["pick"]?.setText(bet.pick)
        betEditTexts["odds"]?.setText("${bet.odds}")
        betEditTexts["amount"]?.setText("${bet.amount}")
        betEditTexts["tipster"]?.setText(bet.tipster)
        val stakePos = resources.getStringArray(R.array.valores_array).indexOf("${(bet.stake * 10).toInt()}/10")
        stakeSpinner?.setSelection(stakePos)
        val elem = resources.getStringArray(R.array.status_array).filter { it.first() == bet.status }
        val statusPos = resources.getStringArray(R.array.status_array).indexOf(elem[0])
        statusSpinner?.setSelection(statusPos)
        this.view?.visibility = View.VISIBLE
    }

    private fun editBet() {
        if (validFields()) {
            val editBet = BetModel(
                    null,
                    null,
                    null,
                    betEditTexts["event"]?.text.toString(),
                    betEditTexts["type"]?.text.toString(),
                    betEditTexts["pick"]?.text.toString(),
                    1,
                    "${stakeSpinner?.selectedItem}"[0].toString().toDouble() / 10,
                    betEditTexts["amount"]?.text.toString().toDouble(),
                    betEditTexts["odds"]?.text.toString().toDouble(),
                    "${statusSpinner?.selectedItem}"[0],
                    betEditTexts["tipster"]?.text.toString()
            )
            this.bet?.id?.let { id ->
                mPresenter?.editBet(id, editBet)
            }

        }
    }

    private fun validFields(): Boolean {
        var valid = true
        if (!checkEmptyField(betEditTexts["event"], "Event is mandatory.")) {
            valid = false
        }
        if (!checkEmptyField(betEditTexts["type"], "Type is mandatory.")) {
            valid = false
        }
        if (!checkEmptyField(betEditTexts["pick"], "Pick is mandatory.")) {
            valid = false
        }
        if (!checkEmptyField(betEditTexts["odds"], "Odds is mandatory.")) {
            valid = false
        }
        if (!checkEmptyField(betEditTexts["amount"], "Amount is mandatory.")) {
            valid = false
        }
        if (!checkEmptyField(betEditTexts["tipster"], "Tipster is mandatory.")) {
            valid = false
        }
        return valid

    }

    override fun closeEdit(bet : BetModel) {
        (activity as MainActivity).updateBetData(bet)
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

    interface OnUpdateBetDataListener {
        fun updateBetData(bet : BetModel)
    }

}
