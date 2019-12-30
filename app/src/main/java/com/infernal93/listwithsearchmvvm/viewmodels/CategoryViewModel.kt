package com.infernal93.listwithsearchmvvm.viewmodels

import android.view.View
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.infernal93.listwithsearchmvvm.views.interfaces.CategoryListener

/**
 * Created by Armen Mkhitaryan on 30.12.2019.
 */
class CategoryViewModel : ViewModel() {

    private lateinit var mAuth: FirebaseAuth
    var categoryListener: CategoryListener? = null


}