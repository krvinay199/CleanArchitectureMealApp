package com.example.mealapp.data.repository

import com.example.mealapp.data.model.MealsDTO
import com.example.mealapp.data.remote.MealSearchAPI
import com.example.mealapp.domain.repository.MealSearchRepository

class GetMealListImpl(private val mealSearchAPI: MealSearchAPI): MealSearchRepository {


    override suspend fun getMealList(s: String): MealsDTO {
        return mealSearchAPI.getMealList(s)
    }


}