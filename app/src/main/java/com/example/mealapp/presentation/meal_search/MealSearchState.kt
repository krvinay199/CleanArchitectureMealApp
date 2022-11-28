package com.example.mealapp.presentation.meal_search

import com.example.mealapp.domain.model.Meal

data class MealSearchState(
    val data : List<Meal>? = null,
    val error : String = " ",
    val isLoading : Boolean = false
)