package com.example.mealapp.hilt

import com.example.mealapp.common.Constants
import com.example.mealapp.data.remote.MealSearchAPI
import com.example.mealapp.data.repository.GetMealDetailsImpl
import com.example.mealapp.data.repository.GetMealListImpl
import com.example.mealapp.domain.repository.GetMealDetailsRepository
import com.example.mealapp.domain.repository.MealSearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltModules {

    @Provides
    @Singleton
    fun provideMealSearchAPI():MealSearchAPI{
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(MealSearchAPI::class.java)
    }


    @Provides
    fun provideMealSearchRepository(mealSearchAPI: MealSearchAPI):MealSearchRepository{
        return GetMealListImpl(mealSearchAPI)
    }

    @Provides
    fun provideMealDetailsRepository(mealSearchAPI: MealSearchAPI):GetMealDetailsRepository{
        return GetMealDetailsImpl(mealSearchAPI)
    }


}