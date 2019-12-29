package com.infernal93.listwithsearchmvvm.views.interfaces

/**
 * Created by Armen Mkhitaryan on 29.12.2019.
 */
interface LoginListener {

    fun startLoading()
    fun endLoading()
    fun showError(textResource: Int)
    fun getLoginAndPassword()
    fun validateLoginAndPassword()
}