package com.ecs198f.foodtrucks

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface FoodTruckService {
    @GET("/food-trucks")
    fun listFoodTrucks(): Call<List<FoodTruck>>

    @POST("/food-trucks")
    fun createFoodTrucks(@Body foodTruck: FoodTruck): Call<Unit>

    @GET("/food-trucks/{id}")
    fun getFoodTruck(@Path("id") id: Int): Call<FoodTruck>

    @GET("/food-trucks/{truckId}/items")
    fun getFoodItem(@Path("truckId") id: String): Call<List<FoodItem>>

    @POST("/food-trucks/{truckId}/items")
    fun createFoodItem(@Path("truckId")@Body foodItem:FoodItem): Call<Unit>
}