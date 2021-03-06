package com.ecs198f.foodtrucks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ecs198f.foodtrucks.databinding.FoodListItemBinding

class FoodItemListRecyclerViewAdapter(private var foodItems: List<FoodItem>) :
    RecyclerView.Adapter<FoodItemListRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(val binding: FoodListItemBinding): RecyclerView.ViewHolder(binding.root)

    fun updateFoodItem(items: List<FoodItem>)
    {
        this.foodItems = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FoodListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        foodItems[position].let {
            holder.binding.apply {
                foodItemName.text = it.name
                var taxstr: String
                taxstr = " (tax included)"

                when(it.taxIncluded)
                {
                    true -> foodItemPrice.text = '$' + it.price + taxstr
                    false -> foodItemPrice.text = '$' +it.price
                }

                foodItemDescription.text = it.description
            }
        }
    }



    override fun getItemCount() = foodItems.size
}