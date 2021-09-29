

package com.example.mommymode.data

import androidx.annotation.DrawableRes

data class Task(
    val id: Long,
    val name: String,
    @DrawableRes
    val image: Int?,
    val description: String
) {

}