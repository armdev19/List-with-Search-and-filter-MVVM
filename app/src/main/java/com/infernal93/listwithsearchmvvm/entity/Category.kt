package com.infernal93.listwithsearchmvvm.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by Armen Mkhitaryan on 29.12.2019.
 */

data class Category(
    @SerializedName(value = "category")
    val category: String,
    @SerializedName(value = "icon")
    val icon: String,
    @SerializedName(value = "name")
    val name: String,
    @SerializedName(value = "price")
    val price: Int
)