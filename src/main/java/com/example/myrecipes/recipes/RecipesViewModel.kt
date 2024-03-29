package com.example.myrecipes.recipes

import androidx.lifecycle.*
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.myrecipes.RecipeApplication
import com.example.myrecipes.data.models.Recipe
import com.example.myrecipes.repository.RecipeRepository
import kotlinx.coroutines.launch

class RecipesViewModel(
    private val recipeRepository: RecipeRepository
) : ViewModel() {

    // Don't need suspend as using viewModelScope
    //  Launching a new coroutine to insert the data in a non-blocking way
    fun saveRecipe(recipe: Recipe) = viewModelScope.launch {
        recipeRepository.upsert(recipe)
    }

    // Define ViewModel factory in a companion object
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val myRepository = (this[APPLICATION_KEY] as RecipeApplication).repository
                RecipesViewModel(
                    recipeRepository = myRepository
                )
            }
        }
    }
}
