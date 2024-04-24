package com.example.mealsafari.ui.fragment

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.mealsafari.MealViewModel
import com.example.mealsafari.R
import com.example.mealsafari.databinding.HomeFragmentBinding
import com.example.mealsafari.ui.Adapter.CategoryAdapter
import com.example.mealsafari.ui.Adapter.PopularAdapter
import syntax.com.playground.data.model.meal.Meal

class HomeFragment : Fragment() {

    companion object{
        const val MEAL_ID="com.example.MealSafari.ui.fragments.idMeal"
        const val MEAL_NAME="com.example.MealSafari.ui.fragments.nameMeal"
        const val MEAL_THUMB="com.example.MealSafari.ui.fragments.thumbMeal"
        const val CATEGORY_NAME=" com.example.MealSafari.ui.fragments.categoryName"
        const val MEAL_STR=" com.example.MealSafari.ui.fragments.strMeal"
        const val MEAL_AREA=" com.example.MealSafari.ui.fragments.strArea"


    }

    private lateinit var binding: HomeFragmentBinding
    private val viewModel: MealViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(inflater)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadRandomMeal()
        viewModel.loadPopularMeal("Seafood")
        viewModel.loadAllMealCategories()
        viewModel.randomMeal.observe(viewLifecycleOwner) { mealObj: Meal ->
            binding.imgRandomMeal.load(mealObj.image)
        }

        binding.randomMealCard.setOnClickListener {
            findNavController().navigate(R.id.detailFragment)
        }
        viewModel.PopularMeal.observe(viewLifecycleOwner) { popularMeals ->
            binding.recViewMealPopular.adapter = PopularAdapter(popularMeals)
        }

        viewModel.getMealsByCategory.observe(viewLifecycleOwner) {
            binding.recViewCategories.adapter = CategoryAdapter(it)
        }

       binding.recViewCategories.setOnClickListener {
            findNavController().navigate(R.id.categoryMealsFragment)
        }


    }



}