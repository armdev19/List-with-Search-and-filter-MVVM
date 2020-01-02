package com.infernal93.listwithsearchmvvm.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by Armen Mkhitaryan on 29.12.2019.
 */

data class Category(
    @SerializedName("category")
    val category: String,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int
)