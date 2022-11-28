package com.example.mealapp.data.repository

import com.example.mealapp.data.model.MealsDTO
import com.example.mealapp.data.remote.MealSearchAPI
import com.example.mealapp.domain.repository.GetMealDetailsRepository

class GetMealDetailsImpl(private val mealSearchAPI: MealSearchAPI):GetMealDetailsRepository {

    override suspend fun getMealDetails(id: String): MealsDTO {
        return mealSearchAPI.getMealDetails(id)
    }

}
