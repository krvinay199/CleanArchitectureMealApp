package com.example.mealapp.presentation.meal_details

import com.example.mealapp.domain.model.MealDetails

data class MealDetailsState(
    val data : MealDetails? = null,
    val error : String = " ",
    val isLoading : Boolean = false
)
