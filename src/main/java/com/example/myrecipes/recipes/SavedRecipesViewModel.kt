package com.example.myrecipes.recipes

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.myrecipes.RecipeApplication
import com.example.myrecipes.data.models.Recipe
import com.example.myrecipes.repository.RecipeRepository

class SavedRecipesViewModel(
    private val recipeRepository: RecipeRepository
) : ViewModel() {
    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allRecipes: LiveData<List<Recipe>> = recipeRepository.allRecipes.asLiveData()

    // Define ViewModel factory in a companion object
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val myRepository = (this[APPLICATION_KEY] as RecipeApplication).repository
                SavedRecipesViewModel(
                    recipeRepository = myRepository
                )
            }
        }
    }
}