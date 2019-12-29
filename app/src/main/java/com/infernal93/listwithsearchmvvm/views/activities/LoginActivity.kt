package com.infernal93.listwithsearchmvvm.views.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.auth.FirebaseAuth
import com.infernal93.listwithsearchmvvm.R
import com.infernal93.listwithsearchmvvm.databinding.ActivityLoginBinding
import com.infernal93.listwithsearchmvvm.utils.longToast
import com.infernal93.listwithsearchmvvm.viewmodels.LoginViewModel
import com.infernal93.listwithsearchmvvm.views.interfaces.LoginListener
import kotlinx.android.synthetic.main.activity_login.*
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener

class LoginActivity : AppCompatActivity(), LoginListener, KeyboardVisibilityEventListener {
    private val TAG = "LoginActivity"

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        val binding: ActivityLoginBinding =DataBindingUtil.setContentView(this@LoginActivity, R.layout.activity_login)
        val loginViewModel = ViewModelProviders.of(this@LoginActivity).get(LoginViewModel::class.java)
        binding.loginViewModel = loginViewModel

        loginViewModel.loginListener = this@LoginActivity

        // Keyboard visibility
        KeyboardVisibilityEvent.setEventListener(this, this)


    }

    override fun startLoading() {
        btn_login_enter.visibility = View.GONE
        cpv_login.visibility = View.VISIBLE
    }

    override fun endLoading() {
        btn_login_enter.visibility = View.VISIBLE
        cpv_login.visibility = View.GONE
    }

    override fun showError(textResource: Int) {
        Toast.makeText(applicationContext, getString(textResource), Toast.LENGTH_SHORT).show()
    }

    override fun getLoginAndPassword() {
        longToast(R.string.login_and_password)
    }

    override fun validateLoginAndPassword() {
        startActivity(Intent(applicationContext, CategoryActivity::class.java))
        finish()
    }

    override fun onVisibilityChanged(isKeyboardOpen: Boolean) {
        if (isKeyboardOpen) {
            root_scroll_view.scrollTo(0, root_scroll_view.bottom)
        } else {
            root_scroll_view.scrollTo(0, root_scroll_view.top)
        }
    }
}
