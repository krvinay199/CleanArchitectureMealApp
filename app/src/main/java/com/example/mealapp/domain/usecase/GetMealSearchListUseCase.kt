package com.example.mealapp.domain.usecase

import com.example.mealapp.common.Resource
import com.example.mealapp.data.model.toDomainMeal
import com.example.mealapp.domain.model.Meal
import com.example.mealapp.domain.repository.MealSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMealSearchListUseCase @Inject constructor(private val repository: MealSearchRepository) {

    operator fun invoke(s:String):Flow<Resource<List<Meal>>> = flow {
        try{
            emit(Resource.Loading())

            val response = repository.getMealList(s)
            val list = if (response.meals.isNullOrEmpty()) emptyList<Meal>() else response.meals.map { it.toDomainMeal() }

            emit(Resource.Success(data = list))

        } catch (e:HttpException){
            emit(Resource.Error(message = e.localizedMessage?: "Unknown Error"))

        } catch (e:IOException){
            emit(Resource.Error(message = e.localizedMessage?: "Check your Internet connection"))

        }catch (e:Exception){
            emit(Resource.Error(message = e.localizedMessage?: " "))

        }
    }
}