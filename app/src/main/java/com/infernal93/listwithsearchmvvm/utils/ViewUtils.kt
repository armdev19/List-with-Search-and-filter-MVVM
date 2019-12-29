package com.infernal93.listwithsearchmvvm.utils

import android.content.Context
import android.widget.Toast

/**
 * Created by Armen Mkhitaryan on 29.12.2019.
 */

fun Context.shortToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.longToast(message: Int) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}