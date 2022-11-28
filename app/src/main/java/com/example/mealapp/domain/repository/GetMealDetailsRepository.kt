package com.example.mealapp.domain.repository

import com.example.mealapp.data.model.MealsDTO

interface GetMealDetailsRepository {

    suspend fun getMealDetails(id:String):MealsDTO
}