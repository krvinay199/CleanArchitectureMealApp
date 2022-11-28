package com.example.mealapp.presentation.meal_search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealapp.common.Resource
import com.example.mealapp.domain.usecase.GetMealDetailsUseCase
import com.example.mealapp.domain.usecase.GetMealSearchListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MealSearchViewModel @Inject constructor(private val getMealSearchListUseCase: GetMealSearchListUseCase) : ViewModel() {

    private val _mealSearchList = MutableStateFlow<MealSearchState>(MealSearchState())
    val mealSearchList : StateFlow<MealSearchState> = _mealSearchList

    fun searchMealList(s:String){
        getMealSearchListUseCase(s).onEach {
            when(it){
                is Resource.Loading-> {
                    _mealSearchList.value = MealSearchState(isLoading = true)
                }
                is Resource.Error-> {
                    _mealSearchList.value = MealSearchState(error = it.message?:" ")
                }
                is Resource.Success-> {
                    _mealSearchList.value = MealSearchState(data = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }

}