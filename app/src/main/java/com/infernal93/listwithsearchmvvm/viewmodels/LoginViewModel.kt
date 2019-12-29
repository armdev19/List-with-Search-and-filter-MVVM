package com.infernal93.listwithsearchmvvm.viewmodels

import android.view.View
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.infernal93.listwithsearchmvvm.R
import com.infernal93.listwithsearchmvvm.views.interfaces.LoginListener

/**
 * Created by Armen Mkhitaryan on 29.12.2019.
 */

class LoginViewModel : ViewModel() {

    var mEmail: String = ""
    var mPassword: String = ""
    private lateinit var mAuth: FirebaseAuth

    var loginListener: LoginListener? = null

    fun testLogin(view: View) {
        loginListener?.startLoading()
        if (mEmail.isNullOrEmpty() || mPassword.isNullOrEmpty()) {
            loginListener?.showError(textResource = R.string.invalid_email_or_password)
            return
        }
        loginListener?.endLoading()
    }

    fun login(view: View) {

        if (mEmail.isNotEmpty() && mPassword.isNotEmpty()) {
            mAuth = FirebaseAuth.getInstance()
            mAuth.signInWithEmailAndPassword(mEmail, mPassword).addOnCompleteListener {
                if (it.isSuccessful) {
                    loginListener?.startLoading()
                    android.os.Handler().postDelayed({
                        loginListener?.endLoading()
                        loginListener?.validateLoginAndPassword()
                    }, 500)
                } else {
                    loginListener?.showError(textResource = R.string.login_error)
                }
            }
        } else {
            loginListener?.showError(textResource = R.string.login_or_password_empty)
        }
    }

    fun testRegisterButtonClick(view: View) {
        loginListener?.getLoginAndPassword()
    }
}