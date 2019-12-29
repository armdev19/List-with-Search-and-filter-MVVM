package com.infernal93.listwithsearchmvvm.viewmodels

import android.view.View
import androidx.lifecycle.ViewModel
import com.infernal93.listwithsearchmvvm.R
import com.infernal93.listwithsearchmvvm.views.interfaces.LoginListener

/**
 * Created by Armen Mkhitaryan on 29.12.2019.
 */

class LoginViewModel : ViewModel() {

    var email: String? = null
    var password: String? = null

    var loginListener: LoginListener? = null

    fun testLoginButtonClick(view: View) {
        loginListener?.startLoading()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            loginListener?.showError(textResource = R.string.invalid_email_or_password)
            return
        }
        loginListener?.endLoading()
    }

    fun testRegisterButtonClick(view: View) {
        loginListener?.getLoginAndPassword()
    }
}