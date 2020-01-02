package com.infernal93.listwithsearchmvvm.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.infernal93.listwithsearchmvvm.entity.Category
import com.infernal93.listwithsearchmvvm.remote.CategoryClient
import com.infernal93.listwithsearchmvvm.views.interfaces.CategoryListener
import javax.inject.Inject

/**
 * Created by Armen Mkhitaryan on 30.12.2019.
 */
  class CategoryViewModel @Inject constructor(private val categoryClient: CategoryClient) : ViewModel() {

    var categoryListener: CategoryListener? = null

    fun getArrayList(): MutableLiveData<ArrayList<Category>> {
        return categoryClient.loadCategory()
    }

}