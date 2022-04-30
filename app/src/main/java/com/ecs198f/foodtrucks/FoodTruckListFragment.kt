package com.ecs198f.foodtrucks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDateTime

class FoodTruckListFragment : Fragment() {
    private val foodTrucks = listOf(
        FoodTruck(
            "1",
            "Shah's Halal",
            "https://android-course-ucd.web.app/img/food-trucks/Shah's_Halal.png",
            3,
            "Silo Patio",
            LocalDateTime.of(2021, 10, 19, 11, 0, 0, 0),
            LocalDateTime.of(2021, 10, 19, 16, 0, 0, 0),
        ),
        FoodTruck(
            "2",
            "Hefty Gyros",
            "https://android-course-ucd.web.app/img/food-trucks/Hefty_Gyros.png",
            2,
            "West Quad",
            LocalDateTime.of(2021, 10, 19, 11, 0, 0, 0),
            LocalDateTime.of(2021, 10, 19, 16, 0, 0, 0),
        ),
        FoodTruck(
            "4",
            "Star Ginger",
            "https://android-course-ucd.web.app/img/food-trucks/StarGinger.jpg",
            2,
            "West Quad",
            LocalDateTime.of(2021, 10, 4, 11, 0, 0, 0),
            LocalDateTime.of(2021, 10, 4, 16, 0, 0, 0),
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        activity?.title = "Food Trucks"
        val view = inflater.inflate(R.layout.fragment_food_truck_list, container, false)
        view.findViewById<RecyclerView>(R.id.foodTruckListRecyclerView).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = FoodTruckListRecyclerViewAdapter(foodTrucks)
        }
        return view
    }
}