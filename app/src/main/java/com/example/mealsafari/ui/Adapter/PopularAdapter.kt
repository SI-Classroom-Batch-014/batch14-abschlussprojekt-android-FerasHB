package com.example.mealsafari.ui.Adapter

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mealsafari.MealViewModel
import com.example.mealsafari.R
import com.example.mealsafari.databinding.PopularItemsBinding
import com.example.mealsafari.ui.Data.MealList
import syntax.com.playground.data.model.meal.Meal

const val DETAIL = "DETAIL"

class PopularAdapter(val meals: List<Meal>, val viewModel: MealViewModel) :
    RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {
    val onItemClick: ((MealList) -> Unit)? = null


    inner class PopularViewHolder(val binding: PopularItemsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        val binding = PopularItemsBinding.inflate(LayoutInflater.from(parent.context))
        return PopularViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return meals.size
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        val mealPopular = meals[position]


        // Laden des Bildes in das ImageView im ViewHolder
        holder.binding.imgPopularMeal.load(mealPopular.image)
        holder.binding.popularName.text = mealPopular.name


        holder.binding.root.setOnClickListener {


            val bundle = Bundle()
            bundle.putSerializable("detail", mealPopular)
            holder.binding.imgPopularMeal.findNavController()
                .navigate(R.id.action_homeFragment_to_detailFragment, bundle)
            Log.e(TAG, "Error loading Data from API Popular: ")

            viewModel.setMeal(mealPopular)


        }


    }


}