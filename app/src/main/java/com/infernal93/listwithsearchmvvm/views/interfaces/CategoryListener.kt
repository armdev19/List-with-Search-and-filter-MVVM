package com.infernal93.listwithsearchmvvm.views.interfaces

import com.infernal93.listwithsearchmvvm.entity.Category

/**
 * Created by Armen Mkhitaryan on 30.12.2019.
 */
interface CategoryListener {

    fun showError(textResource: Int)
    fun setupEmptyList()
    fun setupCategoryList(categoryList: ArrayList<Category>)
    fun startLoading()
    fun endLoading()
    fun logOut()
}