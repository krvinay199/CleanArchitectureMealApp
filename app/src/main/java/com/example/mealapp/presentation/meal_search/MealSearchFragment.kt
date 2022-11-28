package com.example.mealapp.presentation.meal_search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import com.example.mealapp.R
import com.example.mealapp.databinding.FragmentMealSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealSearchFragment : Fragment() {

    private var _binding : FragmentMealSearchBinding? = null
    val binding : FragmentMealSearchBinding
    get() = _binding!!

    private val mealSearchViewModel: MealSearchViewModel by viewModels()
    private val mealSearchAdapter = MealSearchAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMealSearchBinding.inflate(inflater,container,false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.searchMeal.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {

                query?.let {
                    mealSearchViewModel.searchMealList(it)
                }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })

        binding.searchRecycler.apply {
            adapter = mealSearchAdapter
        }

        lifecycle.coroutineScope.launchWhenCreated {
            mealSearchViewModel.mealSearchList.collect{

                if (it.isLoading){
                    binding.notFound.visibility = View.GONE
                    binding.progressMealSearch.visibility = View.VISIBLE
                }
                if (it.error.isNotBlank()){
                    binding.notFound.visibility = View.GONE
                    binding.progressMealSearch.visibility = View.GONE

                }
                it.data?.let {
                    if (it.isEmpty()){
                        binding.notFound.visibility = View.VISIBLE
                    }

                    binding.progressMealSearch.visibility = View.GONE
                    mealSearchAdapter.setContentList(it.toMutableList())

                }

            }
        }

        mealSearchAdapter.itemClickListener {
            findNavController().navigate(
                MealSearchFragmentDirections.actionMealSearchFragmentToMealDetailsFragment(mealId = it.mealId)
//            R.layout.fragment_meal_details
            )
        }


    }
}