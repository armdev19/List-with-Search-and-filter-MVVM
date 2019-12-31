package com.infernal93.listwithsearchmvvm.remote

import com.infernal93.listwithsearchmvvm.entity.Category
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Armen Mkhitaryan on 30.12.2019.
 */
interface CategoryApi {

    @GET(value = "Mycategory")

    fun fetchAllCategory(): Call<List<Category>>
}