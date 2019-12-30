package com.infernal93.listwithsearchmvvm.views.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.auth.FirebaseAuth
import com.infernal93.listwithsearchmvvm.R
import com.infernal93.listwithsearchmvvm.databinding.ActivityCategoryBinding
import com.infernal93.listwithsearchmvvm.entity.Category
import com.infernal93.listwithsearchmvvm.viewmodels.CategoryViewModel
import com.infernal93.listwithsearchmvvm.views.interfaces.CategoryListener


class CategoryActivity : AppCompatActivity(), CategoryListener {
    private val TAG = "CategoryActivity"

    private lateinit var mAuth: FirebaseAuth
    private lateinit  var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_category)

        mAuth = FirebaseAuth.getInstance()

        val binding: ActivityCategoryBinding =
            DataBindingUtil.setContentView(this@CategoryActivity, R.layout.activity_category)
        val categoryViewModel =
            ViewModelProviders.of(this@CategoryActivity).get(CategoryViewModel::class.java)
        binding.categoryViewModel = categoryViewModel

        categoryViewModel.categoryListener = this@CategoryActivity

        // Toolbar implementation
        toolbar = findViewById(R.id.toolbar_category)
        setSupportActionBar(binding.toolbarCategory)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_log_out) {
            logOut()
        }
        return true
    }

    override fun onStart() {
        super.onStart()
        if (mAuth.currentUser == null) {
            logOut()
        }
    }

    override fun showError(textResource: Int) {

    }

    override fun setupEmptyList() {

    }

    override fun setupCategoryList(categoryList: ArrayList<Category>) {

    }

    override fun startLoading() {

    }

    override fun endLoading() {

    }

    override fun logOut() {
        mAuth = FirebaseAuth.getInstance()
        mAuth.signOut()
        startActivity(Intent(this@CategoryActivity, LoginActivity::class.java))
        finish()
    }

    // !! Не забыть убрать лишний метод
    private fun SendToLoginActivity() {
        startActivity(Intent(this@CategoryActivity, LoginActivity::class.java))
        finish()
    }
}
