package com.infernal93.listwithsearchmvvm.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.infernal93.listwithsearchmvvm.entity.Category
import com.infernal93.listwithsearchmvvm.remote.CategoryClient
import com.infernal93.listwithsearchmvvm.views.interfaces.CategoryListener


/**
 * Created by Armen Mkhitaryan on 30.12.2019.
 */
class CategoryViewModel : ViewModel() {

    var categoryListener: CategoryListener? = null

    fun getArrayList(): MutableLiveData<ArrayList<Category>> {
        return CategoryClient().loadCategory()
    }

//    var mutableLiveData = MutableLiveData<ArrayList<Category>>()
//
//    fun getArrayList(): MutableLiveData<ArrayList<Category>> {
//
//        mutableLiveData.value = CategoryClient().loadCategory() as ArrayList<Category>?
//
//        return  mutableLiveData
//
//    }

}