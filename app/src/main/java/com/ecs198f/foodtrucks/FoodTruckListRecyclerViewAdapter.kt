package com.ecs198f.foodtrucks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ecs198f.foodtrucks.databinding.FoodTruckListItemBinding

class FoodTruckListRecyclerViewAdapter(private var items: List<FoodTruck>) :
    RecyclerView.Adapter<FoodTruckListRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(val binding: FoodTruckListItemBinding) : RecyclerView.ViewHolder(binding.root)

    fun updateFoodTruck(items: List<FoodTruck>)
    {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FoodTruckListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        items[position].let {
            holder.binding.apply {
                foodTruckListItemTitle.text = it.name
                foodTruckListItemPriceLevel.text = "$".repeat(it.priceLevel)
                Glide.with(holder.itemView).load(it.imageUrl).into(foodTruckListItemImage)
                foodTruckListItemLocation.text = it.location
                foodTruckListItemTime.text = it.formattedTimeInterval

                holder.itemView.setOnClickListener {
                    val action = FoodTruckListFragmentDirections
                        .actionFoodTruckListFragmentToFoodTruckDetailFragment(items[position])
                    root.findNavController().navigate(action)
                }
            }
        }
    }

    override fun getItemCount() = items.size
}