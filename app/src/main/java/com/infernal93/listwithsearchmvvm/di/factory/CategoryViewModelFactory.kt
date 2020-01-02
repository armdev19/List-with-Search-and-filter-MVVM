package com.infernal93.listwithsearchmvvm.di.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.infernal93.listwithsearchmvvm.viewmodels.CategoryViewModel
import javax.inject.Inject
import javax.inject.Provider
/**
 * Created by Armen Mkhitaryan on 02.01.2020.
 */
class CategoryViewModelFactory @Inject constructor(private val categoryViewModelProvider: Provider<CategoryViewModel>)
    : ViewModelProvider.Factory{

    @Suppress(names = ["UNCHECKED_CAST"])
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return categoryViewModelProvider.get() as T
    }
}