package com.example.mealsafari.ui.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mealsafari.ViewModel
import com.example.mealsafari.R
import com.example.mealsafari.databinding.CategoryItemBinding
import com.example.mealsafari.ui.Data.Category

class CategoryHomeAdapter(val mealCategory: List<Category>, val viewModel: ViewModel) :
    RecyclerView.Adapter<CategoryHomeAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = CategoryItemBinding.inflate(LayoutInflater.from(parent.context))
        return CategoryViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return mealCategory.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val mealCategory = mealCategory[position]
       // holder.binding.imgCategory.load(mealCategory.categoryImage)
       // holder.binding.

        holder.binding.apply {
            imgCategory.load(mealCategory.categoryImage)
            tvCategoryName.text = mealCategory.categoryName
        }

        holder.binding.root.setOnClickListener {
            viewModel.loadMealByCategory(mealCategory.categoryName)
            holder.itemView.findNavController().navigate(R.id.categoryDetailFragment)
        }

    }


}