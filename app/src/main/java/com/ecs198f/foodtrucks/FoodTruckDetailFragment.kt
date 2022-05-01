package com.ecs198f.foodtrucks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FoodTruckDetailFragment : Fragment() {
    private val args: FoodTruckDetailFragmentArgs by navArgs()

    val service = Retrofit.Builder()
        .baseUrl("https://api.foodtruck.schedgo.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(FoodTruckService::class.java)
    private val foodItems = listOf<FoodItem>()
    val myadapter =FoodItemListRecyclerViewAdapter(foodItems)

        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_food_truck_detail, container, false)
        view.findViewById<RecyclerView>(R.id.foodItemListRecyclerView).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = myadapter
        }

            service.getFoodItem(args.foodTruck.id).enqueue(object : Callback<List<FoodItem>>{
                override fun onResponse(
                    call: Call<List<FoodItem>>,
                    response: Response<List<FoodItem>>
                ) {
                    myadapter.updateFoodItem(response.body()!!)
                }

                override fun onFailure(call: Call<List<FoodItem>>, t: Throwable) {
                    throw t
                }

            })

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = args.foodTruck.name
        view.apply {
            val imageView = findViewById<ImageView>(R.id.foodTruckListItemImage)
            Glide.with(this).load(args.foodTruck.imageUrl).into(imageView)
            findViewById<TextView>(R.id.foodTruckListItemLocation).text =
                args.foodTruck.location
            findViewById<TextView>(R.id.foodTruckPriceLevel).text =
                "$".repeat(args.foodTruck.priceLevel)
            findViewById<TextView>(R.id.foodTruckListItemTime).text =
                args.foodTruck.formattedTimeInterval
        }
    }
}