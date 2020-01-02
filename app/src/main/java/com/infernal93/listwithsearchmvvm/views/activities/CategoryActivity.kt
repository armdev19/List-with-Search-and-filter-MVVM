package com.infernal93.listwithsearchmvvm.views.activities

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.google.firebase.auth.FirebaseAuth
import com.infernal93.listwithsearchmvvm.R
import com.infernal93.listwithsearchmvvm.di.factory.CategoryViewModelFactory
import com.infernal93.listwithsearchmvvm.entity.Category
import com.infernal93.listwithsearchmvvm.viewmodels.CategoryViewModel
import com.infernal93.listwithsearchmvvm.views.adapters.CategoryAdapter
import com.infernal93.listwithsearchmvvm.views.interfaces.CategoryListener
import dagger.Binds
import dagger.Component
import dagger.Module
import kotlinx.android.synthetic.main.activity_category.*
import javax.inject.Inject

class CategoryActivity : AppCompatActivity(), CategoryListener {
    private val TAG = "CategoryActivity"

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    lateinit var categoryViewModel: CategoryViewModel

    private lateinit var mAuth: FirebaseAuth
    private lateinit  var toolbar: Toolbar
    private lateinit var mAdapter: CategoryAdapter

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        mAuth = FirebaseAuth.getInstance()

        // MVVM implementation use Dagger
        DaggerCategoryActivity_CategoryComponent.create().inject(this@CategoryActivity)
        categoryViewModel = ViewModelProviders.of(this@CategoryActivity, factory).get(CategoryViewModel::class.java)
        categoryViewModel.getArrayList().observe(this@CategoryActivity, Observer {

            mAdapter = CategoryAdapter(this@CategoryActivity, it)
            recycler_category.layoutManager = LinearLayoutManager(applicationContext, OrientationHelper.VERTICAL, false)
            recycler_category.adapter = mAdapter
            recycler_category.setHasFixedSize(true)
        })

        // Sort list
        btn_sort.setOnClickListener {
            val builder = AlertDialog.Builder(this@CategoryActivity)
            builder.setTitle(R.string.alert_dialog_title)
            builder.setMessage(R.string.alert_dialog_message)
            builder.setPositiveButton(R.string.alert_dialog_first_button) {dialogInterface, i ->
                mAdapter.sortByName()
            }

            builder.setNegativeButton(R.string.alert_dialog_second_button) {dialogInterface, i ->
                mAdapter.sortByPrice()
            }
            builder.show()
        }

        // Filter list
        btn_filter.setOnClickListener {

        }

        // Search
        txt_category_search.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                mAdapter.search(s.toString())
            }
        })

        categoryViewModel.categoryListener = this@CategoryActivity

        // Toolbar implementation
        toolbar = findViewById(R.id.toolbar_category)
        setSupportActionBar(toolbar_category)
    }

    // Dagger create
    @Component (modules = [CategoryModule::class])
    interface CategoryComponent {
        fun inject (activity: CategoryActivity)
    }
    @Module
    abstract class CategoryModule {

        @Binds
        abstract fun bindViewmodelFactory(factory: CategoryViewModelFactory): ViewModelProvider.Factory
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
        txt_category_no_items.text = getString(textResource)
    }

    override fun setupEmptyList() {
        recycler_category.visibility = View.GONE
        btn_filter.visibility = View.GONE
        btn_sort.visibility = View.GONE
        txt_category_no_items.visibility = View.VISIBLE
    }

    override fun setupCategoryList(categoryList: ArrayList<Category>) {
        recycler_category.visibility = View.VISIBLE
        btn_filter.visibility = View.VISIBLE
        btn_sort.visibility = View.VISIBLE
        txt_category_no_items.visibility = View.GONE

        mAdapter.setupCategory(categoryList = categoryList)
    }

    override fun startLoading() {
        recycler_category.visibility = View.GONE
        txt_category_no_items.visibility = View.GONE
        btn_filter.visibility = View.GONE
        btn_sort.visibility = View.GONE
        cpv_category.visibility = View.VISIBLE
    }

    override fun endLoading() {
        cpv_category.visibility = View.GONE
    }

    override fun logOut() {
        mAuth = FirebaseAuth.getInstance()
        mAuth.signOut()
        startActivity(Intent(this@CategoryActivity, LoginActivity::class.java))
        finish()
    }
}