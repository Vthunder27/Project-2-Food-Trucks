package com.ecs198f.foodtrucks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.time.LocalDateTime

class FoodTruckListFragment : Fragment() {
    val gson = GsonBuilder().registerTypeAdapter(LocalDateTime::class.java, object : JsonDeserializer<LocalDateTime>{
        override fun deserialize(
            json: JsonElement?,
            typeOfT: Type?,
            context: JsonDeserializationContext?
        ): LocalDateTime {
            return LocalDateTime.parse(json!!.asString)
        }
    })
        .registerTypeAdapter(LocalDateTime::class.java, object :JsonSerializer<LocalDateTime>{
            override fun serialize(
                src: LocalDateTime?,
                typeOfSrc: Type?,
                context: JsonSerializationContext?
            ): JsonElement {
                return JsonPrimitive(src!!.toString())
            }

        })
        .create()

    val service = Retrofit.Builder()
        .baseUrl("https://api.foodtruck.schedgo.com")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(FoodTruckService::class.java)

    private val foodTrucks = listOf<FoodTruck>()

    val myadapter = FoodTruckListRecyclerViewAdapter(foodTrucks)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        activity?.title = "Food Trucks"
        val view = inflater.inflate(R.layout.fragment_food_truck_list, container, false)
        view.findViewById<RecyclerView>(R.id.foodTruckListRecyclerView).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = myadapter
        }

        service.listFoodTrucks().enqueue(object : Callback<List<FoodTruck>>{
            override fun onResponse(
                call: Call<List<FoodTruck>>,
                response: Response<List<FoodTruck>>
            ) {

                myadapter.updateFoodTruck(response.body()!!)
            }

            override fun onFailure(call: Call<List<FoodTruck>>, t: Throwable) {
                throw t
            }
        })

        return view
    }


}