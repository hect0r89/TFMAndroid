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


class RegisterFragment : Fragment(), AuthenticationContract.View {



    private var mPresenter: AuthenticationContract.Presenter? = null



    companion object {
        fun newInstance(): RegisterFragment {
            return RegisterFragment()
        }
    }

    override fun setPresenter(presenter: AuthenticationContract.Presenter) {
        mPresenter = checkNotNull(presenter)
    }



    @Nullable
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_register, container, false)
        val btnRegister = root.findViewById(R.id.btnRegister) as Button
        val editTextUsername = root.findViewById(R.id.editTextUsernameRegister) as EditText
        val editTextEmail = root.findViewById(R.id.editTextEmail) as EditText
        val editTextPassword = root.findViewById(R.id.editTextPassword) as EditText
        val editTextRepeatPassword = root.findViewById(R.id.editTextRepeatPassword) as EditText
        val linkLogin = root.findViewById(R.id.linkAlreadyMember) as TextView

        btnRegister.setOnClickListener {
            if (editTextPassword.text.toString() == editTextRepeatPassword.text.toString()){
                mPresenter?.register(editTextUsername.text.toString(), editTextPassword.text.toString(), editTextEmail.text.toString())
            }
            else{
                showErrors("Passwords must be equals")
            }
        }

        linkLogin.setOnClickListener { (activity as RegisterFragment.OnLoginClickListener).onLoginClick() }
        return root
    }

    override fun showErrors(msgError: String) {
        AlertDialog.Builder(context).setTitle("Error").setMessage(msgError).setPositiveButton("OK", { dialog, id ->  Log.d("Ok", "Login")}).show()
    }

    override fun showMainActivity() {
        (activity as OnCorrectLoginListener).onCorrectLogin()
    }


    interface OnLoginClickListener {
        fun onLoginClick()
    }



}
