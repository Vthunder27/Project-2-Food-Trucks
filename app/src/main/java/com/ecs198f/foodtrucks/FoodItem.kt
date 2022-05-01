package com.ecs198f.foodtrucks

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FoodItem(
    val id: String,
    val truckId: String,
    val description: String,
    val name: String,
    val price: String,  // written as $_._ (tax included)
    val taxIncluded: Boolean
):Parcelable