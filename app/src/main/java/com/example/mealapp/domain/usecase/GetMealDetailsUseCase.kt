package com.example.mealapp.domain.usecase

import com.example.mealapp.common.Resource
import com.example.mealapp.data.model.toDomainMeal
import com.example.mealapp.data.model.toDomainMealDetails
import com.example.mealapp.domain.model.Meal
import com.example.mealapp.domain.model.MealDetails
import com.example.mealapp.domain.repository.GetMealDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMealDetailsUseCase @Inject constructor(private val repository: GetMealDetailsRepository) {

    operator fun invoke(id:String):Flow<Resource<MealDetails>> = flow{
        try{
            emit(Resource.Loading())

            val response = repository.getMealDetails(id).meals[0].toDomainMealDetails()

            emit(Resource.Success(data = response))

        } catch (e: HttpException){
            emit(Resource.Error(message = e.localizedMessage?: "Unknown Error"))

        } catch (e: IOException){
            emit(Resource.Error(message = e.localizedMessage?: "Check your Internet connection"))

        }catch (e:Exception){
            emit(Resource.Error(message = e.localizedMessage?: " "))

        }

    }
}