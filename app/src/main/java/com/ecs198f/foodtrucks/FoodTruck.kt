package com.ecs198f.foodtrucks

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class FoodTruck(
    val id: String,
    val name: String,
    val imageResId: Int,
    val priceLevel: Int,
    val location: String,
    val openTime: LocalDateTime,
    val closeTime: LocalDateTime
) {
    val formattedTimeInterval: String
        get() = "${openTime.format(timeOnlyFormatter)} - ${closeTime.format(dateTimeFormatter)}"

    companion object {
        private val timeOnlyFormatter: DateTimeFormatter =
            DateTimeFormatter.ofPattern("hh:mm a")

        private val dateTimeFormatter: DateTimeFormatter =
            DateTimeFormatter.ofPattern("hh:mm a, EEE, MMM d")
    }
}