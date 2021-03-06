package com.master.tfm_android.views.authentication

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.master.tfm_android.R
import com.master.tfm_android.contracts.AuthenticationContract
import org.jetbrains.annotations.Nullable


class LoginFragment : Fragment(), AuthenticationContract.View {



    private var mPresenter: AuthenticationContract.Presenter? = null

    override fun setPresenter(presenter: AuthenticationContract.Presenter) {
        mPresenter = checkNotNull(presenter)
    }

    companion object {
        fun newInstance(): LoginFragment {
            return LoginFragment()
        }
    }



    @Nullable
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_login, container, false)
        val btnLogin = root.findViewById(R.id.btnLogin) as Button
        val linkRegister = root.findViewById(R.id.linkNotAccount) as TextView
        val txtUsername = root.findViewById(R.id.txtUsername) as EditText
        val txtPassword = root.findViewById(R.id.txtPassword) as EditText

        btnLogin.setOnClickListener {
            mPresenter?.login(txtUsername.text.toString(), txtPassword.text.toString())
        }
        linkRegister.setOnClickListener { (activity as OnRegisterClickListener).onRegisterClick() }

        return root
    }

    override fun showError(msgError : String) {
        AlertDialog.Builder(context).setTitle("Error").setMessage(msgError).setPositiveButton("OK", { dialog, id ->  Log.d("Ok", "Login")}).show()
    }

    override fun showMainActivity() {
        (activity as OnCorrectLoginListener).onCorrectLogin()
    }


    interface OnRegisterClickListener {
        fun onRegisterClick()
    }




}


